package miage.parisnanterre.fr.runwithme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RunningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        getSupportActionBar().hide();
    }
}
