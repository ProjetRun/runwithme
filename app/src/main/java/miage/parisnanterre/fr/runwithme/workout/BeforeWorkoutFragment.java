package miage.parisnanterre.fr.runwithme.workout;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.truizlop.fabreveallayout.FABRevealLayout;
import com.truizlop.fabreveallayout.OnRevealChangeListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
    Random random = new Random();
    List<String> keys;
    StepperIndicator indicator;
    int currentStep = 0;

    private String session;

    //private TextView xxx;
    //private Button xxx;
    private TextView streamTXT;
    private ImageButton xxx;

    PlayerView playerView;
    static SimpleExoPlayer player;
    private ImageButton btnStop;
    final String audioUrl = "http://185.52.127.159/fr/30007/mp3_128.mp3?origine=fluxradios";
    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        FABRevealLayout fabRevealLayout = (FABRevealLayout) view.findViewById(R.id.fab_reveal_layout);
        configureFABReveal(fabRevealLayout);
        img = (ImageView) view.findViewById(R.id.game_cover_image);

        playerView = (PlayerView) view.findViewById(R.id.video_view);


        indicator = (StepperIndicator) view.findViewById(R.id.indicator);
        //indicator.setCurrentStep(2);
        exo = (TextView) view.findViewById(R.id.exercice);
        mTextViewCountDown = view.findViewById(R.id.text_view_countdown);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        pause = (ImageButton) view.findViewById(R.id.imageButtonPause);
        skip = (ImageButton) view.findViewById(R.id.imageButtonSkip);
        //xxx = (TextView) view.findViewById(R.id.xxx);
        streamTXT = (TextView) view.findViewById(R.id.streamTXT);
        xxx = (ImageButton) view.findViewById(R.id.btn_play);
        btnStop = (ImageButton) view.findViewById(R.id.btn_stop);



        session = ((WorkoutActivity)getActivity()).getWORKOUT_SESSION();

        insertWorkout();
        changeWorkout();

        updateCountDownText();
    }


    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        Uri uri = Uri.parse(audioUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }


    @Override
    public void onResume() {
        super.onResume();

        initializePlayer();

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setPlayWhenReady(false);
                initializePlayer();
            }
        });
        xxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.setPlayWhenReady(true);
                xxx.setEnabled(false);
            }
        });
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


    private  void insertStretching(){
        //streching
        strechingWorkoutList.put("Hight Knees",R.drawable.hight_knees);
        strechingWorkoutList.put("Standing toe",R.drawable.standing_toe);
        strechingWorkoutList.put("Groin & back",R.drawable.groin_back);
        strechingWorkoutList.put("Hight Jump",R.drawable.hight_jump);
        strechingWorkoutList.put("Calf",R.drawable.calf);
        strechingWorkoutList.put("Dynamic chest",R.drawable.dynamic_chest);
        strechingWorkoutList.put("Site to side lunge",R.drawable.site_to_side_lunge);
        strechingWorkoutList.put("Toe tap hops",R.drawable.toe_tap_hops);
    }
    private  void insertWarmUp(){
        //warmup
        strechingWorkoutList.put("Alt back expensions",R.drawable.alt_back_expensions);
        strechingWorkoutList.put("Arm circles",R.drawable.arm_circles);
        strechingWorkoutList.put("arm circles wide",R.drawable.arm_circles_wide);
        strechingWorkoutList.put("Chest expensions",R.drawable.chest_expensions);
        strechingWorkoutList.put("Hip rotation",R.drawable.hip_rotation);
        strechingWorkoutList.put("Hop on the spot",R.drawable.hop_on_the_spot);
        strechingWorkoutList.put("Side to side hop",R.drawable.side_to_side_hop);
        strechingWorkoutList.put("Torso rotation",R.drawable.torso_rotation);
    }
    private  void insertCoach(){
        //coach
        strechingWorkoutList.put("Climber",R.drawable.climber);
        strechingWorkoutList.put("Jumping jack",R.drawable.jumping_jack);
        strechingWorkoutList.put("Lung step up",R.drawable.lunge_step_up);
        strechingWorkoutList.put("Plank",R.drawable.plank);
        strechingWorkoutList.put("Plank jacks",R.drawable.plank_jacks);
        strechingWorkoutList.put("Squat",R.drawable.squat);
    }
    private void insertWorkout(){
        switch (session){
            case "stretching": insertStretching();break;
            case "warmup": insertWarmUp();break;
            case "coach" :
                indicator.setStepCount(10);
                insertCoach();
                break;
            default:
                insertStretching();
                insertWarmUp();
                insertCoach();
        }
        keys = new ArrayList<String>(strechingWorkoutList.keySet());
    }
    private void changeWorkout(){

        String randomKey = keys.get( random.nextInt(keys.size()) );
        int value = strechingWorkoutList.get(randomKey);
        if(randomKey == exo.getText()){
            changeWorkout();
        }else{
            img.setImageResource(Integer.parseInt(String.valueOf(value)));
            exo.setText(randomKey);
        }
    }
    private void selectWorkout(){
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
                currentStep = indicator.getStepCount() != currentStep ? currentStep+1 : currentStep;
                indicator.setCurrentStep(currentStep);
                if(currentStep == indicator.getStepCount()){
                    ((WorkoutActivity)getActivity()).toggleWorkoutFinish();
                    ((WorkoutActivity)getActivity()).closeWorkout();
                }
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