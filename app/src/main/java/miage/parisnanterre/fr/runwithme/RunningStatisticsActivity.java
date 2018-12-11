package miage.parisnanterre.fr.runwithme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import miage.parisnanterre.fr.runwithme.badges.Badge;
import miage.parisnanterre.fr.runwithme.badges.ListAllBadgesActivity;
import miage.parisnanterre.fr.runwithme.database.DatabaseSQLite;
import miage.parisnanterre.fr.runwithme.database.DatabaseUser;
import miage.parisnanterre.fr.runwithme.database.User;
import miage.parisnanterre.fr.runwithme.running.RunningStatistics;
import miage.parisnanterre.fr.runwithme.running.RunningStatisticsAdapter;

public class RunningStatisticsActivity extends AppCompatActivity {

    private ListView statisticsListView;
    static List<RunningStatistics> statistics;
    static RunningStatisticsAdapter adapter;

    static Button button_record_time;
    static Button button_record_dist;
    static Button button_record_speed;
    static Button dernier_badge;
    final DatabaseSQLite db = new DatabaseSQLite(this);
    final DatabaseUser dbU = new DatabaseUser(this);

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
        dernier_badge = findViewById(R.id.dernier_badge);
        dernier_badge.setVisibility(View.GONE);

        //PullStatsBackgroundTask load = new PullStatsBackgroundTask(this);
        //load.execute();
        User us = dbU.getUsers();
        db.getAllBadges();
        ImageView img_level = (ImageView) findViewById(R.id.imageView10);

        switch (us.getLevel()){
            case 1:
                img_level.setImageResource(R.mipmap.ic_level_one_foreground);
                break;
            case 2:
                img_level.setImageResource(R.mipmap.ic_level_two_foreground);
                break;
            case 3:
                img_level.setImageResource(R.mipmap.ic_level3_foreground);
                break;
            case 4:
                img_level.setImageResource(R.mipmap.ic_level4_foreground);
                break;
            case 5:
                img_level.setImageResource(R.mipmap.ic_level5_foreground);
                break;
            case 6:
                img_level.setImageResource(R.mipmap.ic_level6_foreground);
                break;
            case 7:
                img_level.setImageResource(R.mipmap.ic_level7_foreground);
                break;
            default:
                img_level.setImageResource(R.mipmap.ic_levelinfinite_foreground);
        }

        ImageView img_next_level = findViewById(R.id.imageView10);
        img_next_level.setImageResource(R.mipmap.ic_level_one_foreground);
        //img_next_level.setVisibility(View.GONE);

        //HashMap hmap = user.getHmap();
        db.getAllBadges();
        for(Badge badge : db.getAllBadges()){
            //hmap.put(hmap.size(),badge);
            dernier_badge.setText(badge.getNom());
            dernier_badge.setVisibility(View.VISIBLE);
        }
        /*
        Set cles = hmap.keySet();
        Iterator it = cles.iterator();

        while (it.hasNext()){
            int cle = (int) it.next();
            Badge badge = (Badge) hmap.get(cle);
            dernier_badge.setText(badge.getNom());
        }*/

        int best_distance = 0;
        int best_rythme = 0;
        int best_temps = 0;
        String best_duree_course = "00:00:00";
        String uniteMesure = "";
        for(RunningStatistics rs : statistics){
            if (Double.parseDouble(rs.getDistance()) > best_distance){
                best_distance = (int) Double.parseDouble(rs.getDistance());
                uniteMesure = rs.getUniteMesure();
            }
            best_rythme = (int)(Double.parseDouble(rs.getRythme()) > best_rythme ? Double.parseDouble(rs.getRythme()) : best_rythme);

            if((Double.parseDouble(rs.getDuree()) > best_temps)){
                best_temps = (int) Double.parseDouble(rs.getDuree());
                best_duree_course = rs.getDureeHeuresMinutesSecondes();
            }
        }
        button_record_time.setText(best_duree_course);
        button_record_speed.setText(best_rythme+"km/h");
        button_record_dist.setText(best_distance+" "+uniteMesure);
        Button b = findViewById(R.id.see_all_badges);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchBadgesActivity(v);
            }
        });
    }
    public void launchBadgesActivity(View v){
        Intent intent = new Intent(this, ListAllBadgesActivity.class);
        startActivity(intent);
    }

}