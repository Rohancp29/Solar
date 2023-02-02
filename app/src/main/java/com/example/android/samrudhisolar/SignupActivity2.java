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

public class SignupActivity2 extends AppCompatActivity {

    EditText addr,pin1,pin2,pin3,pin4,pin5,pin6;
    Button btn2;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Dealers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        btn2 = findViewById(R.id.btn2);
        addr = findViewById(R.id.addr);
        pin1 = findViewById(R.id.pin1);
        pin2 = findViewById(R.id.pin2);
        pin3 = findViewById(R.id.pin3);
        pin4 = findViewById(R.id.pin4);
        pin5 = findViewById(R.id.pin5);
        pin6 = findViewById(R.id.pin6);

        //String pin = pin1.getText().toString()+pin2.getText().toString()+pin3.getText().toString()+pin4.getText().toString()+pin5.getText().toString()+pin6.getText().toString();

        String[] pin = new String[]{pin1.getText().toString() + pin2.getText().toString() + pin3.getText().toString() + pin4.getText().toString() + pin5.getText().toString() + pin6.getText().toString()};

        String userAddress = addr.getText().toString();
        String userPinCode = Arrays.toString(pin);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
//        Toast.makeText(this, "pincode:" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

       /* Intent intent = new Intent(SignupActivity2.this, SignupActivity3.class);
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);*/

        Map<String, Object> user = new HashMap<>();
        user.put("Address", userAddress );
        user.put("Pin code", userPinCode);

        pin1.addTextChangedListener(new TextWatcher() {  //FOR MOVING THE FOUCUS FROM ONE TO NEXT PINCODE EDITTEXT WHEN ONE NUMBER IS ENTERED IN EACH
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
                        pin2.requestFocus();
                }
            }
        });

        pin2.addTextChangedListener(new TextWatcher() {
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
                        pin3.requestFocus();
                }
            }
        });

        pin3.addTextChangedListener(new TextWatcher() {
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
                        pin4.requestFocus();
                }
            }
        });

        pin4.addTextChangedListener(new TextWatcher() {
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
                        pin5.requestFocus();
                }
            }
        });

        pin5.addTextChangedListener(new TextWatcher() {
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
                        pin6.requestFocus();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {  //TO VALIDATE AND REPLACE THE FRAGMENT TO THIRD FRAGMENT(SignUp3)
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(TextUtils.isEmpty(addr.getText().toString()) && TextUtils.isEmpty(pin1.getText().toString())) {  //TO CHECK IF EACH EDDITTEXT IS EMPTY OR NOT?
                            addr.setError("Please enter address");
                            pin6.setError("Please add all pincode digits");
                        }
                        else if (TextUtils.isEmpty(addr.getText().toString())){  //TO CHECK IF ONLY ADDRESS EDDITTEXT IS EMPTY
                            addr.setError("Please enter address");
                        }
                        else if(TextUtils.isEmpty(pin1.getText().toString())) {  //TO CHECK IF ONLY PIN1 EDDITTEXT IS EMPTY
                            pin6.setError("Please add all pincode digits");
                        }
                        else if(TextUtils.isEmpty(pin2.getText().toString())) {  //TO CHECK IF ONLY PIN2 EDDITTEXT IS EMPTY
                            pin6.setError("Please add all pincode digits");
                        }
                        else if(TextUtils.isEmpty(pin3.getText().toString())) {  //TO CHECK IF ONLY PIN3 EDDITTEXT IS EMPTY
                            pin6.setError("Please add all pincode digits");
                        }
                        else if(TextUtils.isEmpty(pin4.getText().toString())) {  //TO CHECK IF ONLY PIN4 EDDITTEXT IS EMPTY
                            pin6.setError("Please add all pincode digits");
                        }
                        else if(TextUtils.isEmpty(pin5.getText().toString())) {  //TO CHECK IF ONLY PIN5 EDDITTEXT IS EMPTY
                            pin6.setError("Please add all pincode digits");
                        }
                        else if(TextUtils.isEmpty(pin6.getText().toString())) {  //TO CHECK IF ONLY PIN6 EDDITTEXT IS EMPTY
                            pin6.setError("Please add all pincode digits");
                        }
                        else{
//                            Dealers dealers = new Dealers(addr.getText().toString(), pin.toString());
//                            database.getReference().child("Dealers").child("first name").setValue(dealers);

                            reference.child( currentFirebaseUser.getUid()).child("Address").setValue(addr.getText().toString());
                            reference.child( currentFirebaseUser.getUid()).child("Pin code").setValue(pin1.getText().toString() + pin2.getText().toString() + pin3.getText().toString() + pin4.getText().toString() + pin5.getText().toString() + pin6.getText().toString());

                            Intent i = new Intent(SignupActivity2.this, SignupActivity3.class); // TO MOVE FROM SIGNUP TO SIGN IN
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