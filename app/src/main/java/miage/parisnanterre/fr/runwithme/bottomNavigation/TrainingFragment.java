package miage.parisnanterre.fr.runwithme.bottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import miage.parisnanterre.fr.runwithme.MarathonTraining.MarathonTrainingActivity;
import miage.parisnanterre.fr.runwithme.MarathonTraining.TrainingChoiceActivity;
import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.workout.WorkoutActivity;


public class TrainingFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_training, parent, false);
    }

    LinearLayout cardioLayout;
    LinearLayout stretchingLayout;
    LinearLayout hiitLayout;
    LinearLayout marathonLayout;

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        cardioLayout = (LinearLayout) view.findViewById(R.id.lay1);
        stretchingLayout = (LinearLayout) view.findViewById(R.id.lay2);
        hiitLayout = (LinearLayout) view.findViewById(R.id.lay3);
        marathonLayout = view.findViewById(R.id.lay4);

        cardioLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Warmup!",Toast. LENGTH_SHORT ).show();
                //Intent intent = new Intent(getActivity(), WarmActivity.class);
                Intent intent = new Intent(getActivity(), WorkoutActivity.class);
                intent.putExtra("WORKOUT_SESSION", "warmup");
                startActivity(intent);

            }
        });
        stretchingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Stretching!",Toast. LENGTH_SHORT ).show();
                Intent intent = new Intent(getActivity(), WorkoutActivity.class);
                intent.putExtra("WORKOUT_SESSION", "stretching");
                startActivity(intent);
            }
        });
        hiitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WorkoutActivity.class);
                intent.putExtra("WORKOUT_SESSION", "coach");
                startActivity(intent);
            }
        });
        marathonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrainingChoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}