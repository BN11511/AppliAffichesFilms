package com.example.bruno.appliaffichesfilms.activity;


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
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String url = "http://voyage3.corellis.eu/filmsSeances.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager


        URL u = null;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new DownloadFilesTask().execute(u);




    }
    private class DownloadFilesTask extends AsyncTask<URL, Void, ArrayList<Film>> {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected ArrayList<Film> doInBackground(URL... params) {
            ArrayList<Film> films = new ArrayList<>();
            URL u = params[0];

            Gson gson = new Gson();

            try {
                InputStreamReader reader = new InputStreamReader(u.openStream());

                BufferedReader readerr = new BufferedReader(
                        reader);
                String test = readerr.lines().collect(Collectors.joining(System.getProperty("line.separator")));

            JSONObject jsonObj = new JSONObject(test);
            JSONArray data = jsonObj.getJSONArray("");

            for (int i =0;i< data.length();i++)
            {
                JSONObject jsonfilm = data.getJSONObject(i);
                Film film = gson.fromJson(jsonfilm.toString(), Film.class);
                films.add(film);
            }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return films;
        }

        @Override
        protected void onPostExecute(ArrayList<Film> films) {
            super.onPostExecute(films);
            MyAdapter adapter = new MyAdapter(films);
            mRecyclerView.setAdapter(adapter);
        }
    }




}
