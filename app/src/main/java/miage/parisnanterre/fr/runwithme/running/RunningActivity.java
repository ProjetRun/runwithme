package miage.parisnanterre.fr.runwithme.running;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.NumberFormat;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import miage.parisnanterre.fr.runwithme.MarathonTraining.Seance;
import miage.parisnanterre.fr.runwithme.badges.Badge;
import miage.parisnanterre.fr.runwithme.bottomNavigation.HomeFragment;
import miage.parisnanterre.fr.runwithme.database.DatabaseStats;
import miage.parisnanterre.fr.runwithme.database.DatabaseUser;
import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.database.User;

public class RunningActivity extends AppCompatActivity {

    Button button_rythme;
    Button button_distance;
    Button button_distance_titre;
    Button button_cal;
    TextView txtv_pogress;
    Button txtv_badge;
    LocationManager  locationManager;
    boolean first_call = true;
    boolean is_km = false;

    Location current_location;
    Location last_location;
    double distance;
    java.text.DecimalFormat df;
    java.text.DecimalFormat df2;
    Chronometer simpleChronometer;
    ImageView play_and_stop;
    User user = new User();

    double latitude;
    double longitude;
    boolean isLaunch;
    String date, heure, distancee,duree,rythme, calories;
    private BroadcastReceiver broadcastReceiver;

    final DatabaseStats db = new DatabaseStats(this);
    //ca peut pas fonctionner ça, on peut invoquer que une seule classe qui etend openclasshelper
    //ca peut pas fonctionner ça, on peut invoquer que une seule classe qui etend openclasshelper
    final DatabaseUser dbU = new DatabaseUser(this);
    static RunningStatisticsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        getSupportActionBar().hide();

        button_distance= findViewById(R.id.button_distance_display);
        button_distance_titre = findViewById(R.id.button_distance);
        button_rythme= findViewById(R.id.button_pace_display);
        button_cal = findViewById(R.id.button_cal_display);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        current_location = new Location("current_location");
        current_location.setLatitude(40);
        current_location.setLongitude(12);
        last_location = new Location("last_location");
        distance = 0.0;
        isLaunch = false;
        df = new java.text.DecimalFormat("0.#");
        df2 =new java.text.DecimalFormat("0.##");
        simpleChronometer = findViewById(R.id.textView_MIN_DISPLAY);

