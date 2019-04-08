package miage.parisnanterre.fr.runwithme.sandwich;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.util.List;

import miage.parisnanterre.fr.runwithme.Challenges.VraiFaux;
import miage.parisnanterre.fr.runwithme.R;
import miage.parisnanterre.fr.runwithme.workout.WorkoutActivity;

public class SandwichAdapter extends RecyclerView.Adapter<SandwichAdapter.ViewHolder>{


    private List<Sandwich> mSandwichList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    SandwichAdapter(Context context, List<Sandwich> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mSandwichList = data;
        this.context = context;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_sandwich, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sandwich sandwich = mSandwichList.get(position);
        /*Glide.with(context)
                .load(sandwich.getImage())
                .into(holder.imageSandwich);*/
        holder.imageSandwich.setImageBitmap(sandwich.getImageBMP());
        holder.textName.setText(sandwich.getMainName());
        holder.textDescription.setText(sandwich.getDescription());


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mSandwichList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageSandwich;
        TextView textName;
        TextView textDescription;


        ViewHolder(View itemView) {
            super(itemView);
            imageSandwich = itemView.findViewById(R.id.image_sandwich);
            textName = itemView.findViewById(R.id.text_name);
            textDescription = itemView.findViewById(R.id.text_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            System.out.println(mSandwichList.get(getAdapterPosition()).getMainName());

            Intent intent = new Intent(view.getContext() , SandwichDetail.class);

            //create a Bundle object
            Bundle extras = new Bundle();
            extras.putSerializable("sandwich", mSandwichList.get(getAdapterPosition()));
            intent.putExtras(extras);
            view.getContext().startActivity(intent);
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    // convenience method for getting data at click position
    Sandwich getItem(int id) {
        return mSandwichList.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }


}


