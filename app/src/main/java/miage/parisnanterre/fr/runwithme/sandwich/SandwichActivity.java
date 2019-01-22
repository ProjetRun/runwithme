package miage.parisnanterre.fr.runwithme.sandwich;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import miage.parisnanterre.fr.runwithme.R;

public class SandwichActivity extends AppCompatActivity {



    private SandwichAdapter mSandwichAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);

        getSupportActionBar().hide();

        setupListAdapter(setupListSandwich());

    }

    private List<Sandwich> setupListSandwich(){
        List<Sandwich> sandwicheList = new ArrayList<>();

        String[] sandwiches = this.getResources().getStringArray(R.array.sandwich_details);
        System.out.println(sandwiches);
        for (String sandwiche : sandwiches) {
            System.out.println(sandwiche);

            Sandwich sandwich = null;
            try {
                sandwich = JsonUtils.parseSandwichJson(sandwiche);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
    }




}