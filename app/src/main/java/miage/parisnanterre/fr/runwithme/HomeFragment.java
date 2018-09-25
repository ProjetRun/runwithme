package miage.parisnanterre.fr.runwithme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_home, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        Button b = (Button) view.findViewById(R.id.buttonLaunchRunning);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchRunningActivity(v);
            }
        });
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLaunchRunning:
                launchRunningActivity(v);
                break;
        }
    }

    public void launchRunningActivity(View v){
        Intent intent = new Intent(getActivity(), RunningActivity.class);
        startActivity(intent);
    }
}