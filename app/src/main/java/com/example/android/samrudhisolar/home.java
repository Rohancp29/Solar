package com.example.android.samrudhisolar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class home extends AppCompatActivity  {


    ChipNavigationBar navBar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navBar = findViewById(R.id.bottomnav);


        navBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                Fragment fragment = null;
                switch (i) {
                    case R.id.cummunity_frag:
                        fragment = new community_fragg();
                        break;

                    case R.id.home_frag:
                        fragment = new Home_Fragment();
                        break;

                    case R.id.profile_frag:
                        fragment = new profile_Fragment();
                        break;


                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container , fragment)
                            .commit();
                } else {
                    Toast.makeText(home.this , "Toast error" , Toast.LENGTH_SHORT).show();
                }
            }

        });


    }
}