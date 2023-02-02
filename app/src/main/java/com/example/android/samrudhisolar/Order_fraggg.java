package com.example.android.samrudhisolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.samrudhisolar.utility.NetworkChangeListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Order_fraggg extends AppCompatActivity {

    EditText dealer_mobile, dealer_address;
    Button place_order;
    TextView dealer_name ;
    ProgressDialog p;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    Spinner product, quantity, productquantity;
    ArrayList<String> product_array;
    ArrayAdapter<String> arrayAdapter_product, arrayAdapter_quantity_array_other, arrayadapter_quantity;
    ArrayList<String> quantity_array;
    ArrayList<String> total_quantity;
    ArrayList<String> pump_hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fraggg);

      /*  androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.color));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        dealer_name = findViewById(R.id.dealersnames2);
        dealer_mobile = findViewById(R.id.dealermobile2);
        dealer_address = findViewById(R.id.dealeraddress2);
        place_order = findViewById(R.id.btnsubmit2);
        product = findViewById(R.id.productspinner);
        quantity = findViewById(R.id.quantityspinner);
        productquantity = findViewById(R.id.quantityspinner3);

        /*String s2 = Home_Fragment.cname .getText().toString() ;
        s2 = s2.replace(".", "_") ;
        dealer_name.setText (s2);*/


        product_array = new ArrayList<>();
        product_array.add("FRP");
        product_array.add("SS");
        product_array.add("GI");
        product_array.add("Solar Pump");
        product_array.add("Other");

        total_quantity = new ArrayList<>();
        total_quantity.add("1");
        total_quantity.add("2");
        total_quantity.add("3");
        total_quantity.add("4");
        total_quantity.add("5");
        total_quantity.add("6");
        total_quantity.add("7");
        total_quantity.add("8");
        total_quantity.add("9");
        total_quantity.add("10");


        quantity_array = new ArrayList<>();
        quantity_array.add("100 L");
        quantity_array.add("150 L");
        quantity_array.add("200 L");
        quantity_array.add("250 L");
        quantity_array.add("300 L");
        quantity_array.add("500 L");

        pump_hp = new ArrayList<>();
        pump_hp.add("1 HP");
        pump_hp.add("2 HP");
        pump_hp.add("3 HP");
        pump_hp.add("5 HP");
        pump_hp.add("7.5 HP");
        pump_hp.add("10 HP");
        pump_hp.add("12 HP");
        pump_hp.add("9 HP");
        pump_hp.add("Other");


        arrayAdapter_product = new ArrayAdapter<>(Order_fraggg.this, R.layout.spinner_color, product_array);
        arrayAdapter_product.setDropDownViewResource(R.layout.spinner_items);
        product.setAdapter(arrayAdapter_product);

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3) {
                    arrayAdapter_quantity_array_other = new ArrayAdapter<>(Order_fraggg.this, R.layout.spinner_color, pump_hp);
                    arrayAdapter_quantity_array_other.setDropDownViewResource(R.layout.spinner_items);
                }
                else if (position == 4) {
                    arrayAdapter_quantity_array_other = new ArrayAdapter<>(Order_fraggg.this, R.layout.spinner_color , total_quantity);
                    arrayAdapter_quantity_array_other.setDropDownViewResource(R.layout.spinner_items);
                }
                else {
                    arrayAdapter_quantity_array_other = new ArrayAdapter<>(Order_fraggg.this, R.layout.spinner_color, quantity_array);
                    arrayAdapter_quantity_array_other.setDropDownViewResource(R.layout.spinner_items);
                }

                quantity.setAdapter(arrayAdapter_quantity_array_other);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        arrayadapter_quantity = new ArrayAdapter<>(Order_fraggg.this, R.layout.spinner_color, total_quantity);
        arrayadapter_quantity.setDropDownViewResource(R.layout.spinner_items);
        productquantity.setAdapter(arrayadapter_quantity);

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mobile = dealer_mobile.getText().toString();
                String address = dealer_address.getText().toString();



                if (mobile.isEmpty()) {
                    dealer_mobile.setError("enter dealer mobile number");
                    return;
                } else if (address.isEmpty()) {
                    dealer_address.setError("enter dealer address");
                    return;
                } else {
                    Date dt = Calendar.getInstance().getTime();
                    String currentDate = dt.toString();

                    String selected_product = product.getSelectedItem().toString();
                    String selected_quantity = quantity.getSelectedItem().toString();
                    String selected_total_quantity = productquantity.getSelectedItem().toString();

                    p = new ProgressDialog(Order_fraggg.this);
                    p.show();
                    p.setContentView(R.layout.progressdialog);
                    p.setCancelable(false);
                    p.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    HashMap<String, Object> pq = new HashMap<>();
                    pq.put("Dealer Name", dealer_name.getText().toString());
                    pq.put("Dealer Mobile", mobile);
                    pq.put("Dealer address", address);
                    pq.put("Device", selected_product);
                    pq.put("LPD", selected_quantity);
                    pq.put("Quantity", selected_total_quantity);


                    FirebaseDatabase.getInstance().getReference().child("Dealers Orders").child( dealer_name.getText().toString() ).child(currentDate).updateChildren(pq).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                p.dismiss();

                                try {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage("9763152452", null, "Dealer name : " + dealer_name.getText().toString() + "\nDealer Mobile : " + mobile+ "\nAddress : " + address +"\nRequirement -:\nDevice : " + selected_product + " " + selected_quantity + "\nTotal qunatity : "+selected_total_quantity, null, null);
                                    smsManager.sendTextMessage("9119558899", null, "Dealer name : " + dealer_name.getText().toString() + "\nDealer Mobile : " + mobile+ "\nAddress : " + address +"\nRequirement -:\nDevice : " + selected_product + " " + selected_quantity + "\nTotal qunatity : "+selected_total_quantity, null, null);

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "failed to send message, please contact the office", Toast.LENGTH_LONG).show();
                                }

                                startActivity(new Intent(Order_fraggg.this, ordersubmit.class));
                                finish();

                            } else {
                                p.dismiss();
                                Toast.makeText(getApplicationContext(), "Something went wrong, please try again", Toast.LENGTH_LONG).show();
                                onBackPressed();
                                finish();

                            }

                        }
                    });
                }

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