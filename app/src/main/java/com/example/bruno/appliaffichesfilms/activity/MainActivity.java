package com.example.bruno.appliaffichesfilms.activity;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.bruno.appliaffichesfilms.R;
import com.example.bruno.appliaffichesfilms.adapter.MyAdapter;
import com.example.bruno.appliaffichesfilms.model.Film;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static String url = "http://voyage3.corellis.eu/filmsSeances.json";
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        pd = new ProgressDialog(MainActivity.this);
        pd.setTitle("AppliAffichesFilms");
        pd.setMessage("Chargement des films");
        pd.show();

        URL u = null;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new DownloadFilesTask().execute(u);




    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }


    private class DownloadFilesTask extends AsyncTask<URL, Void, ArrayList<Film>> {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected ArrayList<Film> doInBackground(URL... params) {
            ArrayList<Film> films = new ArrayList<>();
            URL u = params[0];

            Gson gson = new Gson();

            try {
                InputStreamReader reader = new InputStreamReader(u.openStream(), Charset.forName("UTF-8"));

                BufferedReader readerr = new BufferedReader(
                        reader);


                String resultat = readAll(readerr);
                JSONArray data = new JSONArray(resultat);


                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonfilm = data.getJSONObject(i);
                    Film film = gson.fromJson(jsonfilm.toString(), Film.class);
                    JSONArray medias = null;
                    ArrayList<String> images = new ArrayList<>();
                    try {
                        medias = jsonfilm.getJSONArray("medias");
                    } catch (JSONException e) {
                    }

                    if (medias != null) {
                        for (int a = 0; a < medias.length(); a++) {
                            JSONObject jsonimage = medias.getJSONObject(a);
                            String image = (String) jsonimage.getString("path");
                            images.add(image.split("&")[0]);
                        }
                    }
                    film.setImagesX(images);
                    films.add(film);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return films;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return films;
        }
            @Override
        protected void onPostExecute(ArrayList<Film> films) {
            super.onPostExecute(films);
            MyAdapter adapter = new MyAdapter(films, MainActivity.this);
            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            mLayoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setHasFixedSize(true);
            if(pd.isShowing())
                pd.dismiss();
        }
    }




}
