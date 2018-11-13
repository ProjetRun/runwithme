package miage.parisnanterre.fr.runwithme.bottomNavigation;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;
import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.RunningStatisticsActivity;
import miage.parisnanterre.fr.runwithme.badges.Badge;
import miage.parisnanterre.fr.runwithme.database.DatabaseStats;
import miage.parisnanterre.fr.runwithme.database.DatabaseUser;
import miage.parisnanterre.fr.runwithme.running.RunningStatistics;


public class ProfilFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_profil, parent, false);
    }

    private TextView txtProgress;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        txtProgress = (TextView) view.findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= 95) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            txtProgress.setText(pStatus + " %");
                        }
                    });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();


        LineChartView chart = (LineChartView) view.findViewById(R.id.chart);

        List<PointValue> values = new ArrayList<PointValue>();

        PointValue PointValue;

        DatabaseStats db = new DatabaseStats(getContext());
        List<RunningStatistics> statistics;
        statistics = db.getAllStats();
        float distance_max=0;
        values.add(new PointValue(0, 0));
        //

        for(RunningStatistics runningStatistics : statistics){
            //Float distance= Float.parseFloat(runningStatistics.getDistance());

            NumberFormat format = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                format = NumberFormat.getInstance(Locale.FRANCE);
            }
            Number number = null;
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    number = format.parse(runningStatistics.getDistance());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            float distance = number.floatValue();

            if (distance_max < distance)distance_max = distance;
            PointValue = new PointValue(runningStatistics.getId(), distance);
            values.add(PointValue);
        }
//
        //In most cased you can call data model methods in builder-pattern-like manner.
        Line line = new Line(values).setColor(Color.DKGRAY).setCubic(true);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        List<AxisValue> axisValuesForX = new ArrayList<>();
        List<AxisValue> axisValuesForY = new ArrayList<>();
        AxisValue tempAxisValue;

        for (int i = 0; i <= 10; i ++){
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setLabel(i+"");
            axisValuesForX.add(tempAxisValue);
        }

        if(distance_max == 0){
            tempAxisValue = new AxisValue(1);
            tempAxisValue.setLabel(""+1);
            axisValuesForY.add(tempAxisValue);
        }
        else{
            for (int i = 0; i <= distance_max; i += distance_max/10){
                tempAxisValue = new AxisValue(i);
                tempAxisValue.setLabel(""+i);
                axisValuesForY.add(tempAxisValue);
            }
        }

        Axis xAxis = new Axis(axisValuesForX);
        Axis yAxis = new Axis(axisValuesForY);
        data.setAxisXBottom(xAxis);
        data.setAxisYLeft(yAxis);
        chart.setLineChartData(data);

        Button b = (Button) view.findViewById(R.id.buttonLaunchRunningStat);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchRunningStatActivity(v);
            }
        });


    }

    public void launchRunningStatActivity(View v){
        Intent intent = new Intent(getActivity(), RunningStatisticsActivity.class);
        startActivity(intent);
    }
}