package miage.parisnanterre.fr.runwithme;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    // GPSTracker class
    //GpsTracker gps;
    static User user;
    final DatabaseStats db = new DatabaseStats(this);
    final DatabaseUser dbU = new DatabaseUser(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        user = dbU.getUsers();
        Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();

    }

    public void do_log(View v){
        //Intent intent = new Intent(this, HomeActivity.class);
        //startService(new Intent(this, MyService.class));
        //startActivity(intent);
        //finish();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void do_subscribe(View v){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
        }

    public void do_click_for_stop_tracking(){
        startActivity(new Intent(this,RunningActivity.class));
    }
    public static File getFilePath(String fileName){
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        File folder = new File(extStorageDirectory, "FOLDER_NAME");
        File filePath = new File(folder + "/" + fileName);
        return filePath;
    }
    }
