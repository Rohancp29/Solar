package com.example.android.samrudhisolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.samrudhisolar.utility.NetworkChangeListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView forgotpassword , joinnow;
    EditText names , password;
    private Button login;
    private FirebaseAuth fAuth;
    ProgressDialog progressDialog ;
    AlertDialog.Builder builder;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

            }

            else {
                requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
            }
        }


        names = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.btnlogin);
        forgotpassword = findViewById(R.id.forgot) ;
        fAuth = FirebaseAuth.getInstance() ;
        joinnow = findViewById(R.id.join_now);

        names.setCursorVisible(true);
        password.setCursorVisible(true);

        builder = new AlertDialog.Builder(this);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class));
            }
        });

        joinnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivity4.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (names.getText().toString().isEmpty()) {
                    names.setError("Enter Username");
                    return;
                }

                else if (password.getText().toString().isEmpty()) {
                    password.setError("Enter Password");
                    return;
                }
                else if (!names.getText().toString().isEmpty() && ! password.getText().toString().isEmpty()) {

                 /*   progressDialog = new ProgressDialog(MainActivity.this) ;
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progressdialog);
                    progressDialog.setCancelable(false);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);*/
                    String txt_email=names.getText().toString();
                    String txt_pass=password.getText().toString();

                    loginuser(txt_email,txt_pass);

                   /* fAuth.signInWithEmailAndPassword(names.getText().toString(), password.getText().toString()).addOnCompleteListener( MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_SHORT).show();

                                Intent it = new Intent(getApplicationContext(), home.class);
                                startActivity(it);
                                progressDialog.dismiss();
                                names.setText("");
                                password.setText("");
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "login failed, Please check the UserId and Password", Toast.LENGTH_SHORT).show();
                                names.setText("");
                                password.setText("");
                            }

                        }
                    }) ;*/

                }

                else {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void loginuser(String email, String password) {
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,home.class));
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login unsuccessfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.aboutus) {
            startActivity(new Intent(MainActivity.this, Aboutus.class))  ;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION ) ;
        registerReceiver(networkChangeListener, filter)  ;
        super.onStart();

       if (fAuth.getCurrentUser()!=null) {
            startActivity( new Intent(MainActivity.this, home.class));
            finish();

        }

    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}