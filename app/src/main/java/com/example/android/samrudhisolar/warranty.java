package com.example.android.samrudhisolar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.samrudhisolar.utility.NetworkChangeListener;

public class warranty extends AppCompatActivity {
    ProgressDialog p ;

    private WebView mywebView;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warranty);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mywebView = findViewById(R.id.webview);

        p = new ProgressDialog(warranty .this);
        p.show();
        p.setContentView(R.layout.progressdialog);
        p.setCancelable(false);
        p.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        mywebView.setWebViewClient(new WebViewClient() {


            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mywebView.loadUrl("about:blank");

            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return !URLUtil.isNetworkUrl(url);
            }


        });



        mywebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSc_QlaJ6mYYYY-CxUFSASKYxFvuDGiRnOvmCZ24stRgZKdltQ/viewform?usp=sf_link");
        WebSettings webSettings=mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);


        p.dismiss();


    }

    public class mywebClient extends WebViewClient{
        private final Activity activity ;
        public mywebClient (Activity activity) {
            this.activity = activity ;
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){

            return false;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request){
            return false;
        }
    }
    @Override
    public void onBackPressed(){
        if (mywebView.canGoBack()) {
            super.onBackPressed();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter ) ;
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}
