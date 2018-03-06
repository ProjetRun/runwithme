package miage.parisnanterre.fr.runwithme;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class PushStatsBackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;

    PushStatsBackgroundTask(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String ajout_url = "http://runwithme-france.fr/ajout.php";
               String method = params[0];
               if(method.equals("ajout")){

        String date = params[1];
        String heure = params[2];
        String distance = params[3];
        String duree = params[4];
        String rythme = params[5];
        String calories = params[6];

        try {
            URL url = new URL(ajout_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

            String data = URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" +
                    URLEncoder.encode("heure", "UTF-8") + "=" + URLEncoder.encode(heure, "UTF-8") + "&" +
                    URLEncoder.encode("distance", "UTF-8") + "=" + URLEncoder.encode(distance, "UTF-8") + "&" +
                    URLEncoder.encode("duree", "UTF-8") + "=" + URLEncoder.encode(duree, "UTF-8") + "&" +
                    URLEncoder.encode("rythme", "UTF-8") + "=" + URLEncoder.encode(rythme, "UTF-8") + "&" +
                    URLEncoder.encode("calories", "UTF-8") + "=" + URLEncoder.encode(calories, "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            InputStream IS = httpURLConnection.getInputStream();
            IS.close();

            return "Donnes ont ete bien inseré:)";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();

    }
}


