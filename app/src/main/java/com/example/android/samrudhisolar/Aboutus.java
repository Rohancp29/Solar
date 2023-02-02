package com.example.android.samrudhisolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.color));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Element adElement = new Element();
        adElement.setTitle( "Design and Developed by Good Day Technologies, Kolhapur. (+919172713940)") ;
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Samrudhi Solar\n\n" +
                        "Shreya Enterprises is a sales and service organization to develop environmental energy resources to give the benefit to customer and services from last 8 years. Main motto of our organization is “BEST IN QUALITY AND BEST IN SERVICE”\n" +
                        "\n" +
                        "“SAMRUDHI SOLAR” is our brand in solar water heating system. Our product line is user friendly and easy to maintenance. Shreya Enterprises is located in Ujalaiwadi, Kolhapur, Maharashtra. We are a leading manufacturer and supplier of high efficiency solar water heaters and other solar energy products. Since the company was founded in 2010, Shreya Enterprises has consolidated its footprint in the renewable energy industry." +
                        "\n\n" +
                        "Shreya Enterprises is located in a facility that includes an office area and more than 20,000 square feet of production assembly and warehouse space in Ujalaiwadi, Maharashtra. The company has successfully launched the patent of FRP Inner Tank Technology solar water heater products." +
                        "\n\nOur water heating products deliver unique and comprehensive benefits and outperform any existing solar water heating product on the market. Therefore, Shreya Enterprises products offer significant advantages for hot water supply for either residential or commercial applications.\n" +
                        "\n Shreya Enterprise’s logistics management is capable of shipping containers of products either to our warehouse or directly to a distributor’s warehouse. Then, the ordered products can be delivered within 3-5 days to any location in the continental India.\n" +
                        "\n" +
                        "Samrudhi Solar")


                .addItem(new Element().setTitle("Version 5.0"))
                .addItem(adElement)
                .addWebsite("https://samrudhisolar.com/goodday-technology-pvt-ltd/")
                .addGroup("Connect with us")
                .addEmail("ppandharpatte49@gmail.com")
                .addInstagram("salman__sayyad")
                .addFacebook("ashutosh.talekar.188")


                .create() ;
        setContentView(aboutPage);

    }
}