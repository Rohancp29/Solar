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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity4 extends AppCompatActivity {

    EditText mail,password,cnf_pass;
    Button btn4;
    FirebaseAuth sAuth;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("Dealers");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup4);

        mail=findViewById(R.id.email);

        password = findViewById(R.id.pass);
        cnf_pass = findViewById(R.id.cnf_pass);
        btn4 = findViewById(R.id.btn4);
        sAuth=FirebaseAuth.getInstance();

        String userPass = password.getText().toString();
        String usermail=mail.getText().toString();
        String userConPass = cnf_pass.getText().toString();
        /*Intent intent = new Intent(SignupActivity4.this, SignupActivity5.class);
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);*/

        Map<String, Object> user1 = new HashMap<>();
        user1.put("Password", userPass);
        user1.put("Confirm Password", userConPass);
        user1.put("Email",usermail);

        btn4.setOnClickListener(new View.OnClickListener() {  //TO VALIDATE AND REPLACE THE FRAGMENT TO FIFTH FRAGMENT(SignUp5)
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(cnf_pass.getText().toString())){  //TO CHECK IF BOTH EDITTEXT ARE EMPTY
                    password.setError("Please enter password");
                    cnf_pass.setError("Please enter your password");
                }

                else {

                            sAuth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        UDer ud=new UDer(mail.getText().toString(),password.getText().toString(),cnf_pass.getText().toString());
                                        String id=task.getResult().getUser().getUid();

                                        reference.child(id).setValue(ud);
//                                        Toast.makeText(SignupActivity4.this, "signup successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignupActivity4.this, SignupActivity.class));
                                    } else {
                                        Toast.makeText(SignupActivity4.this, "Oopssssss", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }



                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       // String keyid = reference.push().getKey();
                     /*   reference.child("samrudhi_solar").child("Email").setValue(mail.getText().toString());
                        reference.child("samrudhi_solar").child("Password").setValue(password.getText().toString());
                        reference.child("samrudhi_solar").child("Confirm_password").setValue(cnf_pass.getText().toString());
                   */ }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

             /*   reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(cnf_pass.getText().toString())){  //TO CHECK IF BOTH EDITTEXT ARE EMPTY
                            password.setError("Please enter password");
                            cnf_pass.setError("Please enter your password");
                        }
                        else if (TextUtils.isEmpty(password.getText().toString())){  //TO CHECK IF ONLY PASSWORD EDITTEXT IS EMPTY OR NOT
                            password.setError("Please enter password");
                        }
                        else if(TextUtils.isEmpty(cnf_pass.getText().toString())) {  //TO CHECK IF ONLY CONFIRM_PASSWORD EDITTEXT IS EMPTY OR NOT
                            cnf_pass.setError("Please re-enter your password");
                        }
                        else if(cnf_pass.equals(password)){
                            cnf_pass.setError("Confirm Password must be equal to Password.");
                        }
                        else {
                            if (password.getText().toString().length() < 8 && !isValidPassword(password.getText().toString())) {  //FOR PASSWORD VALIDATION
                                password.setError("Password not valid");
                            } else {
                                if (password.getText().toString() == cnf_pass.getText().toString())  //TO CHECK IF PASSWORD AND CONFIRM PASSWORD MATCH OR NOT?
                                    cnf_pass.setError("Dosen't match to password");
                                else {
                                    sAuth.createUserWithEmailAndPassword(usermail,userConPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){
                                                startActivity(new Intent(SignupActivity4.this,SignupActivity5.class));
                                            }
                                            else {
                                                Toast.makeText(SignupActivity4.this, "Oopssssss", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                                    reference.child("Dealers").child("Email").setValue(usermail);
                                    reference.child("Dealers").child("Password").setValue(password.getText().toString());
                                    reference.child("Dealers").child("Confirm_password").setValue(cnf_pass.getText().toString());

                                  //  Intent i = new Intent(SignupActivity4.this, SignupActivity5.class); // TO MOVE FROM SIGNUP TO SIGN IN
                                  //  startActivity(i);
                                    overridePendingTransition(R.anim.left_anim, R.anim.right_anim); //FOR ANIMATION
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
        });
    }

    public static boolean isValidPassword(final String password) {  //DEFINATION OF METHOD FOR  VALIDATION OF THE PASSWORD
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";  //VALIDATION STRING
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}