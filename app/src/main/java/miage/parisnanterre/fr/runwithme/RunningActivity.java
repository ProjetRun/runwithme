package miage.parisnanterre.fr.runwithme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.widget.Chronometer;
import android.widget.TextView;

public class RunningActivity extends AppCompatActivity {

    TextView KM_DISPLAY;
    TextView KMH_DISPLAY;
    LocationManager  locationManager;
    LocationListener locationListener;

    Location current_location;
    Location last_location;
    double distance;
    double speed;
    java.text.DecimalFormat df;
    Chronometer simpleChronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        getSupportActionBar().hide();

        KM_DISPLAY= (TextView) findViewById(R.id.textView_KM_DISPLAY);
        KMH_DISPLAY= (TextView) findViewById(R.id.textView_KMH_DISPLAY);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        current_location = new Location("current_location");
        current_location.setLatitude(40);
        current_location.setLongitude(12);
        last_location = new Location("last_location");
        distance = 0.2;
        df = new java.text.DecimalFormat("0.##");
        simpleChronometer = (Chronometer) findViewById(R.id.textView_MIN_DISPLAY);
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.start(); // start a chronometer
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                /*last_location.setLatitude(location.getLatitude());
                last_location.setLongitude(location.getLongitude());
                current_location.setLatitude(location.getLatitude());
                current_location.setLongitude(location.getLongitude());
                //distance = distance + current_location.distanceTo(last_location)/1000;
                //distance = current_location.ge* distance;
                distance += meterDistanceBetweenPoints((float) current_location.getLatitude(), (float) current_location.getLongitude(), (float) last_location.getLatitude(),(float) last_location.getLongitude())/1000;
                */
                distance = distance*1.01;
                KM_DISPLAY.setText(""+df.format(distance));
                calculateAndDisplaySpeed();



            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        configureLocation();


    }




    void configureLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        locationManager.requestLocationUpdates("gps", 500, 0, locationListener);
    }

    private double meterDistanceBetweenPoints(float lat_a, float lng_a, float lat_b, float lng_b) {
        float pk = (float) (180.f/Math.PI);

        float a1 = lat_a / pk;
        float a2 = lng_a / pk;
        float b1 = lat_b / pk;
        float b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 6366000 * tt;
    }

    private void calculateAndDisplaySpeed(){
        //int speed = distance/simpleChronometer.getBase();
        double meter = distance *1000;
        long elapsedMillis = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
        double second = elapsedMillis/1000;
        KMH_DISPLAY.setText(""+df.format(meter/second));
    }

}
