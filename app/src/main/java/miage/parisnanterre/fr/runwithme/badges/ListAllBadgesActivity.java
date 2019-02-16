package miage.parisnanterre.fr.runwithme.badges;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.database.DatabaseStats;

public class ListAllBadgesActivity extends AppCompatActivity {

    static List<Badge> badges;

    static Button button_record_time;

    final DatabaseStats db = new DatabaseStats(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);
        getSupportActionBar().setTitle("Mes badges");

        badges=db.getAllBadges();
        ListBadgesAdapter adapter = new ListBadgesAdapter(
             this,
             R.layout.badges_list,
             badges
        );
        ListView badgesListView = findViewById(R.id.badges_listView);
        badgesListView.setAdapter(adapter);
    }
}
