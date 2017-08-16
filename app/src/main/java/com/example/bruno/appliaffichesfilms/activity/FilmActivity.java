package com.example.bruno.appliaffichesfilms.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bruno.appliaffichesfilms.R;
import com.example.bruno.appliaffichesfilms.adapter.CarousselPager;
import com.example.bruno.appliaffichesfilms.model.Film;
import com.squareup.picasso.Picasso;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by Bruno on 11/08/2017.
 */

public class FilmActivity extends AppCompatActivity {

    /*//Data  for the Scrollview
    int NUM_PAGES = 5;
    ViewPager mPager;
    PagerAdapter mPagerAdapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        Film film = (Film) getIntent().getExtras().get("film");

        TextView t = (TextView) findViewById(R.id.description);
        t.setText("Genre: " + film.getGenre() + "\nSynopsis: " + film.getSynopsis()+ "\nCategorie: " + film.categorie);

        TextView realisateur = (TextView) findViewById(R.id.realisateur);
        realisateur.setText("Realisateur: " + film.getRealisateur());

        TextView titre = (TextView) findViewById(R.id.titre);
        titre.setText(film.getTitre());

        TextView titre_ori = (TextView) findViewById(R.id.titre_ori);
        if (film.getTitre_ori().equals("")) {
            titre_ori.setText("Titre original : " + film.getTitre());
        } else {
            titre_ori.setText("Titre original: " + film.getTitre_ori());
        }

        TextView duree = (TextView) findViewById(R.id.duree);
        duree.setText("Duree: " + film.getDuree() + " minutes");

        TextView date_sortie = (TextView) findViewById(R.id.date_sortie);
        date_sortie.setText("Date de sortie: " + film.getDate_sortie());

        ImageView img = (ImageView) findViewById(R.id.imagefilm);
        if (film.getImagesX() == null || film.getImagesX().size() == 0)
        {
            ArrayList<String> images = new ArrayList<>();
            images.add(film.affiche.split("&")[0]);
            film.setImagesX(images);
        }

        CarousselPager adapter = new CarousselPager(getSupportFragmentManager());
        adapter.setImages(film.getImagesX());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);


        /*Button btnImage = (Button)findViewById(R.id.buttonImage);
        btnImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(FilmActivity.this,ImageActivity.class);
                startActivity(i);
                //finish();
            }

        });*/

        /*// Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);*/





    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     *//*
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.image_layout);

            // Instantiate a ViewPager and a PagerAdapter.
            mPager = (ViewPager) findViewById(R.id.pager);
            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
            mPager.setAdapter(mPagerAdapter);
        }

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }*/
}
