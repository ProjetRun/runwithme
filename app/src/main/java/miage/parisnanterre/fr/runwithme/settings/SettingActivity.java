package miage.parisnanterre.fr.runwithme.settings;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

import miage.parisnanterre.fr.runwithme.R;

public class SettingActivity extends AppCompatActivity {

    static Profil profil = new Profil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().hide();

        File file = new File(getApplicationContext().getFilesDir(),"whatever.txt");
        if(file.exists()){
            load();
            setUpAllSpinner();
        }
        else{
            //Nothing
            setUpAllSpinner();
        }


    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        save();
        finish();

    }

    public void save(){
        OutputStream os = null;
        File path = getBaseContext().getFilesDir();
        File file = new File(path, "whatever.txt");

        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Crypto.encrypt((Serializable) profil, os, "RWM");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            try {
                read();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
        }

    }

    public void read() throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {



        InputStream is = null;
        File path = getBaseContext().getFilesDir();
        File file = new File(path, "whatever.txt");

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            profil = (Profil) Crypto.decrypt(is, "RWM");
            setUpAllSpinner();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

    public void setUpAllSpinner(){
        setUpInfo();
        setUpSexe();
        setUpTaille();
        setUpKG();
    }

    EditText editPrenom;
    EditText editNom;
    EditText editEmail;
    public void setUpInfo(){
        editPrenom = (EditText) findViewById(R.id.editPrenom);
        editNom = (EditText) findViewById(R.id.editNom);
        editEmail = (EditText) findViewById(R.id.editMail);

        editPrenom.setText(profil.getPrenom());
        editNom.setText(profil.getNom());
        editEmail.setText(profil.getEmail());

        editPrenom.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                profil.setPrenom(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        editNom.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                profil.setNom(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                profil.setEmail(c.toString());
            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {
                // this one too
            }
        });
    }


    Spinner mspinSexe;
    public void setUpSexe(){
        mspinSexe=(Spinner) findViewById(R.id.spinnerSexe);
        //Integer[] items = new Integer[]{1,2,3,4}
        final String[] items = new String[]{"Femme","Homme","autres"};
        //ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);
        mspinSexe.setAdapter(adapter);
        mspinSexe.setSelection(profil.getSexe());
        mspinSexe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                profil.setSexe(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


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
        mspinTaille.setSelection(profil.getTaille());

        mspinTaille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                profil.setTaille(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


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
        mspinKG.setSelection(profil.getPoids());


        mspinKG.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                profil.setPoids(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    void GoToURlGestion(View v){
        Uri uri = Uri.parse("https://fr-fr.facebook.com/privacy/explanation");
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    void GoToURLConfidentialite(View v){
        Uri uri = Uri.parse("https://fr-fr.facebook.com/privacy/explanation");
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    void GoToURLUtilisation(View v){
        Uri uri = Uri.parse("https://fr-fr.facebook.com/terms");
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }


}
