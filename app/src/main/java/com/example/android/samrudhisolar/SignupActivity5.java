package com.example.android.samrudhisolar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity5 extends AppCompatActivity {

    EditText vld1,vld2,vld3,vld4,vld5,vld6;
    Button btn5;

    EditText fName,lName, addr, pincode, m_no, mail, password,cnf_pass, vldcode;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Dealers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup5);

        btn5 = findViewById(R.id.btn5);
        vld1 = findViewById(R.id.vld1);
        vld2 = findViewById(R.id.vld2);
        vld3 = findViewById(R.id.vld3);
        vld4 = findViewById(R.id.vld4);
        vld5 = findViewById(R.id.vld5);
        vld6 = findViewById(R.id.vld6);



        String[] validCode = new String[]{vld1.getText().toString()+vld2.getText().toString()+vld3.getText().toString()+vld4.getText().toString()+vld5.getText().toString()+vld6.getText().toString()};

        String userValidationCode = Arrays.toString(validCode);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


        // Intent intent = new Intent(SignupActivity5.this, SignupActivity5.class);
       /* String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");*/
        //intent.putExtra("firstName", firstName);
        //intent.putExtra("lastName", lastName);

        Map<String, Object> user = new HashMap<>();
        user.put("Validation Code", userValidationCode);

        vld1.addTextChangedListener(new TextWatcher() {  //FOR MOVING THE FOUCUS FROM ONE TO NEXT PINCODE EDITTEXT WHEN ONE NUMBER IS ENTERED IN EACH
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        vld2.requestFocus();
                }
            }
        });

        vld2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        vld3.requestFocus();
                }
            }
        });

        vld3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        vld4.requestFocus();
                }
            }
        });

        vld4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        vld5.requestFocus();
                }
            }
        });

        vld5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        vld6.requestFocus();
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(TextUtils.isEmpty(vld1.getText().toString())) {  //TO CHECK IF EACH EDDITTEXT IS EMPTY OR NOT?
                            vld6.setError("Please add all digits");
                        }
                        else if(TextUtils.isEmpty(vld2.getText().toString())) {
                            vld6.setError("Please add all digits");
                        }
                        else if(TextUtils.isEmpty(vld3.getText().toString())) {
                            vld6.setError("Please add all digits");
                        }
                        else if(TextUtils.isEmpty(vld4.getText().toString())) {
                            vld6.setError("Please add all digits");
                        }
                        else if(TextUtils.isEmpty(vld5.getText().toString())) {
                            vld6.setError("Please add all digits");
                        }
                        else if(TextUtils.isEmpty(vld6.getText().toString())) {
                            vld6.setError("Please add all digits");
                        }else{
//
                            //reference.child("Dealers").child("validation_code").setValue(validationCode);
                          //  String keyid = reference.push().getKey();
                            reference.child(currentFirebaseUser.getUid()).child("Validation code").setValue(vld1.getText().toString()+vld2.getText().toString()+vld3.getText().toString()+vld4.getText().toString()+vld5.getText().toString()+vld6.getText().toString());

                            Intent i = new Intent(SignupActivity5.this, MainActivity.class); // TO MOVE FROM SIGNUP TO SIGN IN
                            startActivity(i);
                            Toast.makeText(SignupActivity5.this, "Account successfully created! Login now...", Toast.LENGTH_SHORT).show();
                            overridePendingTransition(R.anim.left_anim, R.anim.right_anim); //FOR ANIMATION
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
//                    reference.child("Dealers").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if(TextUtils.isEmpty(vld1.getText().toString())) {  //TO CHECK IF EACH EDDITTEXT IS EMPTY OR NOT?
//                                vld6.setError("Please add all digits");
//                            }
//                            else if(TextUtils.isEmpty(vld2.getText().toString())) {
//                                vld6.setError("Please add all digits");
//                            }
//                            else if(TextUtils.isEmpty(vld3.getText().toString())) {
//                                vld6.setError("Please add all digits");
//                            }
//                            else if(TextUtils.isEmpty(vld4.getText().toString())) {
//                                vld6.setError("Please add all digits");
//                            }
//                            else if(TextUtils.isEmpty(vld5.getText().toString())) {
//                                vld6.setError("Please add all digits");
//                            }
//                            else if(TextUtils.isEmpty(vld6.getText().toString())) {
//                                vld6.setError("Please add all digits");
//                            }else{
////
//                                //reference.child("Dealers").child("validation_code").setValue(validationCode);
//
//                                Intent i = new Intent(SignupActivity5.this, MainActivity.class); // TO MOVE FROM SIGNUP TO SIGN IN
//                                startActivity(i);
//                                Toast.makeText(SignupActivity5.this, "Account successfully created! Login now...", Toast.LENGTH_SHORT).show();
//                                overridePendingTransition(R.anim.left_anim, R.anim.right_anim); //FOR ANIMATION
//                                finish();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });




            }
        });
    }
}