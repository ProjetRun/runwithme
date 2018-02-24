package miage.parisnanterre.fr.runwithme;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RunningStatisticsAdapter extends ArrayAdapter<RunningStatistics>{

    Context context;
    List<RunningStatistics> listes;
    public RunningStatisticsAdapter(Context context, List<RunningStatistics> listes){
        super(context,0,listes);
        this.context = context;
        this.listes = listes;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.running_statistics_list, parent, false);
        }


        ListeViewHolder viewHolder = (ListeViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ListeViewHolder();
            viewHolder.date = (TextView) convertView.findViewById(R.id.textViewDate);
            viewHolder.heure = (TextView) convertView.findViewById(R.id.textViewHeure);
            viewHolder.distance = (Button) convertView.findViewById(R.id.button_distance_display);
            viewHolder.duree = (Button) convertView.findViewById(R.id.button_time_display);
            viewHolder.rythme = (Button) convertView.findViewById(R.id.button_pace_display);
            viewHolder.calories = (Button) convertView.findViewById(R.id.button_cal_display);
            convertView.setTag(viewHolder);
        }

        RunningStatistics statistics = getItem(position);
        viewHolder.duree.setText(statistics.getDuree());
        viewHolder.heure.setText(statistics.getHeure());
        viewHolder.distance.setText(statistics.getDistance());
        viewHolder.rythme.setText(statistics.getRythme());
        viewHolder.calories.setText(statistics.getCalories());
        viewHolder.date.setText(String.valueOf(statistics.getDate()));

        //convertView.setClickable(true);


        //convertView.setOnClickListener(myClickListener);
        /*
        convertView.setOnClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text or do whatever you need.
                Toast.makeText(context, getItem(position).getNom(), Toast.LENGTH_SHORT).show();
            }
        });
        */


        return convertView;

    }



    protected class ListeViewHolder{
        public TextView date;
        public TextView heure;
        public Button distance;
        public Button duree;
        public Button rythme;
        public Button calories;
    }
}
