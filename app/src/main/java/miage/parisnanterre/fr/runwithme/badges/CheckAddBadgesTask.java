package miage.parisnanterre.fr.runwithme.badges;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import miage.parisnanterre.fr.runwithme.database.DatabaseSQLite;
import miage.parisnanterre.fr.runwithme.running.RunningStatistics;
import miage.parisnanterre.fr.runwithme.running.RunningStatisticsAdapter;

public class CheckAddBadgesTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;

    public CheckAddBadgesTask (Context context){
        mContext = context;
    }

    static RunningStatisticsAdapter adapter;
    private SQLiteDatabase bdd;

    @Override
    protected Void doInBackground(Void... voids) {
        DatabaseSQLite db = new DatabaseSQLite(mContext);
        List<RunningStatistics> statistics = db.getAllStats();
        adapter = new RunningStatisticsAdapter(mContext,statistics);

        if(adapter.getCount() == 1){
            //user.addBagde(new Badge(1,"Badge premiere course"));

            DatabaseSQLite dbBadge = new DatabaseSQLite(mContext);
            dbBadge.addBadge(new Badge(1,"Badge premiere course"));
        }
        //for(RunningStatistics rs : statistics){
        //}
        return null;
    }

    //other methods like onPreExecute etc.
    protected void onPostExecute() {
        Toast.makeText(mContext,"premiere course", Toast.LENGTH_LONG).show();
    }

}