package miage.parisnanterre.fr.runwithme.MarathonTraining;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.database.DatabaseStats;

import static miage.parisnanterre.fr.runwithme.database.DatabaseStats.DB_TABLE;
//import static miage.parisnanterre.fr.runwithme.database.DatabaseStats.ID;


public class MarathonTrainingActivity extends AppCompatActivity {

    DatabaseStats db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db = new DatabaseStats(this);
        listView = findViewById(R.id.lstSeance);
        //utiliser loadBDexercices(); pour mettre un plan d'entrainement dans la base sqlite
        int choice = getDefaults("choiceKey",this);
        ArrayList<Seance> seances  = db.getSeanceList1();
        ArrayList<Seance> seances3 = db.getSeanceList3();
        //db.deleteallseances();

        if (seances.isEmpty()){
            loadBDexercices();
            loadBDexercices1();
        }
        if(seances3.isEmpty()){
            loadBDexercices2();
        }

        loadTaskList();
    }

    private void loadTaskList() {
        // Construct the data source
        int choice = getDefaults("choiceKey",this);
        ArrayList<Seance> seances = null;
        if(choice == 1){//on lance l'entrainement 1
            seances = db.getSeanceList1();
        }

        if(choice == 3){//on lance l'entrainement 1
            seances = db.getSeanceList3();
        }
        ArrayList<Seance> scs = new ArrayList<>();
        for(int i=0; i<seances.size(); i++){

            if (seances.get(i).getId() == 1)
                scs.add(seances.get(i));
        }

        // Create the adapter to convert the array to views
        SeanceAdapter adapter = new SeanceAdapter(this, seances);
        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.lstSeance);
        listView.setAdapter(adapter);
    }

    private void loadBDexercices() {

        Seance seance = new Seance();
        //TYPE d'entrainement = 'Débuter'
        //OBJECTIF : courir 30mns - 1 séance / semaine
        //PENDANT 12 semaines
        //N°semaine | N°séance |Type | Contenu
        seance.setId(1);
        seance.setChecked(false);
        seance.setContenuSeance("10' marche - 6 * 2' course avec R=1' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(1);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(12);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("10' marche - 5 * 3' course avec R=1' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(2);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(15);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("8' marche - 4 * 4' course avec R=1' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(3);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(16);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("6' marche - 4 * 5' course avec R=1' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(4);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(20);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("2 * 6 ' course avec R=1'30 + 2 * 5' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(5);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(22);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("2 * 8 ' course avec R=1'30 + 3 * 4' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(6);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(28);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("12' - 10' - 8' de course avec R=2' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(7);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(30);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("2 * 10 ' course avec R=2' + 2 * 5' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(8);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(30);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("15' course - R=2' - 3 * 5' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(9);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(30);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("20' - 10' - 5' course avec R=2' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(10);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(35);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("25' course - R=2' + 2 * 5' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(11);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(35);
        db.insertNewSeance(seance);
        seance.setId(1);
        seance.setContenuSeance("30' - Bravo vous y êtes !!!");
        seance.setNumSeance(1);
        seance.setNumSemaine(12);
        seance.setTypeSeance("Debuter");
        seance.setMinutes(30);
        db.insertNewSeance(seance);

    }

    private void loadBDexercices1() {

        Seance seance1 = new Seance();
        //TYPE d'entrainement = 'Débuter'
        //OBJECTIF : courir 30mns - 2 séance / semaine
        //PENDANT 6 semaines
        //N°semaine | N°séance |Type | Contenu
        seance1.setId(2);
        seance1.setContenuSeance("10' marche - 6 * 2' course avec R=1' marche");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(1);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("10' marche - 5 * 3' course avec R=1' marche");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(1);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("8' marche - 4 * 4' course avec R=1' marche");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(2);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("6' marche - 4 * 5' course avec R=1' marche");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(2);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("2 * 6 ' course avec R=1'30 + 2 * 5' avec R=1'");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(3);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("2 * 8 ' course avec R=1'30 + 3 * 4' avec R=1'");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(3);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("12' - 10' - 8' de course avec R=2' marche");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("2 * 10 ' course avec R=2' + 2 * 5' avec R=1'");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("15' course - R=2' - 3 * 5' avec R=1'");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(5);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("20' - 10' - 5' course avec R=2' marche");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(5);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("25' course - R=2' + 2 * 5' avec R=1'");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(6);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);
        seance1.setId(2);
        seance1.setContenuSeance("30' - Bravo vous y êtes !!!");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(6);
        seance1.setTypeSeance("Debuter6");
        seance1.setMinutes(35);
        db.insertNewSeance(seance1);

    }

    private void loadBDexercices2() {

        Seance seance1 = new Seance();
        //TYPE d'entrainement = 'Débuter'
        //OBJECTIF : courir 30mns - 2 séance / semaine
        //PENDANT 6 semaines
        //N°semaine | N°séance |Type | Contenu

        //semaine 1
        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(1);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 6 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(1);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(1);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 30\"-30\" à 100-105%VMA avec 3 minutes de récupération entre chaque série.");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(1);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);


        seance1.setContenuSeance("Sortie Longue de 1h10");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(1);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);


        //semaine 2
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(2);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(2);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(2);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(2);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);


        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(2);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(2);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        //semaine 3
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(3);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(3);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(3);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(3);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);


        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(3);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(3);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        //semaine 4
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);


        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        //semaine 5
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(5);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(5);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(5);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(5);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);


        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(5);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(5);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        //semaine 4
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(6);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(6);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(6);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(6);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(6);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(6);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        //semaine 7
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(7);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(7);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(7);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(7);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(7);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(7);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        //semaine 8
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(8);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(8);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(8);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(8);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(8);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        //semaine 9
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(9);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(9);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(4);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(9);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(9);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(9);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        //semaine 10
        seance1.setContenuSeance("Footing de 20 minutes suivi de 2 fois 8 minutes de travail de PPG/PPS en circuit en circuit avec une récupération de 3 minutes entre chaque effort.");
        seance1.setNumSeance(1);
        seance1.setNumSemaine(10);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes");
        seance1.setNumSeance(2);
        seance1.setNumSemaine(10);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 10 fois 200m à 100-105%VMA avec une récupération de 30 sec entre les 200m et 3 minutes entre chaque série.");
        seance1.setNumSeance(3);
        seance1.setNumSemaine(10);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(45);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 45 minutes à 1 h");
        seance1.setNumSeance(4);
        seance1.setNumSemaine(10);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Footing de 20-30 minutes suivi de 2 séries de 5 fois 400m à 95%VMA avec une récupération de 1 minutes entre les 400m et 3 minutes entre chaque série.");
        seance1.setNumSeance(5);
        seance1.setNumSemaine(10);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);

        seance1.setContenuSeance("Sortie Longue de 1h20 à 70-75%FCM");
        seance1.setNumSeance(6);
        seance1.setNumSemaine(10);
        seance1.setTypeSeance("Semi_experimente");
        seance1.setMinutes(32);
        db.insertNewSeance(seance1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        //Change menu icon color
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Cette fonctionnalité n'est pas terminée
        switch (item.getItemId()){
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Ajouter une nouvelle séance")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Seance seance = new Seance();
                                seance.setContenuSeance(String.valueOf(taskEditText.getText()));
                                seance.setNumSemaine(2);
                                seance.setNumSeance(4);
                                seance.setTypeSeance("débutant");
                                db.insertNewSeance(seance);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
                return true;
            case R.id.action_choice:
                Intent intent = new Intent(this, TrainingChoiceActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static void setDefaults(String key, int value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }
    /*public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView)parent.findViewById(R.id.task_title);
        Log.e("String", (String) taskTextView.getText());
        String task = String.valueOf(taskTextView.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }*/
}
