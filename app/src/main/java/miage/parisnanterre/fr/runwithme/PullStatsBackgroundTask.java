package miage.parisnanterre.fr.runwithme;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import miage.parisnanterre.fr.runwithme.running.RunningStatistics;

import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.adapter;
import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.button_record_dist;
import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.button_record_speed;
import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.button_record_time;
import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.statistics;


public class PullStatsBackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    JSONObject json;

    PullStatsBackgroundTask(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String select_url = "http://runwithme-france.fr/selectall.php";


        try {
            URL url = new URL(select_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            InputStream iS = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iS, "UTF-8"));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb);
           json = new JSONObject(sb.toString());
            br.close();

            return "";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        //Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();



        JSONArray jsonArray = null;
        int best_duree = 0;
        int best_rythme = 0;
        int best_distance=0;
        try {
            jsonArray = json.getJSONArray("activity");

            for(int i=0;i<jsonArray.length();i++){
                statistics.add(new RunningStatistics(
                        jsonArray.getJSONObject(i).getString("date"),
                        jsonArray.getJSONObject(i).getString("heure"),
                        jsonArray.getJSONObject(i).getString("distance"),
                        jsonArray.getJSONObject(i).getString("duree"),
                        jsonArray.getJSONObject(i).getString("rythme"),
                        jsonArray.getJSONObject(i).getString("calories")));

                if(Integer.parseInt(jsonArray.getJSONObject(i).getString("duree"))>best_duree){
                    best_duree = Integer.parseInt(jsonArray.getJSONObject(i).getString("duree"));
                }
                if(jsonArray.getJSONObject(i).getInt("distance") > best_distance){
                    best_distance = Integer.parseInt(jsonArray.getJSONObject(i).getString("distance"));
                }
                if(jsonArray.getJSONObject(i).getInt("rythme") > best_rythme){
                    best_rythme = Integer.parseInt(jsonArray.getJSONObject(i).getString("rythme"));
                }


            }
            Collections.reverse(statistics);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        button_record_time.setText(best_duree+"min.");
        button_record_dist.setText(best_distance+"km");
        button_record_speed.setText(best_rythme+"km/h");
    }
}


