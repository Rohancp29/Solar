package com.example.android.samrudhisolar;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.samrudhisolar.utility.NetworkChangeListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    public static TextView cname ;
    Button register, order, fetchbtn ;
    FloatingActionButton logout  ;
    FirebaseAuth auth ;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        register = findViewById(R.id.btnlogin) ;
        order = findViewById(R.id.btnorder) ;
        cname = findViewById(R.id.lname) ;
        logout = findViewById(R.id.fbtnlogou) ;
        fetchbtn = findViewById(R.id.fetchdatabtn ) ;
        auth = FirebaseAuth.getInstance() ;

        //String currentuser = FirebaseAuth.getInstance().getCurrentUser().getEmail() ;
       // cname.setText(currentuser);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity2.this, MainActivity3.class));

            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (getApplicationContext(), order.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                //startActivity(new Intent(MainActivity2.this, MainActivity.class));
                onBackPressed();
            }
        });

        fetchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent (MainActivity2.this, registeredcustomers.class));
            }
        });
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION ) ;
        registerReceiver(networkChangeListener, filter)  ;
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}

