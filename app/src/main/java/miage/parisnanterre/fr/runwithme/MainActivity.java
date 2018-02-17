package miage.parisnanterre.fr.runwithme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // GPSTracker class
    //GpsTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void do_log(View v){
        //Intent intent = new Intent(this, HomeActivity.class);
        //startService(new Intent(this, MyService.class));
        //startActivity(intent);
        //finish();
    }

    public void do_subscribe(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
        }
    }
