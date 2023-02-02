package com.example.android.samrudhisolar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.android.samrudhisolar.utility.NetworkChangeListener;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;


public class Home_Fragment extends Fragment {


    View view;
    ProgressDialog pr ;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    ImageSlider imageSlider;

    CardView new_customer_reg,your_customer_reg,order_material;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Home_Fragment() {

    }


    public static Home_Fragment newInstance(String param1 , String param2) {
        Home_Fragment fragment = new Home_Fragment();
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



            ArrayList<SlideModel> slideModels = new ArrayList<>();

            slideModels.add(new SlideModel(R.drawable.banner01, ScaleTypes.FIT));
            slideModels.add(new SlideModel("https://samrudhisolar.com/wp-content/uploads/2021/05/solar-roof-top-500x500-500x500-2.jpg", ScaleTypes.FIT));
            slideModels.add(new SlideModel("https://samrudhisolar.com/wp-content/uploads/2021/05/solar-water-pump-500x500-1.jpg", ScaleTypes.FIT));
            slideModels.add(new SlideModel("https://samrudhisolar.com/wp-content/uploads/2021/05/solar-lighting-500x500-1.jpg", ScaleTypes.FIT));
            imageSlider.setImageList(slideModels,ScaleTypes.FIT);

            new_customer_reg = view.findViewById(R.id.new_customer_reg);
            your_customer_reg=view.findViewById(R.id.your_customer_reg);
            order_material=view.findViewById(R.id.order_customer_reg);

           /* new_customer_reg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Helloooooooooo", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), Google_Form.class); // TO MOVE FROM SIGNUP TO SIGN IN
                    startActivity(intent);
                    ((Activity) getActivity()).overridePendingTransition(R.anim.left_anim, R.anim.right_anim); //FOR ANIMATION
                }
            });*/


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_ , container , false);
        new_customer_reg = view.findViewById(R.id.new_customer_reg);
        new_customer_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Helloooooooooo", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), warranty.class); // TO MOVE FROM SIGNUP TO SIGN IN
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(R.anim.left_anim, R.anim.right_anim); //FOR ANIMATION
            }
        });
        your_customer_reg=view.findViewById(R.id.your_customer_reg);
        your_customer_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Eiiuuuuuwwwwwwww", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Your_Customer.class); // TO MOVE FROM SIGNUP TO SIGN IN
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(R.anim.left_anim, R.anim.right_anim); //FOR ANIMATION
            }
        });
        order_material=view.findViewById(R.id.order_customer_reg);
        order_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Eiiuuuuuwwwwwwww", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),Order_fraggg.class));
              //  ((Activity) getActivity()).overridePendingTransition(R.anim.left_anim, R.anim.right_anim); //FOR ANIMATION

            }
        });

        return view;
    }

    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }*/
}