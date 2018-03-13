package miage.parisnanterre.fr.runwithme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RunningStatisticsActivity extends AppCompatActivity {

    private ListView statisticsListView;
    static List<RunningStatistics> statistics;
    static RunningStatisticsAdapter adapter;

    static Button button_record_time;
    static Button button_record_dist;
    static Button button_record_speed;
    final DatabaseStats db = new DatabaseStats(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        //statistics = new ArrayList<RunningStatistics>();
        statistics = db.getAllStats();
        statisticsListView = (ListView) findViewById(R.id.statisticsListView);
        adapter = new RunningStatisticsAdapter(RunningStatisticsActivity.this,statistics);
        statisticsListView.setAdapter(adapter);

        button_record_dist = (Button) findViewById(R.id.button_record_dist);
        button_record_speed = (Button) findViewById(R.id.button_record_speed);
        button_record_time = (Button) findViewById(R.id.button_record_time);

        //PullStatsBackgroundTask load = new PullStatsBackgroundTask(this);
        //load.execute();

    }

}
