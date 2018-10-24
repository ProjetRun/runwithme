package miage.parisnanterre.fr.runwithme.workout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import miage.parisnanterre.fr.runwithme.R;

public class BeforeWorkoutFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_before, parent, false);
    }

    int[] imageArray = { R.drawable.highjumps1, R.drawable.highjumps2 };
    ImageView img;
    //private ProgressBar progressBar;
    //private int progressStatus = 0;
    //private TextView textView;

    FloatingActionButton fab;
    private ImageButton pause;
    private ImageButton skip;
    private TextView exo;
    Thread th;
    private boolean run = false;
    int i = 0;
    List<String> strechingTitle = new ArrayList<String>();
    HashMap<String,Integer> strechingWorkoutList = new HashMap<String, Integer>();

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        FABRevealLayout fabRevealLayout = (FABRevealLayout) view.findViewById(R.id.fab_reveal_layout);
        configureFABReveal(fabRevealLayout);
        img = (ImageView) view.findViewById(R.id.game_cover_image);



        exo = (TextView) view.findViewById(R.id.exercice);
        mTextViewCountDown = view.findViewById(R.id.text_view_countdown);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        pause = (ImageButton) view.findViewById(R.id.imageButtonPause);
        skip = (ImageButton) view.findViewById(R.id.imageButtonSkip);
        strechingTitle.add("Hight Knees");
        strechingTitle.add("Standing toe");
        strechingTitle.add("Groin & back");
        strechingTitle.add("Hight Jump");
        strechingWorkoutList.put("Hight Knees",R.drawable.hight_knees);
        strechingWorkoutList.put("Standing toe",R.drawable.standing_toe);
        strechingWorkoutList.put("Groin & back",R.drawable.groin_back);
        strechingWorkoutList.put("Hight Jump",R.drawable.hight_knees);

        updateCountDownText();
    }


    private void configureFABReveal(FABRevealLayout fabRevealLayout) {
        fabRevealLayout.setOnRevealChangeListener(new OnRevealChangeListener() {
            @Override
            public void onMainViewAppeared(FABRevealLayout fabRevealLayout, View mainView) {}

            @Override
            public void onSecondaryViewAppeared(final FABRevealLayout fabRevealLayout, View secondaryView) {
                prepareBackTransition(fabRevealLayout);
            }
        });
    }


    private void changeWorkout(){
        String title = strechingTitle.get(i);
        int image = strechingWorkoutList.get(title);
        img.setImageResource(Integer.parseInt(String.valueOf(image)));
        exo.setText(title);

        i++;
        if(i>strechingTitle.size()){
            i = 0;
        }
    }
    private void prepareBackTransition(final FABRevealLayout fabRevealLayout) {
        /*if (mTimerRunning) {
            pauseTimer();
        } else {*/
            startTimer();
        //}
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                //if (mTimerRunning) {
                    pauseTimer();
                /*} else {
                    startTimer();
                }*/
                fabRevealLayout.revealMainView();
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                resetTimer();
                changeWorkout();
                fabRevealLayout.revealMainView();
            }
        });

    }

    private static final long START_TIME_IN_MILLIS = 30000;

    private TextView mTextViewCountDown;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                skip.performClick();
                mTimerRunning = false;
                resetTimer();
            }
        }.start();

        mTimerRunning = true;
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void resetTimer() {
        pauseTimer();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}