        play_and_stop = findViewById(R.id.imageView_start_and_stop);
        currentContext = this;
    }

    public double meterDistanceBetweenPoints(float lat_a, float lng_a, float lat_b, float lng_b) {
        float pk = (float) (180.f/Math.PI);

        float a1 = lat_a / pk;
        float a2 = lng_a / pk;
        float b1 = lat_b / pk;
        float b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        if(Double.isNaN(tt)){
            tt = 0;
            Toast.makeText(getBaseContext(),
                    "distance = NAN --> tt = " + 6366000 * tt + "metres",
                    Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getBaseContext(),
                    "distance = " + 6366000 * tt + "metres",
                    Toast.LENGTH_SHORT).show();

        return 6366000 * tt;
    }

    private void calculateAndDisplaySpeed(){
        double meter = distance *1000;
        double kilometre = meter / 1000;
        long elapsedMillis = SystemClock.elapsedRealtime() - simpleChronometer.getBase();
        double second = elapsedMillis/1000;
        double heures = second/3600;
        button_rythme.setText(""+df.format(kilometre/heures));
    }


    public void do_click_tracking(View v) {
        //finish();
        if(isLaunch == false) {
            do_click_for_play_tracking();
        }else{
            do_click_for_stop_tracking();
        }
    }

    public void do_click_for_play_tracking(){

        isLaunch = true;
        play_and_stop.setImageResource(R.mipmap.ic_stop_foreground);
        //play_and_stop.setBackgroundColor(Color.rgb(219, 45, 45));

        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.start(); // start a chronometer
        //configureLocation();
        if(broadcastReceiver == null){
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    if(first_call==true){
                        latitude = (double) intent.getExtras().get("lati");
                        longitude = (double) intent.getExtras().get("longi");
                        current_location.setLatitude(latitude);
                        current_location.setLongitude(longitude);
                        Toast.makeText(getBaseContext(),
                                "premières coordonnées " + latitude + longitude ,
                                Toast.LENGTH_SHORT).show();
                        first_call=false;
                    }
                    else{
                        last_location.setLatitude(current_location.getLatitude());
                        last_location.setLongitude(current_location.getLongitude());

                        latitude = (double) intent.getExtras().get("lati");
                        longitude = (double) intent.getExtras().get("longi");

                        current_location.setLatitude(latitude);
                        current_location.setLongitude(longitude);

                        distance += meterDistanceBetweenPoints((float) current_location.getLatitude(), (float) current_location.getLongitude(), (float) last_location.getLatitude(),(float) last_location.getLongitude())/1000;
                        if(distance*1000 > 1000){
                            button_distance_titre.setText("Distance(km)");
                            button_distance.setText(""+df2.format(distance));
                            is_km = true;
                        }else{
                            button_distance.setText(""+df.format(distance*1000));
                        }
                        calculateAndDisplaySpeed();
                        calcul_kcak();
                    }
                }
            };
        }
        this.registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
        sendNotification();
    }

    boolean in_bmBottomSheetDialog=false;

    public void do_click_for_stop_tracking(){
        in_bmBottomSheetDialog = true;
        List<String> citations = new ArrayList<String>();
        citations.add("Si tu n’as pas confiance, tu trouveras toujours une manière de perdre. - Carl Lewis");
        citations.add("Nous avons tous des rêves, mais pour les réaliser, il faut beaucoup de détermination, de dévouement, de discipline et d’efforts - Jesse Owens");
        citations.add("La course est la plus grande métaphore de la vie, parce que vous en tirez ce dont vous en mettez. - Oprah Winfrey");
        citations.add("Le miracle n'est pas que j'ai terminé. Le miracle est que j'ai eu le courage de commencer. - John Bingham");
        citations.add("La motivation vous sert de départ. L’habitude vous fait continuer - Jim Ryun ");
        //(int) (Math.random() * (monArrayList.size() - 1));

        simpleChronometer.stop();

        //double nbKilometre = Double.parseDouble(button_distance.getText().toString());

        NumberFormat format = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            format = NumberFormat.getInstance(Locale.FRANCE);
        }
        Number number = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            try {
                number = format.parse(button_distance.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        double nbKilometre = number.doubleValue();

        //String nb = button_distance.getText().toString();
        //println(nb);

        if(is_km==false){
            nbKilometre=0.0;
        }
        user.updateKm(nbKilometre);
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);

        View sheetView = this.getLayoutInflater().inflate(R.layout.popup_after_tracking, null);

        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
        mBottomSheetDialog.setCancelable(false);

        //ajouter données du footing dans la bd
        mNotificationManager.cancelAll();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println( sdf.format(cal.getTime()) );
        date = sdf.format(cal.getTime());
        cal = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm:ss");
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        sdf.setTimeZone(gmt);
        System.out.println( sdf.format(cal.getTime()) );
        heure =sdf.format(cal.getTime());
        distancee = button_distance.getText().toString();
        int elapsedMillis = (int)((SystemClock.elapsedRealtime()-simpleChronometer.getBase()));
        duree = Integer.toString(elapsedMillis);
        rythme = button_rythme.getText().toString();
        calories = button_cal.getText().toString();
        RunningStatistics pushStats = new RunningStatistics();
        pushStats.setDate(date);
        pushStats.setHeure(heure);
        pushStats.setDistance(distancee);
        String uniteMesure = button_distance_titre.getText().toString();
        if(uniteMesure.equals("Distance(km)"))
            pushStats.setUniteMesure("km");
        else
            pushStats.setUniteMesure("m");
        pushStats.setDuree(duree);
        int minutes = transformInMinutes(duree);
        checkSeance(minutes);
        pushStats.setRythme(rythme);
        pushStats.setCalories(calories);
        db.addStats(pushStats);
        //dbU.update(user);

        //add badges if conditions
        //CheckAddBadgesTask checkAddBadges = new CheckAddBadgesTask(this);
        //checkAddBadges.onPostExecute();
        //checkAddBadges.execute();
        //boucle for sur checkAddBadges.badgesajoutés pour les ajouter au layout
        List<RunningStatistics> statistics = db.getAllStats();
        List<Badge> badges = new ArrayList<>();//liste destiné à contenir les nouveaux badges
        adapter = new RunningStatisticsAdapter(this,statistics);
        DatabaseStats db = new DatabaseStats(this);

        //on stock les badges uniquement gagnés dans hmap
        HashMap hmap = user.getHmap();

        if(adapter.getCount() == 1){
            Badge badge1 = new Badge(1,"first run");
            db.addBadge(badge1);
            badges.add(badge1);
            db.close();
        }
        if(adapter.getCount() == 2){
            Badge badge2 = new Badge(2,"X2 RUN");
            db.addBadge(badge2);
            badges.add(badge2);
            db.close();
        }
        if(adapter.getCount() == 3){
            Badge badge2 = new Badge(3,"X3 RUN");
            db.addBadge(badge2);
            badges.add(badge2);
            db.close();
        }
        //boutons de la popup de fin de course
        txtv_pogress = mBottomSheetDialog.getWindow().findViewById(R.id.textView7);
        txtv_badge = mBottomSheetDialog.getWindow().findViewById(R.id.button_badge);
        //txtv_badge.setVisibility(View.GONE);//on masque le bouton "badge" par défaut si l'utilisateur n'en gagne pas pendant la course

        for(Badge badge : badges){
            txtv_badge.setVisibility(View.VISIBLE);
            txtv_badge.setBackgroundResource(R.mipmap.badge_orange);
            txtv_badge.setText(badge.getNom());
        }

        //u.updateKm(Integer.parseInt(button_distance.getText().toString()));
        txtv_pogress.setText(user.getKm()+"/"+user.getkmNextLevel()+"km");
        ProgressBar progressBar4 = (ProgressBar) mBottomSheetDialog.getWindow().findViewById(R.id.progressBar4);
        //progressBar4.setMax(u.getkmNextLevel());
        progressBar4.setMax(user.getkmNextLevel());
        //progressBar4.setProgress(12);
        progressBar4.setProgress((int)user.getKm());

        TextView txtv_cita = (TextView) mBottomSheetDialog.getWindow().findViewById(R.id.textView5);
        txtv_cita.setText(citations.get((int) (Math.random() * (citations.size() - 1))));

        ImageView img_level = (ImageView)  mBottomSheetDialog.getWindow().findViewById(R.id.imageView6);

        ImageView img_next_level = (ImageView)  mBottomSheetDialog.getWindow().findViewById(R.id.imageView9);

        switch (user.getLevel()){
            case 1:
                img_level.setImageResource(R.mipmap.ic_level_one_foreground);
                img_next_level.setImageResource(R.mipmap.ic_level_two_foreground);
                break;
            case 2:
                img_level.setImageResource(R.mipmap.ic_level_two_foreground);
                img_next_level.setImageResource(R.mipmap.ic_level3_foreground);
                break;
            case 3:
                img_level.setImageResource(R.mipmap.ic_level3_foreground);
                img_next_level.setImageResource(R.mipmap.ic_level4_foreground);
                break;
            case 4:
                img_level.setImageResource(R.mipmap.ic_level4_foreground);
                img_next_level.setImageResource(R.mipmap.ic_level5_foreground);
                break;
            case 5:
                img_level.setImageResource(R.mipmap.ic_level5_foreground);
                img_next_level.setImageResource(R.mipmap.ic_level6_foreground);
                break;
            case 6:
                img_level.setImageResource(R.mipmap.ic_level6_foreground);
                img_next_level.setImageResource(R.mipmap.ic_level7_foreground);
                break;
            case 7:
                img_level.setImageResource(R.mipmap.ic_level7_foreground);
                img_next_level.setImageResource(R.mipmap.ic_levelinfinite_foreground);
                break;
            default:
                img_level.setImageResource(R.mipmap.ic_levelinfinite_foreground);
                img_next_level.setImageResource(R.mipmap.ic_levelinfinite_foreground);
        }
    }

    public void do_click_for_share(View v){
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.popup_share, null);
        mBottomSheetDialog.setContentView(sheetView);
        mBottomSheetDialog.show();
    }

    public int transformInMinutes(String duree){
        int milliseconds = Integer.parseInt(this.duree);
        int seconds = (milliseconds / 1000) % 60 ;
        int minutes = ((milliseconds / (1000*60)) % 60);
        int hours   = ((milliseconds / (1000*60*60)) % 24);
        int result = 1 + minutes + hours * 60;
        Log.w("checkSeance", "result = nb de minutes parcouru" + result);
        return result;
    }

    public void checkSeance (int duree){
        // validation automatique de la séance si la duree de l'activité est égale ou spérieur à celle de la séance
        ArrayList<Seance> seances = db.getSeanceList1();
        Seance seance = nextSeance(seances);
        Log.w("checkSeance", "nombre de minutes de la seance" +seance.getNumSemaine()+ "    "+ seance.getMinutes());
        if(seance.getMinutes()>=0){
            seance.setChecked(true);
        }
        db.insertSeanceChecked(seance);
    }

    public Seance nextSeance(ArrayList<Seance> seances){
        Iterator<Seance> it = seances.iterator();
        Seance seance2 = new Seance();
        while (it.hasNext()) {
            Seance seance = it.next();
            if(seance.isChecked() == true){
                seance2 = seance;
                break;
            }
        }
        return seance2;
    }


    public void share_on_facebook(View v){
        //partage avec Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        String uniteMesure;
        if(button_distance_titre.getText().toString().equals("Distance(km)"))
            uniteMesure = "km";
        else
            uniteMesure = "m";
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                .setQuote("J'ai couru "+distancee+" "+uniteMesure+" en "+getDureeHeuresMinutesSecondes(duree)+" minutes avec RunWithMe")
                .setContentUrl(Uri.parse("https://drive.google.com/file/d/1JEbLvL43ErdNKTGSoEs-6Sof3TUBQ_56/view?usp=sharing"))
                .build();
        ShareDialog.show(this,shareLinkContent);
    }

    public void share_on_twitter(View v){
        //patage avec toutes les apps du téléphone
        Intent sendIntent = new Intent();
        String uniteMesure;
        if(button_distance_titre.getText().toString().equals("Distance(km)"))
            uniteMesure = "km";
        else
            uniteMesure = "m";
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "J'ai couru "+distancee+" "+uniteMesure+" en "+getDureeHeuresMinutesSecondes(duree)+" minutes avec l'application RunWithMe !");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "I just read ");
        sendIntent.setType("*/*");
        startActivity(Intent.createChooser(sendIntent, "Share Your Favorite Article"));
    }

    @Override
    public void onBackPressed() {

        if(isLaunch == true & in_bmBottomSheetDialog==false){
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                //super.onBackPressed();
                new AlertDialog.Builder(this)
                        .setTitle("Really Exit?")
                        .setMessage("Are you sure you want to exit?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                //HomeActivity.super.onBackPressed();
                                finish();
                            }
                        }).create().show();
            }
        }else{
            finish();
        }
    }

    public void writeFileUser(){
        String filename = "myfile";
        String fileContents = user.getId()+"\n"+
                user.getKm()+"\n"+
                user.getLevel()+"\n";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void back_to_homeactivity(View v){
        writeFileUser();
        finish();
    }

    public void calcul_kcak(){
        //Nombre de kcal dépensées = votre Poids (kg) x Distance (km)
        //distance parcourue x poid x 1.036
        button_cal.setText(""+df.format(distance*70*1.036));
    }

    public void sendNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

        mBuilder.setOngoing(true);

        Intent notificationIntent = new Intent(currentContext, RunningActivity.currentContext.getClass() );
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationIntent.setAction("android.intent.action.MAIN");
        notificationIntent.addCategory("android.intent.category.LAUNCHER");
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        //notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);

        mBuilder.setSmallIcon(R.mipmap.ic_user);
        mBuilder.setContentTitle("Durée de la session");
        //mBuilder.setContentText("00:00");
        mBuilder.setUsesChronometer(true);
        mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());




    }

    public static Context currentContext;
    NotificationManager mNotificationManager;

    public String getDureeHeuresMinutesSecondes(String duree){
        int milliseconds = Integer.parseInt(duree);
        int seconds = (milliseconds / 1000) % 60 ;
        int minutes = ((milliseconds / (1000*60)) % 60);
        int hours   = ((milliseconds / (1000*60*60)) % 24);
        String result = String.valueOf(hours) + ':' + String.valueOf(minutes) + ':' + String.valueOf(seconds);
        return result;
    }

    @Override
    public void onPause() {
        super.onPause();

        //Unregister your activity to listen for change send by the service
        //getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null){
            this.unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}
