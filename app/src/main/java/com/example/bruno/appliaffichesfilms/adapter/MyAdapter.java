package com.example.bruno.appliaffichesfilms.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bruno.appliaffichesfilms.R;
import com.example.bruno.appliaffichesfilms.activity.FilmActivity;
import com.example.bruno.appliaffichesfilms.activity.MainActivity;
import com.example.bruno.appliaffichesfilms.model.Film;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Bruno on 09/08/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public ArrayList<Film> films;
    Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView imageView;
        public ViewHolder(View v) {
            super(v);

            mTextView = (TextView) v.findViewById(R.id.editText);
            imageView = (ImageView)v.findViewById(R.id.imageView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return (new ViewHolder(v));

}

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Film film = films.get(position);
        holder.mTextView.setText(film.titre);
        String urlimage = film.affiche.split("&")[0];
        Picasso.with(context).load(urlimage).fit().centerCrop().into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FilmActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("film", film);
                intent.putExtras(b);
                context.startActivity(intent);
                //((MainActivity)context).finish();
            }
        });
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return films.size();
    }



}
