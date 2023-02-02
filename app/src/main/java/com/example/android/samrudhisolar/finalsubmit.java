package com.example.android.samrudhisolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class finalsubmit extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button warrantycard ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediaPlayer = MediaPlayer.create(this, R.raw.musi) ;
        mediaPlayer.start();
        setContentView(R.layout.activity_finalsubmit);  
        warrantycard = findViewById ( R.id.ordersub) ;


        warrantycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getApplicationContext(), warranty.class));
                finish();
            }
        });
    }
}