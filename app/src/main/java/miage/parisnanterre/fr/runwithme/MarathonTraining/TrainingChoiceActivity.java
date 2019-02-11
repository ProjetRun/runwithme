package miage.parisnanterre.fr.runwithme.MarathonTraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import miage.parisnanterre.fr.runwithme.R;

public class TrainingChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_choice);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button button = findViewById(R.id.deb_30min_12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaults("choiceKey", 1, getApplicationContext());
                Intent intent = new Intent(TrainingChoiceActivity.this, MarathonTrainingActivity.class);
                startActivity(intent);
            }
        });




        Button button1 = findViewById(R.id.deb_30min_6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaults("choiceKey", 2, getApplicationContext());
                Intent intent = new Intent(TrainingChoiceActivity.this, MarathonTrainingActivity.class);
                startActivity(intent);
            }
        });


    }
    public static void setDefaults(String key, int value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }
}
