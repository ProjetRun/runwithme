package miage.parisnanterre.fr.runwithme.bottomNavigation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import miage.parisnanterre.fr.runwithme.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().hide();
    }
}
