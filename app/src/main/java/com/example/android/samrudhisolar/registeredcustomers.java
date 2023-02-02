package com.example.android.samrudhisolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class registeredcustomers extends AppCompatActivity {
    RecyclerView recyclerView ;
    DatabaseReference database ;
   // MyAdapter myAdapter ;
    ArrayList <User> list ;
    ProgressDialog p ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeredcustomers);
        String s = MainActivity2.cname .getText().toString() ;
        s = s.replace(".", "_") ;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id .registredcustomerslist) ;
        database = FirebaseDatabase.getInstance().getReference("Dealers").child(s) ;
        recyclerView.setHasFixedSize(true );
        recyclerView .setLayoutManager(new LinearLayoutManager(this) ) ;
        list = new ArrayList<>() ;
        //myAdapter = new MyAdapter( this, list) ;
    //    recyclerView.setAdapter( myAdapter);

        p = new ProgressDialog(registeredcustomers.this);
        p.show();
        p.setContentView(R.layout.progressdialog);
        p.setCancelable(true);
        p.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for ( DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot .getValue(User.class ) ;
                    list.add(user ) ;
                }
                p.dismiss(); 
                //myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) ;
    }
}