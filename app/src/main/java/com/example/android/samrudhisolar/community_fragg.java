package com.example.android.samrudhisolar;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link community_fragg#newInstance} factory method to
 * create an instance of this fragment.
 */
public class community_fragg extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    TextView tv;
    private RecyclerView recyclerView;
    public List<Model> list;
    private MyAdaptercommunity adapter;
  //  private EditText edph;
  //  private Button search1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public community_fragg() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment community_fragg.
     */
    // TODO: Rename and change types and number of parameters
    public static community_fragg newInstance(String param1 , String param2) {
        community_fragg fragment = new community_fragg();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1 , param1);
        args.putString(ARG_PARAM2 , param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

          //  list=new ArrayList<>();
            recyclerView=view.findViewById(R.id.recyclerview);
          //  edph=view.findViewById(R.id.ph);
          //  search1=view.findViewById(R.id.se);
          //  adapter=new MyAdaptercommunity(getActivity(),list);
           // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            getData();
        }

    }

    private void getData() {
        String url="https://script.google.com/macros/s/AKfycbyD5PwU1CPIH3GxJo_QmGFHzB7Hx-8vndZ-no4x9rI25F28_Ttur7LgI_feTJ2uTziR/exec?action=get";
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Model> list =new ArrayList<>();
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

                        list.add(new Model(dealerid,dealername,namecu,emailcu,addresscu,phonecu,imagecu,solarcapacity,systemserialno,dateofinstall));
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
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();


                }
                MyAdaptercommunity adapter= new MyAdaptercommunity(getActivity(),list);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }
        );
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        queue.add(jsonObjectRequest);
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community_fragg , container , false);
    }
}