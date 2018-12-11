package miage.parisnanterre.fr.runwithme.MarathonTraining;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.database.DatabaseSQLite;

public class SeanceAdapter extends ArrayAdapter<Seance> {
    public SeanceAdapter(Context context, ArrayList<Seance> seances) {
        super(context, 0, seances);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final DatabaseSQLite dbHelper;
        dbHelper = new DatabaseSQLite(getContext());
        // Get the data item for this position
        Seance seance = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        // Lookup view for data population
        TextView num_seance = convertView.findViewById(R.id.num_seance);
        TextView num_semaine = convertView.findViewById(R.id.num_semaine);
        TextView contenu = convertView.findViewById(R.id.contenu);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        //TextView Type_seance = convertView.findViewById(R.id.Type_seance);

        // Populate the data into the template view using the data object
        num_seance.setText(String.valueOf(seance.getNumSeance()));
        num_semaine.setText(String.valueOf(seance.getNumSemaine()));
        contenu.setText(seance.getContenuSeance());
        checkBox.setChecked(seance.isChecked());

        // Lookup view for data population
        //Button btButton = convertView.findViewById(R.id.btnDelete);
        CheckBox checkBox1 = convertView.findViewById(R.id.checkBox);
        // Cache row position inside the button using `setTag`
        checkBox1.setTag(position);
        // Attach the click event handler
        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                // Access the row position here to get the correct data item

                CheckBox cb = (CheckBox) view;
                Seance seance = getItem(position);
                seance.setChecked(cb.isChecked());
                dbHelper.insertSeanceChecked(seance);
                Log.e("Adapter", "onCLick" + seance.getContenuSeance());
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}