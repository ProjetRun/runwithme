package miage.parisnanterre.fr.runwithme.sandwich;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import miage.parisnanterre.fr.runwithme.R;

public class SandwichDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich_detail);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Sandwich sandwich=
                (Sandwich) bundle.getSerializable("sandwich");
        System.out.println(sandwich.getMainName());

        setUpUI(sandwich);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void setUpUI(Sandwich sandwich) {
        // Sandwich image
        ImageView imageRecipe = (ImageView) findViewById(R.id.image_recipe);
        Glide.with(this)
                .load(sandwich.getImage())
                .into(imageRecipe);

        // Sandwich main name
        TextView sandwichName = (TextView)findViewById(R.id.text_sandwich_name);
        sandwichName.setText(sandwich.getMainName());

        // Programmatically create & add "also known as" labels
        List<String> names = sandwich.getAlsoKnownAs();
        if (!names.isEmpty()) {
            for (String name : names) {
                TextView textView = new TextView(this);
                textView.setText(name);
                textView.setBackground(ContextCompat.getDrawable(this, R.drawable.chip_shape));
                TextViewCompat.setTextAppearance(textView, R.style.Chips);

            }
        } else {

        }

        // Sandwich description
        TextView descriptionTv = (TextView)findViewById(R.id.description_tv);
        descriptionTv.setText(sandwich.getDescription());

        // Ingredients List
        List<String> ingredients = sandwich.getIngredients();
        for (String ingredient : ingredients) {
            TextView textView = new TextView(this);
            textView.setText(ingredient);
            TextViewCompat.setTextAppearance(textView, R.style.Chips);
            textView.setPadding(0, 32, 0, 32);
            textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(
                    this, R.drawable.bullet), null, null, null);
            textView.setBackground(ContextCompat.getDrawable(this, R.drawable.dashed_line));
            textView.setCompoundDrawablePadding(32);

            LinearLayout ingredients_list = (LinearLayout)findViewById(R.id.ingredients_list);
            ingredients_list.addView(textView);
        }


    }

}
