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

import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.button_record_dist;
import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.button_record_speed;
import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.button_record_time;
import static miage.parisnanterre.fr.runwithme.RunningStatisticsActivity.statistics;

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
            viewHolder.uniteMesure = convertView.findViewById(R.id.button_distance_liste);
            viewHolder.duree = (Button) convertView.findViewById(R.id.button_time_display);
            viewHolder.rythme = (Button) convertView.findViewById(R.id.button_pace_display);
            viewHolder.calories = (Button) convertView.findViewById(R.id.button_cal_display);
            convertView.setTag(viewHolder);
        }

        RunningStatistics statistics = getItem(position);
        viewHolder.duree.setText(statistics.getDureeHeuresMinutesSecondes());
        viewHolder.heure.setText(statistics.getHeure());
        viewHolder.distance.setText(statistics.getDistance());
        viewHolder.uniteMesure.setText(" " + statistics.getUniteMesure());
        viewHolder.rythme.setText(statistics.getRythme());
        viewHolder.calories.setText(statistics.getCalories());
        viewHolder.date.setText(String.valueOf(statistics.getDate()));



        return convertView;

    }



    protected class ListeViewHolder{
        public TextView date;
        public TextView heure;
        public Button distance;
        public Button duree;
        public Button rythme;
        public Button calories;
        public Button uniteMesure;
    }
}
