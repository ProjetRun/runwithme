package miage.parisnanterre.fr.runwithme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        statistics = new ArrayList<RunningStatistics>();
        //statistics.add(new RunningStatistics("28/11/2017","15h30","12","15","7,6","8,6"));


        statisticsListView = (ListView) findViewById(R.id.statisticsListView);
        adapter = new RunningStatisticsAdapter(RunningStatisticsActivity.this,statistics);
        statisticsListView.setAdapter(adapter);

        //makeServiceCall("http://runwithme-france.fr/selectall.php");

        PullStatsBackgroundTask load = new PullStatsBackgroundTask(this);
        load.execute();

    }

}
