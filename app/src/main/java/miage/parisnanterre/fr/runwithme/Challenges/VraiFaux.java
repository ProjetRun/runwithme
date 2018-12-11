package miage.parisnanterre.fr.runwithme.Challenges;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import miage.parisnanterre.fr.runwithme.R;

public class VraiFaux extends AppCompatActivity {

    private List<Questions> catalogue;
    private Questions item;
    private Random randomGenerator;

    private TextView t_question;
    private ImageView imGIF;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrai_faux);

        catalogue = new ArrayList<Questions>();
        randomGenerator = new Random();
        t_question = (TextView) findViewById(R.id.text_question);
        imGIF = (ImageView) findViewById(R.id.loadingImageView);



        insertQuestions();
        changeItem();
    }

    private void insertQuestions(){
        catalogue.add(new Questions("Tout le monde peut pratiquer le running",true));
        catalogue.add(new Questions("Courir permet de travailler tous les muscles du corps",false));
        catalogue.add(new Questions("Il faut courir au minimum trois fois par semaine",false));
        catalogue.add(new Questions("Courir trente minutes est suffisant",false));
        catalogue.add(new Questions("Pour maigrir, il faut courir le matin à jeun",true));
        catalogue.add(new Questions("La course peut être nocive pour les articulations",true));
    }

    public Questions anyItem()
    {
        int index = randomGenerator.nextInt(catalogue.size());
        item = catalogue.get(index);
        return item;
    }


    public void changeItem(){
        //Glide.with(this).load("https://media.giphy.com/media/O5ibw2aqNIfAs/giphy.gif").into(imageView);
        /*Glide.with(this)
                .load("https://media.giphy.com/media/O5ibw2aqNIfAs/giphy.gif")
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imGIF.setImageDrawable(resource);
                    }
                });*/
        Glide.with(this)
                .asGif()
                .load("https://media.giphy.com/media/O5ibw2aqNIfAs/giphy.gif")
                .into(imGIF);
        item = anyItem();
        t_question.setText(item.getQuestion());
    }

    public void checkresult(boolean x){
        String y = x==true ? "vrai" : "faux";
        if(x == item.getReponse()){
            FancyToast.makeText(this,"true !",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();;
        }else{
            FancyToast.makeText(this,"false !",FancyToast.LENGTH_SHORT, FancyToast.ERROR,false).show();;
        }
    }
    public void click_true(View view) {
        checkresult(true);
        changeItem();
    }
    public void click_false(View view) {
        checkresult(false);
        changeItem();
    }
}
