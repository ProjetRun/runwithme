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


        /*

        File sdDir = Environment.getExternalStorageDirectory();
        //File file = new File(sdDir+"myfile");



        File directory = getApplicationContext().getFilesDir();
        File file = new File(directory, "myfile");


        user = new User();
        if( file.exists() == true){

            Toast.makeText(getApplicationContext(), "file true", Toast.LENGTH_SHORT).show();
            System.out.println("file true");
            FileInputStream in = null;
            try {
                in = openFileInput("myfile");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                    if(i==0){
                        user.setId(Integer.parseInt(line));
                    }
                    if(i==1){
                        user.setKm(Integer.parseInt(line));
                    }
                    if(i==2){
                        user.setLevel(Integer.parseInt(line));
                    }

                }

                Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("########### "+sb+" ###############");

        }else{
            Toast.makeText(getApplicationContext(), "file false", Toast.LENGTH_SHORT).show();
            System.out.println("file false");
            String filename = "myfile";
            String fileContents = user.getId()+"\n"+
                                  user.getKm()+"\n"+
                                  user.getLevel()+"\n";
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(fileContents.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } */
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
