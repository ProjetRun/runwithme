package miage.parisnanterre.fr.runwithme.sandwich;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

    private Sandwich s;
    private SandwichAdapter adapter;

    public DownloadImagesTask(Sandwich s, SandwichAdapter adapter) {

        this.s = s;
        this.adapter = adapter;

    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(strings[0]);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("Hub","Error getting the image from server : " + e.getMessage().toString());
        }

        return bm;
    }


    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        s.setImageBMP(result);
        adapter.notifyDataSetChanged();
    }

}