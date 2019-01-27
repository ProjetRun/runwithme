package miage.parisnanterre.fr.runwithme.sandwich;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import miage.parisnanterre.fr.runwithme.R;

public class ObjectifNutritionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectif_nutrition);
        getSupportActionBar().hide();

    }

    public void clickPerte(View v){
        Intent intent = new Intent(this, SandwichActivity.class);
        intent.putExtra("obj", "perte");
        startActivity(intent);
    }
    public void clickGain(View v){
        Intent intent = new Intent(this, SandwichActivity.class);
        intent.putExtra("obj", "gain");
        startActivity(intent);
    }
    public void clickSain(View v){
        Intent intent = new Intent(this, SandwichActivity.class);
        intent.putExtra("obj", "sain");
        startActivity(intent);

    }
}
