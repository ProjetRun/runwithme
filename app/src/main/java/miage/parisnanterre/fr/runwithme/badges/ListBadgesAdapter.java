package miage.parisnanterre.fr.runwithme.badges;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import miage.parisnanterre.fr.runwithme.R;

public class ListBadgesAdapter extends ArrayAdapter {

    List<Badge> badges;
    Context c;
    int r;

    public ListBadgesAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.badges = objects;
        this.c= context;
        this.r = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View v = inflater.inflate(r, parent, false);

        Badge badge;
        badge = badges.get(position);
        TextView tv = v.findViewById(R.id.intitule_badge);
        tv.setText(badge.getNom());

        return v;
    }
}
