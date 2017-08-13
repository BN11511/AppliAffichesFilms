package com.example.bruno.appliaffichesfilms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bruno.appliaffichesfilms.R;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Button btnBack = (Button)findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
/*                Intent i = new Intent(ImageActivity.this,FilmActivity.class);
                startActivity(i);*/
                finish();
            }

        });
    }
}
