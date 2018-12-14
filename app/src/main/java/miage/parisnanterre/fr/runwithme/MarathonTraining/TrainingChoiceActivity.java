package miage.parisnanterre.fr.runwithme.MarathonTraining;

import android.content.Intent;
import android.os.Bundle;
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
                //Intent intent = new Intent(getActivity(), WorkoutActivity.class);
                //intent.putExtra("WORKOUT_SESSION", "coach");
                //startActivity(intent);
                Intent intent = new Intent(TrainingChoiceActivity.this, MarathonTrainingActivity.class);
                startActivity(intent);
            }
        });
    }

}
