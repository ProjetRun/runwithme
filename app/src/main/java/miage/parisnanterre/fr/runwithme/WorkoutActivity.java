package miage.parisnanterre.fr.runwithme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorkoutActivity extends AppCompatActivity {

    Fragment currentFragment = null;
    FragmentTransaction ft;
    Button b;
    Boolean isBeforeWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        b = (Button) findViewById(R.id.workoutButton);

        ft = getSupportFragmentManager().beginTransaction();
        currentFragment = new BeforeWorkoutFragment();
        ft.replace(R.id.containerWorkout, currentFragment);
        ft.commit();

        isBeforeWorkout = true;
    }

    protected void changeWorkoutFragment(View v){
        if(isBeforeWorkout){
            b.setText("SKIP");
            /*currentFragment = new CurrentWorkoutFragment();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, currentFragment);
            ft.commit();*/
            isBeforeWorkout = false;
        }else {
            b.setText("START");
            /*currentFragment = new BeforeWorkoutFragment();
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, currentFragment);
            ft.commit();*/
            isBeforeWorkout = true;
        }
        //isBeforeWorkout = isBeforeWorkout==true?false:true;
    }
}
