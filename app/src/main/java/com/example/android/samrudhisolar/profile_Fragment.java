package com.example.android.samrudhisolar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class profile_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    View view;

    Button updateProfileImg;

    CircleImageView profileImg;

    // int imageSize = 227;


    DatabaseReference reference;
    FirebaseAuth auth;
    Uri imgUri;
    String myUri = "";
    StorageTask uploadTask;
    StorageReference storageReference;


    private TextView username,address,pincode,mail,phone,valid;


    public profile_Fragment() {
        // Required empty public constructor
    }


    public static profile_Fragment newInstance(String param1 , String param2) {
        profile_Fragment fragment = new profile_Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

          /*  username=view.findViewById(R.id.usertv);
            address=view.findViewById(R.id.addresstv);
            pincode=view.findViewById(R.id.pincodetv);
            mail=view.findViewById(R.id.emailtv);
            phone=view.findViewById(R.id.phonetv);
            valid=view.findViewById(R.id.validationcodetv);
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
            Toast.makeText(getActivity(), "Profile"+currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
            DatabaseReference reference = database.getReference("Dealers").child(currentFirebaseUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    username.setText(snapshot.child("First Name").getValue(String.class));
                    address.setText(snapshot.child("Address").getValue(String.class));
                    pincode.setText(snapshot.child("Pin code").getValue(String.class));
                    mail.setText(snapshot.child("mail").getValue(String.class));
                    phone.setText(snapshot.child("Mobile number").getValue(String.class));
                    valid.setText(snapshot.child("Validation code").getValue(String.class));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        username = view.findViewById(R.id.usertv);
        address = view.findViewById(R.id.addresstv);
        pincode = view.findViewById(R.id.pincodetv);
        mail = view.findViewById(R.id.emailtv);
        phone = view.findViewById(R.id.phonetv);
        valid = view.findViewById(R.id.validationcodetv);

        profileImg = view.findViewById(R.id.profile_pic);
        updateProfileImg = view.findViewById(R.id.change_profileImg);

        updateProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
//                startActivityForResult(intent, 10);

            }
        });

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentFirebaseUser != null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("Dealers").child(currentFirebaseUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String fname=snapshot.child("First name").getValue(String.class);
                    String lname=snapshot.child("Last name").getValue(String.class);
                    username.setText(fname+" "+lname);
                    address.setText(snapshot.child("Address").getValue(String.class));
                    pincode.setText(snapshot.child("Pin code").getValue(String.class));
                    mail.setText(snapshot.child("mail").getValue(String.class));
                    phone.setText(snapshot.child("Mobile number").getValue(String.class));
                    valid.setText(snapshot.child("Validation code").getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Toast.makeText(getContext(), "User is not logged in", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if(requestCode==10)
//        {
//            if(data!=null)
//            {
//                Uri uri = data.getData();
//                Bitmap bitmap = null;
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), uri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                profileImg.setImageBitmap(bitmap);
//                bitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize,false);
//                profileImg.setImageBitmap(bitmap);
//            }
//        }else {
//            Toast.makeText(getActivity().getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

}