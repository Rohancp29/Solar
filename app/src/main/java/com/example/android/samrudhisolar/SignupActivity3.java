package com.example.android.samrudhisolar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class SignupActivity3 extends AppCompatActivity {

    EditText m_no,mail;
    Button btn3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Dealers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        btn3 = findViewById(R.id.btn3);
        m_no = findViewById(R.id.m_no);
        //mail = findViewById(R.id.mail);

        String userMobileNo = m_no.getText().toString();

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
       // String userEmail = mail.getText().toString();

       /* Intent intent = new Intent(SignupActivity3.this, SignupActivity4.class);
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);*/

        Map<String, Object> user1 = new HashMap<>();
        user1.put("Mobile number", userMobileNo);
        //user1.put("E-mail", userEmail);

        btn3.setOnClickListener(new View.OnClickListener() {  //TO VALIDATE AND REPLACE THE FRAGMENT TO FOURTH FRAGMENT(SignUp4)
            @Override
            public void onClick(View view) {
//

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(TextUtils.isEmpty(m_no.getText().toString()) && TextUtils.isEmpty(mail.getText().toString())){ //TO CHECK IF BOTH EDITTEXT ARE EMPTY
                            m_no.setError("Please enter mobile no");
                            mail.setError("Please enter email");
                        }
                        else if (TextUtils.isEmpty(m_no.getText().toString())){  //TO CHECK IF ONLY MOBILE NO. EDITTEXT IS EMPTY OR NOT
                            m_no.setError("Please enter mobile no");
                        }
                        else if (!m_no.getText().toString().matches("^[0-9]{10}$"))  //FOR VALIDATION OF MOBILE NO.
                            m_no.setError("Enter valid phone number");
                        /*else if(TextUtils.isEmpty(mail.getText().toString())) {  //TO CHECK IF ONLY EMAIL EDITTEXT IS EMPTY OR NOT
                            mail.setError("Please enter email");
                        }*/
                        else{
                            //String keyid = reference.push().getKey();
//                            Dealers dealers = new Dealers(m_no.getText().toString(), mail.getText().toString());
//                            database.getReference().child("Dealers").child("first name").setValue(dealers);

                            reference.child(currentFirebaseUser.getUid()).child("Mobile number").setValue(m_no.getText().toString());
                           // reference.child("Dealers").child("Email").setValue(mail.getText().toString());

                            Intent i = new Intent(SignupActivity3.this, SignupActivity5.class); // TO MOVE FROM SIGNUP TO SIGN IN
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