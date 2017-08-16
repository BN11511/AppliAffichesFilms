package com.example.bruno.appliaffichesfilms.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bruno.appliaffichesfilms.R;
import com.example.bruno.appliaffichesfilms.model.Film;
import com.squareup.picasso.Picasso;

/**
 * Created by Bruno on 11/08/2017.
 */

public class FilmActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        Film film = (Film) getIntent().getExtras().get("film");

        TextView t = (TextView) findViewById(R.id.description);
        t.setText("Genre: "+film.getGenre()+"\nSynopsis: "+film.getSynopsis());

        TextView realisateur = (TextView) findViewById(R.id.realisateur);
        realisateur.setText("Realisateur: "+film.getRealisateur());

        TextView titre = (TextView) findViewById(R.id.titre);
        titre.setText(film.getTitre());

        TextView titre_ori = (TextView) findViewById(R.id.titre_ori);
        if (film.getTitre_ori().equals(""))
        {titre_ori.setText("Titre original : "+film.getTitre());}
        else {titre_ori.setText("Titre original: "+film.getTitre_ori());}

        TextView duree = (TextView) findViewById(R.id.duree);
        duree.setText("Duree: "+film.getDuree()+" minutes");

        TextView date_sortie = (TextView) findViewById(R.id.date_sortie);
        date_sortie.setText("Date de sortie: "+film.getDate_sortie());

        ImageView img =(ImageView) findViewById(R.id.imagefilm);
        //Picasso.with(this).load(film.getAffiche()).fit().centerCrop().into(img);
        String urlimage = film.getAffiche();
        if(film.getImagesX().size()> 0)
        urlimage = film.getImagesX().get(0);
        Picasso.with(this).load(urlimage).fit().centerCrop().into(img);

        Button btnBack = (Button)findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
/*                Intent i = new Intent(FilmActivity.this,MainActivity.class);
                startActivity(i);*/
                finish();
            }

        });

        /*Button btnImage = (Button)findViewById(R.id.buttonImage);
        btnImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(FilmActivity.this,ImageActivity.class);
                startActivity(i);
                //finish();
            }

        });*/
    }
}
