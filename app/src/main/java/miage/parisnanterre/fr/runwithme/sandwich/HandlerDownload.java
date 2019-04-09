package miage.parisnanterre.fr.runwithme.sandwich;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.lang.ref.WeakReference;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class HandlerDownload  implements Runnable {
    List<Sandwich> sandwiches;
    WeakReference <SandwichAdapter> adapter;
    WeakReference<Activity> a;

    public HandlerDownload(List<Sandwich> sandwiches, SandwichAdapter adapter, Activity a) {
        this.sandwiches = sandwiches;
        this.adapter = new WeakReference <SandwichAdapter>(adapter);
        this.a = new WeakReference <Activity>(a);
        // doStuff();
    }

    @Override
    public void run() {
        for (Sandwich s : sandwiches) {

            Bitmap bm = null;
            try {
                URL aURL = new URL(s.getImage());
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("Hub", "Error getting the image from server : " + e.getMessage().toString());
            }
            s.setImageBMP(bm);

            Activity activity = null;
            if(a.get() != null){
                activity = a.get();
            }
            SandwichAdapter sandwichAdapter = null;
            if(adapter.get()!=null){
                sandwichAdapter=adapter.get();
            }

            final  SandwichAdapter finalSandwichAdapter = sandwichAdapter;

            activity.runOnUiThread(new Runnable() {
                public void run() {
                    finalSandwichAdapter.notifyDataSetChanged();
                }
            });

        }
    }
}
