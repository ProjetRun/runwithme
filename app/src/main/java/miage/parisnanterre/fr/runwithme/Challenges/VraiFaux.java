package miage.parisnanterre.fr.runwithme.Challenges;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import miage.parisnanterre.fr.runwithme.R;

public class VraiFaux extends AppCompatActivity {

    private List<Questions> catalogue;
    private Questions item;
    private Random randomGenerator;
    private int countdown;
    private int score;

    private TextView t_question;
    private ImageView imGIF;

    private static final long START_TIME_IN_MILLIS = 11000;

    private TextView mTextViewCountDown;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrai_faux);

        getSupportActionBar().hide();


        this.countdown = 10;
        this.score = 0;
        catalogue = new ArrayList<Questions>();
        randomGenerator = new Random();
        t_question = (TextView) findViewById(R.id.text_question);
        imGIF = (ImageView) findViewById(R.id.loadingImageView);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);






        insertQuestions();
        changeItem();
        updateCountDownText();
        startTimer();
    }

    private void insertQuestions(){
        catalogue.add(new Questions("Tout le monde peut pratiquer le running",true, "https://media.giphy.com/media/krM6ANSNvFg52/giphy.gif"));
        catalogue.add(new Questions("Courir permet de travailler tous les muscles du corps",false, "https://media.giphy.com/media/yHkyIAfczco6s/giphy.gif"));
        catalogue.add(new Questions("Il faut courir au minimum trois fois par semaine",false, "https://media.giphy.com/media/O5ibw2aqNIfAs/giphy.gif"));
        catalogue.add(new Questions("Courir trente minutes est suffisant",false,"https://media.giphy.com/media/TtAo9puKRyBVu/giphy.gif"));
        catalogue.add(new Questions("Pour maigrir, il faut courir le matin à jeun",true, "https://media.giphy.com/media/QTvYcj77RjkQ/giphy.gif"));
        catalogue.add(new Questions("La course peut être nocive pour les articulations",true, "https://media.giphy.com/media/rsZXfWjafmRa0/giphy.gif"));
        catalogue.add(new Questions("IL FAUT MANGER UNIQUEMENT DES GLUCIDES AVANT UNE LONGUE ÉPREUVE", false, "https://media.giphy.com/media/gzE15pfW1jxkI/giphy.gif"));
        catalogue.add(new Questions("ON NE PEUT PAS COURIR QUAND ON N'A PAS DE SOUFFLE", false, "https://media.giphy.com/media/l2JeaS9QDFFL2PMAw/giphy.gif"));
        catalogue.add(new Questions("MIEUX VAUT BOIRE PEU ET RÉGULIÈREMENT QUE BEAUCOUP D'UN COUP EN COURANT", true, "https://media.giphy.com/media/bDL3BsB4ViI2k/giphy.gif"));
        catalogue.add(new Questions("APRÈS UN ENTRAÎNEMENT OU UNE COURSE, ON PEUT MANGER CE QU'ON VEUT SANS PRENDRE DE POIDS", true, "https://media.giphy.com/media/dnrlK0zmXsIHm/giphy.gif"));
        catalogue.add(new Questions("Si on a un point de côté, on continue.", false, "https://media.giphy.com/media/3o7TKxKvPeSlXhrKSs/giphy.gif"));
        catalogue.add(new Questions("Plus on court vite, plus on perd du poids", false, "https://media.giphy.com/media/l2Sqc3POpzkj5r8SQ/giphy.gif"));
        catalogue.add(new Questions("En running, bien dormir c’est vital", true, "https://media.giphy.com/media/l3M7smiKhkOcw/giphy.gif"));
        catalogue.add(new Questions("C’est en fin nuit que l’on récupère le mieux", false, "https://media.giphy.com/media/fbjHU0pE1QhJC/giphy.gif"));
        catalogue.add(new Questions("Pendant les pauses entre chaque entraînement ou après leur arrêt, le muscle se transforme en graisse", false, "https://media.giphy.com/media/kMSyCATSq9SEw/giphy.gif"));
        catalogue.add(new Questions("Je dois consommer beaucoup de sucres lents (pâtes, riz…) quand je fais du sport ", true, "https://media.giphy.com/media/vKwAAw1AM2mkw/giphy.gif"));
        catalogue.add(new Questions("Le muscle pèse plus lourd que la graisse", false, "https://media.giphy.com/media/wOjQ7aKWQ4vBK/giphy.gif"));
        catalogue.add(new Questions("Le sport est efficace contre la cellulite", false, "https://media.giphy.com/media/nbpCCyy3q7fKo/giphy.gif"));
        catalogue.add(new Questions("Enceinte, j’arrête le sport !", false, "https://media.giphy.com/media/82lhG1Tm9QSvS/giphy.gif"));
        catalogue.add(new Questions("Les aliments biologique, naturels, sans gluten sont meilleurs pour la perte de poids", false, "https://media.giphy.com/media/xUPGcuomRFMUcsB9nO/giphy.gif"));
        catalogue.add(new Questions("Faire des abdos pour voir ses abdominaux", false, "https://media.giphy.com/media/xWBxhW4Xjjw5nmjXL8/giphy.gif"));
        catalogue.add(new Questions("Manger des fruits pour être en bonne santé", true, "https://media.giphy.com/media/Pqr5ddlDQqI8g/giphy.gif"));

    }

    public Questions anyItem() {
        resetTimer();
        if(0>=this.countdown){


            pauseTimer();
            resetTimer();


            new BottomDialog.Builder(this)
                    .setTitle("Score!")
                    .setContent(score+"/10")
                    .setPositiveText("continue")
                    .setPositiveBackgroundColorResource(R.color.colorPrimary)
                    //.setPositiveBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary)
                    .setPositiveTextColorResource(android.R.color.white)
                    //.setPositiveTextColor(ContextCompat.getColor(this, android.R.color.colorPrimary)
                    .onPositive(new BottomDialog.ButtonCallback() {
                        @Override
                        public void onClick(BottomDialog dialog) {
                            Log.d("BottomDialogs", "Do something!");
                            finish();
                        }
                    })
                    .show();


        }else{
            int index = randomGenerator.nextInt(catalogue.size());
            item = catalogue.get(index);
            if(item.getAppeared()){
                return anyItem();
            }else{
                item.setAppeared(true);
                this.countdown--;

                return item;
            }

        }
        return item;
    }


    public void changeItem(){
        item = anyItem();
        Glide.with(this)
                .asGif()
                .load(item.getUrl())
                .into(imGIF);
        t_question.setText(item.getQuestion());
    }

    public void checkresult(boolean x){
        String y = x==true ? "vrai" : "faux";
        if(x == item.getReponse()){
            FancyToast.makeText(this,"true !",25,FancyToast.SUCCESS,false).show();
            score++;
        }else{
            FancyToast.makeText(this,"false !",25, FancyToast.ERROR,false).show();
        }
    }
    public void click_true(View view) {
        checkresult(true);
        changeItem();
        reloadTimer();
    }
    public void click_false(View view) {
        checkresult(false);
        changeItem();
        reloadTimer();
    }

    private void reloadTimer(){
        pauseTimer();
        resetTimer();
        startTimer();
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                resetTimer();
                if(item.getReponse() == true){
                    checkresult(false);
                }else{
                    checkresult(true);
                }
                changeItem();
                startTimer();
            }
        }.start();

        mTimerRunning = true;
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void resetTimer() {
        pauseTimer();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }


    @Override
    public void onBackPressed() {
       pauseTimer();
       finish();
    }
}
