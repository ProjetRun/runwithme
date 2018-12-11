package miage.parisnanterre.fr.runwithme.MarathonTraining;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.database.DatabaseSQLite;

public class MarathonTrainingActivity extends AppCompatActivity {

    DatabaseSQLite db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db = new DatabaseSQLite(this);
        listView = findViewById(R.id.lstSeance);
        //utiliser loadBDexercices(); pour mettre un plan d'entrainement dans la base sqlite
        //loadBDexercices();
        loadTaskList();
    }

    private void loadTaskList() {
        // Construct the data source
        ArrayList<Seance> seances = db.getTaskList();
        // Create the adapter to convert the array to views
        SeanceAdapter adapter = new SeanceAdapter(this, seances);
        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.lstSeance);
        listView.setAdapter(adapter);
    }

    private void loadBDexercices(){
        Seance seance = new Seance();
        //TYPE d'entrainement = 'Débuter'
        //OBJECTIF : courir 30mns - 1 séance / semaine
        //PENDANT 12 semaines
        //N°semaine | N°séance |Type | Contenu
        seance.setContenuSeance("10' marche - 6 * 2' course avec R=1' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(1);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("10' marche - 5 * 3' course avec R=1' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(2);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("8' marche - 4 * 4' course avec R=1' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(3);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("6' marche - 4 * 5' course avec R=1' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(4);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("2 * 6 ' course avec R=1'30 + 2 * 5' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(5);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("2 * 8 ' course avec R=1'30 + 3 * 4' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(6);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("12' - 10' - 8' de course avec R=2' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(7);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("2 * 10 ' course avec R=2' + 2 * 5' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(8);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("15' course - R=2' - 3 * 5' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(9);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("20' - 10' - 5' course avec R=2' marche");
        seance.setNumSeance(1);
        seance.setNumSemaine(10);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("25' course - R=2' + 2 * 5' avec R=1'");
        seance.setNumSeance(1);
        seance.setNumSemaine(11);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
        seance.setContenuSeance("30' - Bravo vous y êtes !!!");
        seance.setNumSeance(1);
        seance.setNumSemaine(12);
        seance.setTypeSeance("Debuter");
        db.insertNewSeance(seance);
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
        }
        return super.onOptionsItemSelected(item);
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
