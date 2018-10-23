package miage.parisnanterre.fr.runwithme;

import android.os.Bundle;
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
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    FloatingActionButton fab;
    private ImageButton pause;
    private ImageButton skip;
    private Handler handler = new Handler();
    private Runnable runnable;
    private TextView exo;
    Thread th;
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


        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        //progressBar.setSecondaryProgress(progressBar.getMax()/2);
        textView = (TextView) view.findViewById(R.id.textView);
        exo = (TextView) view.findViewById(R.id.exercice);
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

        th = new Thread(new Runnable() {
            public void run() {
                while (progressStatus < progressBar.getMax()) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"");
                        }
                    });
                    try {
                        // Sleep for 1000 milliseconds.
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


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


    private void changeWorkout(){
        String title = strechingTitle.get(i);
        int image = strechingWorkoutList.get(title);
        img.setImageResource(Integer.parseInt(String.valueOf(image)));
        exo.setText(title);
        progressBar.setProgress(0);
        textView.setText("0");
        th = new Thread(new Runnable() {
            public void run() {
                while (progressStatus < progressBar.getMax()) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"");
                        }
                    });
                    try {
                        // Sleep for 1000 milliseconds.
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        i++;
        if(i==strechingTitle.size()){
            i = 0;//i = i== strechingTitle.size() ? i+1 : 0;
        }
    }
    private void prepareBackTransition(final FABRevealLayout fabRevealLayout) {
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                th.interrupt();
                fabRevealLayout.revealMainView();
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeWorkout();
                fabRevealLayout.revealMainView();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                th.start();
                //
            }
        }, 2000);
    }
}