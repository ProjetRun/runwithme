package miage.parisnanterre.fr.runwithme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import miage.parisnanterre.fr.runwithme.bottomNavigation.HomeFragment;
import miage.parisnanterre.fr.runwithme.database.DatabaseStats;
import miage.parisnanterre.fr.runwithme.database.DatabaseUser;
import miage.parisnanterre.fr.runwithme.database.User;
import miage.parisnanterre.fr.runwithme.tab.DashboardActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        user = dbU.getUsers();
        //Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();

    }

    public void do_log(View v){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void do_subscribe(View v){
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
        finish();
    }


}
