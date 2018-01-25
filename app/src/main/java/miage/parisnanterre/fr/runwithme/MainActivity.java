package miage.parisnanterre.fr.runwithme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void do_log(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }
    public void do_subscribe(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
