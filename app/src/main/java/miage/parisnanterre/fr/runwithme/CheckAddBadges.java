package miage.parisnanterre.fr.runwithme;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;
/*
public class CheckAddBadges extends AppCompatActivity{

    static RunningStatisticsAdapter adapter;
    final DatabaseStats db = new DatabaseStats(this);
    final DatabaseUser dbU = new DatabaseUser(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<RunningStatistics> statistics = db.getAllStats();
        User us = dbU.getUsers();
        adapter = new RunningStatisticsAdapter(this,statistics);
        if(adapter.getCount() != 0){us.addBagde(new Badge(1,"premiere course"));}
        //for(RunningStatistics rs : statistics){
        //}
    }
}*/
class CheckAddBadgesTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;

    public CheckAddBadgesTask (Context context){
        mContext = context;
    }

    static RunningStatisticsAdapter adapter;

    //this.db = new TheDB(getApplicationContext());
    final DatabaseStats db = new DatabaseStats(mContext);
    final DatabaseUser dbU = new DatabaseUser(mContext);


    @Override
    protected Void doInBackground(Void... voids) {
        List<RunningStatistics> statistics = db.getAllStats();
        User us = dbU.getUsers();
        adapter = new RunningStatisticsAdapter(mContext,statistics);
        if(adapter.getCount() != 0){us.addBagde(new Badge(1,"premiere course"));}
        //for(RunningStatistics rs : statistics){
        //}
        return null;
    }


    //other methods like onPreExecute etc.
    protected void onPostExecute(Long result) {
        Toast.makeText(mContext,"premiere course", Toast.LENGTH_LONG).show();
    }

    }