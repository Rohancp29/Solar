package com.example.android.samrudhisolar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Your_Customer extends AppCompatActivity {
    private RecyclerView recyclerView;
    public List<CustomerModel> list;
    private CustomerAdapter adapter;
    private EditText edph;
    private Button secur;
    private TextView DealerID;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_customer);
       // list=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerviewcustomer);
        edph=findViewById(R.id.searchcustomeret);
        secur=findViewById(R.id.searchcunamebt);
        DealerID=findViewById(R.id.dealeridtv);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentFirebaseUser != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Dealers").child(currentFirebaseUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DealerID.setText(snapshot.child("Validation code").getValue(String.class));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(Your_Customer.this, "User is not logged in", Toast.LENGTH_SHORT).show();
        }


        getData();
    }

    private void getData() {
        String url="https://script.google.com/macros/s/AKfycbyD5PwU1CPIH3GxJo_QmGFHzB7Hx-8vndZ-no4x9rI25F28_Ttur7LgI_feTJ2uTziR/exec?action=get";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<CustomerModel> list =new ArrayList<>();
                try {
                    JSONArray jsonArray=response.getJSONArray("items");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject object=jsonArray.getJSONObject(i);
                        String dealerid=object.getString("Dealer ID");
                        String dealername=object.getString("Dealers Name");
                        String namecu= object.optString("Customer Name","");
                        String emailcu = object.getString("Customer Email Address");
                        String phonecu=object.getString("Number");
                        String addresscu=object.getString("Customer Address");
                        String solarcapacity=object.getString("Capacity");
                        String systemserialno=object.getString("System Serial Number");
                        String dateofinstall=object.getString("Date Of Installation");
                        String imagecu=object.getString("Photo");

                        list.add(new CustomerModel(dealerid,dealername,namecu,emailcu,addresscu,phonecu,imagecu,solarcapacity,systemserialno,dateofinstall));
                        recyclerView.setAdapter(new CustomerAdapter(Your_Customer.this,list));



                    }
                    String dealerId = DealerID.getText().toString();
                    Toast.makeText(Your_Customer.this, "DDDDDDDD"+dealerId, Toast.LENGTH_SHORT).show();

                    List<CustomerModel> filteredList = new ArrayList<>();
                    for (CustomerModel customer : list) {
                       // Toast.makeText(Your_Customer.this, "apidealer"+customer.getDealerid(), Toast.LENGTH_SHORT).show();
                        if (customer.getDealerid().equals(dealerId)) {

                            filteredList.add(customer);
                        }

                    }
                    if(!filteredList.isEmpty()) {
                        Toast.makeText(Your_Customer.this, "Data showing", Toast.LENGTH_SHORT).show();
                        // Set the filtered list as the data source for the recycler view
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Your_Customer.this));
                        recyclerView.setAdapter(new CustomerAdapter(Your_Customer.this, filteredList));
                        // adapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(Your_Customer.this, "No data found", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(Your_Customer.this,e.getMessage(),Toast.LENGTH_SHORT).show();


                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Your_Customer.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }
        );
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private void filterData(List<CustomerModel> list) {

    }
}