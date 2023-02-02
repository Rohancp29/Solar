package com.example.android.samrudhisolar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    EditText fName,lName;
    Button btn1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Dealers");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn1 = findViewById(R.id.btn1);
        fName = findViewById(R.id.user_first_name);
        lName = findViewById(R.id.user_last_name);

        String userFirstName = fName.getText().toString().trim();
        String userLastName = lName.getText().toString().trim();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
/*
        Intent intent = new Intent(SignupActivity.this, SignupActivity2.class);
        intent.putExtra("firstName", fName.getText().toString());
        intent.putExtra("lastName", lName.getText().toString());*/
       // startActivity(intent);

        Map<String, Object> user = new HashMap<>();
        user.put("First name", userFirstName);
        user.put("Last name", userLastName);


        btn1.setOnClickListener(new View.OnClickListener() {  //TO VALIDATE AND REPLACE THE FRAGMENT TO SECOND FRAGMENT(SignUp2)
            @Override
            public void onClick(View view) {


                reference.addValueEventListener(new ValueEventListener() {



                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(TextUtils.isEmpty(fName.getText().toString())) {  //TO CHECK IF EACH EDDITTEXT IS EMPTY OR NOT?
                            fName.setError("Please enter first name");
                        }
                        else if(TextUtils.isEmpty(lName.getText().toString())) {
                            lName.setError("Please enter last name");
                        }else{
//                            Dealers dealers = new Dealers(fName.getText().toString(), lName.getText().toString());
//                            database.getReference().child("Dealers").child("first name").setValue(dealers);
                            //String keyid = reference.push().getKey();
                            String id=reference.getKey();
                            reference.child(currentFirebaseUser.getUid()).child("First name").setValue(fName.getText().toString());
                            reference.child(currentFirebaseUser.getUid()).child("Last name").setValue(lName.getText().toString());

                            Intent i = new Intent(SignupActivity.this, SignupActivity2.class); // TO MOVE FROM SIGNUP TO SIGN IN
                            startActivity(i);
                            overridePendingTransition(R.anim.left_anim, R.anim.right_anim); //FOR ANIMATION
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}