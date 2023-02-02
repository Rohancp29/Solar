package com.example.android.samrudhisolar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.samrudhisolar.utility.NetworkChangeListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.FileUtils;
import com.iceteck.silicompressorr.SiliCompressor;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    TextView loc, dea_names ;
    ConstraintLayout layout ;

    private Button reg_customer, photobtn;
    EditText u_name, u_mobile, u_address , d_name, d_mobile, c_reg_number;
    Uri filepath;
    TextView u_aadhar ;
    Bitmap bitmap;
    ImageView solarpic;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressDialog pd;
    ProgressDialog p;
    Spinner product, quantity  ;
    ArrayList<String> product_array;
    ArrayAdapter<String> arrayAdapter_product, arrayAdapter_quantity_array_other ;
    ArrayList<String> quantity_array;
    ArrayList<String> pump_hp;
    ArrayList<String> total_quantity;
    FusedLocationProviderClient fusedLocationProviderClient ;
    DatabaseReference databaseReference ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.color)) ;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fusedLocationProviderClient = LocationServices .getFusedLocationProviderClient(this)  ;



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

            } else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        }


        u_name = findViewById(R.id.name2);
        u_mobile = findViewById(R.id.mobile2);
        u_address = findViewById(R.id.address2);
        u_aadhar = findViewById(R.id.aadhar2);
        reg_customer = findViewById(R.id.btnadduser2);
        product = findViewById(R.id.productspinner2);
        quantity = findViewById(R.id.quantityspinner2);
        d_mobile = findViewById(R.id.delaermobile);
        c_reg_number = findViewById(R.id.serialnumber);
        photobtn = findViewById(R.id.photobutton);
        solarpic = findViewById(R.id.solarphoto);
        loc = findViewById(R.id.getlocation);
        dea_names = findViewById( R.id.dealersnames) ;
        Calendar calendar = Calendar.getInstance();
        layout  =findViewById(R.id.mainlayout3) ;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);
        String s = MainActivity2.cname .getText().toString() ;
        s = s.replace(".", "_") ;
        dea_names.setText (s);
        mediaPlayer = MediaPlayer.create(MainActivity3.this, R.raw.confirm_dealer_name) ;
        mediaPlayer.start();



        u_aadhar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this, R.style.DatePicker, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String dates = day + "/" + month + "/" + year;
                        u_aadhar.setText(dates);

                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });


        product_array = new ArrayList<>();
        product_array.add("FRP");
        product_array.add("SS");
        product_array.add("GI");
        product_array.add("Solar Pump");
        product_array.add("Other");

        quantity_array = new ArrayList<>();
        quantity_array.add("100 L");
        quantity_array.add("150 L");
        quantity_array.add("200 L");
        quantity_array.add("250 L");
        quantity_array.add("300 L");
        quantity_array.add("500 L");

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



        arrayAdapter_product = new ArrayAdapter<>(MainActivity3.this, R.layout.spinner_color, product_array);
        arrayAdapter_product.setDropDownViewResource(R.layout.spinner_items);
        product.setAdapter(arrayAdapter_product);
        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3) {
                    arrayAdapter_quantity_array_other = new ArrayAdapter<>(MainActivity3.this, R.layout.spinner_color, pump_hp);
                    arrayAdapter_quantity_array_other.setDropDownViewResource(R.layout.spinner_items);
                } else if (position == 4) {
                    arrayAdapter_quantity_array_other = new ArrayAdapter<>(MainActivity3.this, R.layout.spinner_color, total_quantity);
                    arrayAdapter_quantity_array_other.setDropDownViewResource(R.layout.spinner_items);
                } else {
                    arrayAdapter_quantity_array_other = new ArrayAdapter<>(MainActivity3.this, R.layout.spinner_color, quantity_array);
                    arrayAdapter_quantity_array_other.setDropDownViewResource(R.layout.spinner_items);
                }
                quantity.setAdapter(arrayAdapter_quantity_array_other);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        arrayAdapter_quantity_array_other = new ArrayAdapter<>(MainActivity3.this, R.layout.spinner_color, quantity_array);
        arrayAdapter_quantity_array_other.setDropDownViewResource(R.layout.spinner_items);
        quantity.setAdapter(arrayAdapter_quantity_array_other);

        photobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent photo = new Intent(Intent.ACTION_PICK);
                                photo.setType("image/*");
                                startActivityForResult(Intent.createChooser(photo, "please select image"), 1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {


                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();

                            }
                        }).check();


            }
        });

        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission( MainActivity3.this , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity3.this , new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION
                    }, 100);
                }
                else {
                    p = new ProgressDialog(MainActivity3.this);
                    p.show();
                    p.setContentView(R.layout.progressdialog);
                    p.setCancelable(true);
                    p.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    getLocation();
                }


            }
        });

        reg_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = u_name.getText().toString();
                String mobile = u_mobile.getText().toString();
                String aadhar = u_aadhar.getText().toString();
                String address = u_address.getText().toString();
                String ser_num = c_reg_number.getText().toString();


                if (name.isEmpty()) {
                    mediaPlayer = MediaPlayer.create( MainActivity3.this, R.raw.customer_name) ;
                    mediaPlayer.start() ;
                    u_name.setError("Enter Customer Name");
                    return;

                } else if (mobile.isEmpty()) {
                    mediaPlayer = MediaPlayer.create( MainActivity3.this, R.raw.contact_number) ;
                    mediaPlayer.start() ;
                    u_mobile.setError("Enter Customer Mobile");
                    return;
                } else if (address.isEmpty()) {
                    mediaPlayer = MediaPlayer.create( MainActivity3.this, R.raw.address) ;
                    mediaPlayer.start() ;
                    u_address.setError("Enter Customer Address");
                    return;
                } else if (aadhar.isEmpty()) {
                    mediaPlayer = MediaPlayer.create( MainActivity3.this, R.raw.installation_date) ;
                    mediaPlayer.start() ;
                    u_aadhar.setError("Enter Installation Date");
                    return;
                } else if (ser_num.isEmpty()) {
                    mediaPlayer = MediaPlayer.create( MainActivity3.this, R.raw.seriasl_number) ;
                    mediaPlayer.start() ;
                    c_reg_number.setError("Enter system serial number");
                    return;
                } else if (d_mobile.getText().toString().isEmpty()) {
                    mediaPlayer = MediaPlayer.create( MainActivity3.this, R.raw.dealer_contact) ;
                    mediaPlayer.start() ;
                    d_mobile.setError("Enter dealer mobile number");
                    return;
                }
                 else{
                    Date dt = Calendar.getInstance().getTime();
                    String currentDate = dt.toString();

                    String selected_product = product.getSelectedItem().toString();
                    String selected_quantity = quantity.getSelectedItem().toString();

                    pd = new ProgressDialog(MainActivity3.this);
                    pd.show();
                    pd.setContentView(R.layout.progressdialog);
                    pd.setCancelable(false);
                    pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    HashMap<String, Object> pq = new HashMap<>();
                    pq.put("name", name);
                    pq.put("mobile", mobile);
                    pq.put("address", address);
                    pq.put("number", ser_num);
                    pq.put("dealer", d_mobile.getText().toString());
                    pq.put("device", selected_product);
                    pq.put("LPD", selected_quantity);
                    pq.put("dates", aadhar);


                    uploadimage();


                    FirebaseDatabase.getInstance().getReference().child("Dealers").child(dea_names.getText().toString()).child(currentDate).updateChildren(pq).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                pd.dismiss();

                                try {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(mobile, null, "Thank you " + name + " for choosing our solar system\nDevice: " + selected_product + " " + selected_quantity + "\nSystem Serial Number : " + ser_num + "\nInstallation Date: " + aadhar + "\n\nwww.samrudhisolar.com", null, null);
                                    smsManager.sendTextMessage("9763152452", null, "Service successfully provided to " + name + "\nDevice : " + selected_product + " " + selected_quantity + "\nSystem serial number : " + ser_num + "\nInstallation Date: " + aadhar, null, null);
                                    smsManager.sendTextMessage("9119558899", null, "Service successfully provided to " + name + "\nDevice : " + selected_product + " " + selected_quantity + "\nSystem serial number : " + ser_num + "\nInstallation Date: " + aadhar, null, null);

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "failed to send message, please contact the office", Toast.LENGTH_LONG).show();
                                }

                                startActivity(new Intent(MainActivity3.this, finalsubmit.class));
                                finish();
                            } else {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "Something went wrong, please try again", Toast.LENGTH_LONG).show();

                            }

                        }
                    });
                }
            }
        });

    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult() ;
                if (location !=null) {
                    Geocoder geocoder = new Geocoder( MainActivity3.this, Locale.getDefault())  ;
                    try {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1) ;
                        u_address.setText(addresses.get(0).getAddressLine(0)); 

                        p.dismiss();

                    } catch (Exception e) {

                    }

                }
                else {
                    mediaPlayer = MediaPlayer.create( MainActivity3.this, R.raw.location) ;
                    mediaPlayer.start();

                    Toast toast = Toast.makeText(MainActivity3.this, "Please turn on the location and try again or enter the address manually", Toast.LENGTH_LONG) ;
                    toast.show();
                    p.dismiss();
                }

            }
        }) ;


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==1 && resultCode==RESULT_OK) {
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath) ;
                bitmap = BitmapFactory.decodeStream(inputStream);
                solarpic.setImageBitmap(bitmap);

            } catch (Exception ex) {

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadimage() {

        try {
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference uploader = firebaseStorage.getReference().child( dea_names .getText() .toString()).child(u_name.getText().toString().toLowerCase()+".jpg");
            Bitmap bmp = MediaStore.Images.Media.getBitmap(getContentResolver() , filepath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 25, baos);
            byte[] data = baos.toByteArray();


            uploader.putBytes(data ).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText( getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                }
            }) ;
        } catch (Exception exception) {

        }

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
