package miage.parisnanterre.fr.runwithme.workout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.javiersantos.bottomdialogs.BottomDialog;

import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.workout.BeforeWorkoutFragment;

public class WorkoutActivity extends AppCompatActivity {

    Fragment currentFragment = null;
    FragmentTransaction ft;
    Button b;
    Boolean isBeforeWorkout;
    String WORKOUT_SESSION;
    Boolean workoutIsFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        setWORKOUT_SESSION(getIntent().getStringExtra("WORKOUT_SESSION"));

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        b = (Button) findViewById(R.id.workoutButton);
        workoutIsFinish = false;
        ft = getSupportFragmentManager().beginTransaction();
        currentFragment = new BeforeWorkoutFragment();
        ft.replace(R.id.containerWorkout, currentFragment);
        ft.commit();

        isBeforeWorkout = true;



    }

    public void toggleWorkoutFinish(){
        this.workoutIsFinish = !workoutIsFinish;
    }
    public void setWORKOUT_SESSION(String session){
        this.WORKOUT_SESSION = session;
    }
    public String getWORKOUT_SESSION(){
        return this.WORKOUT_SESSION;
    }

    public void closeWorkout(){
        if(workoutIsFinish==false){
            new BottomDialog.Builder(this)
                    .setTitle("are you sure ?")
                    .setContent("You have finished your training soon ...")
                    .setPositiveText("Exit")
                    .setPositiveBackgroundColorResource(R.color.colorPrimary)
                    //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
                    .setPositiveTextColorResource(android.R.color.white)
                    //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
                    .onPositive(new BottomDialog.ButtonCallback() {
                        @Override
                        public void onClick(BottomDialog dialog) {
                            Log.d("BottomDialogs", "Do something!");
                            ImageButton btnStop = (ImageButton) findViewById(R.id.btn_stop);
                            btnStop.performClick();
                            finish();
                        }
                    })
                    //.setCancelable(false)//Dismissing when touching outside
                    .setNegativeText("Cancel")
                    .setNegativeTextColorResource(R.color.colorAccent)
                    .onNegative(new BottomDialog.ButtonCallback() {
                        @Override
                        public void onClick(BottomDialog dialog) {
                            Log.d("BottomDialogs", "Do something!");
                        }
                    }).show();
        }else{
            new BottomDialog.Builder(this)
                    .setTitle("Awesome!")
                    .setContent("The way get started is to quit talking and begin doing. – Walt Disney")
                    .setPositiveText("continue")
                    .setPositiveBackgroundColorResource(R.color.colorPrimary)
                    //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
                    .setPositiveTextColorResource(android.R.color.white)
                    //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
                    .onPositive(new BottomDialog.ButtonCallback() {
                        @Override
                        public void onClick(BottomDialog dialog) {
                            Log.d("BottomDialogs", "Do something!");
                            ImageButton btnStop = (ImageButton) findViewById(R.id.btn_stop);
                            btnStop.performClick();
                            finish();
                        }
                    })
                    .setCancelable(false)//Dismissing when touching outside
                    .show();
        }
    }

    protected void changeWorkoutFragment(View v){
        closeWorkout();
    }
    @Override
    public void onBackPressed() {
        ImageButton btnStop = (ImageButton) findViewById(R.id.btn_stop);
        btnStop.performClick();
        finish();
    }
}
