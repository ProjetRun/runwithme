package miage.parisnanterre.fr.runwithme.bottomNavigation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import miage.parisnanterre.fr.runwithme.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().hide();

        setUpSexe();
        setUpTaille();
        setUpKG();
    }

    Spinner mspinSexe;
    public void setUpSexe(){
        mspinSexe=(Spinner) findViewById(R.id.spinnerSexe);
        //Integer[] items = new Integer[]{1,2,3,4};
        String[] items = new String[]{"Femme","Homme","autres"};
        //ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);

        mspinSexe.setAdapter(adapter);
    }

    Spinner mspinTaille;
    public void setUpTaille(){
        mspinTaille = (Spinner) findViewById(R.id.spinnerTaille);
        List<String> list = new ArrayList<String>();
        for(int i=0;i<99;i++){
            list.add("1m"+i);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinTaille.setAdapter(dataAdapter);
        mspinTaille.setSelection(66);

    }

    Spinner mspinKG;
    public void setUpKG(){
        mspinKG = (Spinner) findViewById(R.id.spinnerKG);
        List<String> list = new ArrayList<String>();
        for(int i=40;i<150;i++){
            list.add(i+" KG.");
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinKG.setAdapter(dataAdapter);
        mspinKG.setSelection(16);

    }

    void GoToURL(View v){
        Uri uri = Uri.parse("https://www.bandainamcoent.com/games/ni-no-kuni-ii");
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
