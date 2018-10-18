package miage.parisnanterre.fr.runwithme;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment implements LocationListener {

    TextView selectCity, cityField, detailsField, currentTemperatureField, humidity_field, pressure_field,
            weatherIcon, updatedField, aqius, aqicn, levelp;
    ProgressBar loader;
    Typeface weatherFont;
    ImageView pollutionicon;
    String city ;
    double lat;
    double longit;
    String cityloc = "48.8566,2.3522";
    private static final int PERMS_CALL_ID = 1234;
    /*  API Key à partir du siteweb https://openweathermap.org*/
    String OPEN_WEATHER_MAP_API = "92a0cb640cc371cd8be907cb79ae4194";

    String POLLUTION_API = "BfsDNLRQan6JbNrts";

    LocationManager locationManager;
    double latitude;
    double longitude;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment



        return inflater.inflate(R.layout.fragment_home, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        checkPermissions();

        Intent i =new Intent(getActivity().getApplicationContext(),GPSService.class);
        getActivity().startService(i);



        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        onLocationChanged(location);
        loc_fun(location);



        loader = (ProgressBar) view.findViewById(R.id.loader);
        selectCity = (TextView) view.findViewById(R.id.selectCity);
        cityField = (TextView) view.findViewById(R.id.city_field);
        updatedField = (TextView) view.findViewById(R.id.updated_field);
        detailsField = (TextView) view.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) view.findViewById(R.id.current_temperature_field);
        humidity_field = (TextView) view.findViewById(R.id.humidity_field);
        pressure_field = (TextView) view.findViewById(R.id.pressure_field);

        aqius = view.findViewById(R.id.aqius);
        aqicn = view.findViewById(R.id.aqicn);
        levelp = view.findViewById(R.id.levelp);

        pollutionicon = view.findViewById(R.id.pollutionicon);

        weatherIcon = (TextView) view.findViewById(R.id.weather_icon);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weathericons-regular-webfont.ttf");
        weatherIcon.setTypeface(weatherFont);

        taskLoadUp(city);

        selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Change City");
                final EditText input = new EditText(getActivity());
                input.setText(city);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Change",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                city = input.getText().toString();
                                taskLoadUp(city);
                            }
                        });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialog.show();
            }
        });

        Button b = (Button) view.findViewById(R.id.buttonLaunchRunning);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchRunningActivity(v);
            }
        });
    }


    public void taskLoadUp(String query) {
        if (Weather.isNetworkAvailable(getActivity())) {
            HomeFragment.DownloadWeather task = new HomeFragment.DownloadWeather();
            task.execute(query);

            HomeFragment.DownloadPollution task2 = new HomeFragment.DownloadPollution();
            task2.execute(query);

        } else {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        lat = latitude;
        longit = longitude;

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void loc_fun(Location location){

        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String country = addresses.get(0).getCountryName();
            String ville = addresses.get(0).getLocality();

      //      String region = addresses.get(0).getAdminArea();

      //      Log.d(":::::::::::::::",region);

            city = ville +"," + country;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    class DownloadWeather extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);

        }
        protected String doInBackground(String...args) {
            String xml = Weather.excuteGet("http://api.openweathermap.org/data/2.5/weather?q=" + args[0] +
                    "&units=metric&appid=" + OPEN_WEATHER_MAP_API);


            return xml;
        }



        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();

                    cityField.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    detailsField.setText(details.getString("description").toUpperCase(Locale.US));
                    currentTemperatureField.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    humidity_field.setText("Humidity: " + main.getString("humidity") + "%");
                    pressure_field.setText("Pressure: " + main.getString("pressure") + " hPa");
                    updatedField.setText(df.format(new Date(json.getLong("dt") * 1000)));
                    weatherIcon.setText(Html.fromHtml(Weather.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));

                    loader.setVisibility(View.GONE);


                }
            } catch (JSONException e) {
                Toast.makeText(getActivity(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }


        }

    }


    class DownloadPollution extends AsyncTask< String, Void, String > {

        protected String doInBackground(String...args) {

            String xml = Weather.excuteGet("http://api.airvisual.com/v2/nearest_city?lat="+lat+"&lon="+longit+"&key="
                    + POLLUTION_API);
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details2 = json.getJSONObject("data").getJSONObject("current").getJSONObject("pollution");
                    aqius.setText("AQI US: " +details2.getInt("aqius")+" " +details2.getString("mainus"));
                    aqicn.setText("AQI CN: " +details2.getInt("aqicn")+" " +details2.getString("maincn"));

                    if(details2.getInt("aqius") <= 50){
                        levelp.setText("Level: Good");
                        pollutionicon.setImageResource(R.drawable.level1);
                    }
                    else if(details2.getInt("aqius") >50 & details2.getInt("aqius" )<=100){
                        levelp.setText("Level: Moderate");
                        pollutionicon.setImageResource(R.drawable.level2);
                    }
                    else if(details2.getInt("aqius") >100 & details2.getInt("aqius" )<=150){
                        levelp.setText("Level: Unhealthy for Sensitive Groups");
                        pollutionicon.setImageResource(R.drawable.level3);
                    }
                    else if(details2.getInt("aqius") >150 & details2.getInt("aqius" )<=200){
                        levelp.setText("Level: Unhealthy");
                        pollutionicon.setImageResource(R.drawable.level4);
                    }
                    else if(details2.getInt("aqius") >200 & details2.getInt("aqius" )<=300){
                        levelp.setText("Level: Very Unhealthy");
                        pollutionicon.setImageResource(R.drawable.level5);
                    }
                    else {
                        levelp.setText("Level: Hazardous");
                        pollutionicon.setImageResource(R.drawable.level6);
                    }




                }
            } catch (JSONException e) {
                Toast.makeText(getActivity(), "Error, Check City", Toast.LENGTH_SHORT).show();
            }


        }

    }




    private void checkPermissions(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            },PERMS_CALL_ID );
            return;
        }
    }

    //se déclenche à chaque fois qu'une demande d'activation des permissions est faite
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMS_CALL_ID){
            checkPermissions();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLaunchRunning:
                launchRunningActivity(v);
                break;
        }
    }

    public void launchRunningActivity(View v){
        Intent intent = new Intent(getActivity(), RunningActivity.class);
        startActivity(intent);
    }
}