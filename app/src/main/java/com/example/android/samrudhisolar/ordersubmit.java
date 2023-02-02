package com.example.android.samrudhisolar;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ordersubmit extends AppCompatActivity {
    MediaPlayer mediaPlayer ;
    Button ordersubmitbtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersubmit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediaPlayer = MediaPlayer.create(this, R.raw.musi) ;
        mediaPlayer.start();
        setContentView(R.layout.activity_finalsubmit);
        ordersubmitbtn= findViewById ( R.id.ordersub) ;
        ordersubmitbtn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}