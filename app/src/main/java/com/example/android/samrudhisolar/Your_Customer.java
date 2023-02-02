package com.example.android.samrudhisolar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_customer);
       // list=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerviewcustomer);
        edph=findViewById(R.id.cuname);
        secur=findViewById(R.id.secustomer);
        /*secur.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                Toast.makeText(Your_Customer.this, "Siuuuuuuuuuuuuoooooooooooo", Toast.LENGTH_SHORT).show();
                String ed=edph.getText().toString();
                if(ed.length() == 10){
                    ArrayList<CustomerModel> filteredList = new ArrayList<CustomerModel>();
                    for(CustomerModel customerModel : list){
                        if(customerModel.getPhonecu().equals(ed)){
                            filteredList.add(customerModel);
                        }
                    }
                    if (!filteredList.isEmpty()) {
                        Toast.makeText(Your_Customer.this,"Response showing", Toast.LENGTH_SHORT).show();
                        recyclerView.setAdapter(new CustomerAdapter(Your_Customer.this, filteredList));
                       // adapter.notifyDataSetChanged();
//                        adapter.setData(filteredList);
//                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(Your_Customer.this, "No data found for the entered id", Toast.LENGTH_SHORT).show();
                        adapter.setData(new ArrayList<>());
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    Toast.makeText(Your_Customer.this, "Please enter a valid id", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
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
                        /*  search.setOnClickListener(new View.OnClickListener() {

                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(Your_Customer.this, "Siuuuuuuuuuuuuoooooooooooo", Toast.LENGTH_SHORT).show();
                                String ed=edph.getText().toString();
                                if(ed.length() == 6){
                                    ArrayList<CustomerModel> filteredList = new ArrayList<CustomerModel>();
                                    for(CustomerModel customerModel : list){
                                        if(customerModel.getDealerid().equals(ed)){
                                            filteredList.add(customerModel);
                                        }
                                    }
                                    if (!filteredList.isEmpty()) {
                                        Toast.makeText(Your_Customer.this,"Response showing", Toast.LENGTH_SHORT).show();
                                        recyclerView.setAdapter(new CustomerAdapter(Your_Customer.this, filteredList));
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(Your_Customer.this, "No data found for the entered phone number", Toast.LENGTH_SHORT).show();
                                        adapter.setData(new ArrayList<>());
                                        adapter.notifyDataSetChanged();
                                    }
                                }else{
                                    Toast.makeText(Your_Customer.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/
//                        adapter=new MyAdapter(MainActivity.this,list);
//
//                        recyclerView.setHasFixedSize(true);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                        recyclerView.setAdapter(adapter);


                    }


                } catch (JSONException e) {
                    Toast.makeText(Your_Customer.this,e.getMessage(),Toast.LENGTH_SHORT).show();


                }
                CustomerAdapter adapter= new CustomerAdapter(Your_Customer.this,list);
                recyclerView.setLayoutManager(new LinearLayoutManager(Your_Customer.this));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
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
}