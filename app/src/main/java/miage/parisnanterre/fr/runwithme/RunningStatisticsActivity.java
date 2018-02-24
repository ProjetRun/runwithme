package miage.parisnanterre.fr.runwithme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RunningStatisticsActivity extends AppCompatActivity {

    private ListView statisticsListView;
    List<RunningStatistics> statistics;
    static RunningStatisticsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        statistics = new ArrayList<RunningStatistics>();
        statistics.add(new RunningStatistics("28/11/2017","15h30","12","15","7,6","8,6"));
        statistics.add(new RunningStatistics("01/01/2018","15h30","12","15","7,6","8,6"));
        statistics.add(new RunningStatistics("02/02/2018","15h30","12","15","7,6","8,6"));

        statisticsListView = (ListView) findViewById(R.id.statisticsListView);
        adapter = new RunningStatisticsAdapter(RunningStatisticsActivity.this,statistics);
        statisticsListView.setAdapter(adapter);

    }
}
