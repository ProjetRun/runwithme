package miage.parisnanterre.fr.runwithme.sandwich;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import miage.parisnanterre.fr.runwithme.R;

public class SandwichActivity extends AppCompatActivity {



    private SandwichAdapter mSandwichAdapter;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);

        getSupportActionBar().hide();
        Intent intent = getIntent();
        String obj = intent.getStringExtra("obj");

        setupListAdapter(setupListSandwich(obj));
    }

    private List<Sandwich> setupListSandwich(String objectif){
        List<Sandwich> sandwicheList = new ArrayList<>();

        String[] sandwiches;
        switch (objectif){
            case "sain":
                sandwiches = this.getResources().getStringArray(R.array.sain_details);
                break;
            case "perte":
                sandwiches = this.getResources().getStringArray(R.array.perte_details);
                break;

            default:
                sandwiches = this.getResources().getStringArray(R.array.sandwich_details);
                break;
        }

        System.out.println(sandwiches);

        Bitmap defaultBMP = BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.common_google_signin_btn_icon_light_normal_background);

        for (String sandwiche : sandwiches) {
            System.out.println(sandwiche);

            Sandwich sandwich = null;
            try {
                sandwich = JsonUtils.parseSandwichJson(sandwiche);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            sandwich.setImageBMP(defaultBMP);
            sandwicheList.add(sandwich);
        }
        System.out.println(sandwicheList);
        return sandwicheList;
    }

    private void setupListAdapter(List<Sandwich> items) {

        RecyclerView recyclerView = findViewById(R.id.recycler_sandwich_list);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));






        SandwichAdapter adapter = new SandwichAdapter(this, items);
        //adapter.setClickListener((SandwichAdapter.ItemClickListener) this);
        //recyclerView.setAdapter(adapter);

        recyclerView.setAdapter(adapter);

        downloadIMG(items,adapter);


    }

    private void downloadIMG(List<Sandwich> items,SandwichAdapter adapter){
        ThreadFactoryIMG sThreadFactory = new ThreadFactoryIMG();
        //handler
        //HandlerDownload h = new HandlerDownload(films,adapter,this);
        HandlerThread handlerThread = new HandlerThread("handler");
        handlerThread.start();

        Looper looper = handlerThread.getLooper();


        Handler handler = new Handler(looper);
        handler.post(new HandlerDownload(items,adapter,this));
        Executor THREAD_POOL_EXECUTOR
                = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);

        for (Sandwich f : items) {
            THREAD_POOL_EXECUTOR.execute(new HandlerDownload(items,adapter,this));
        }
    }




}
