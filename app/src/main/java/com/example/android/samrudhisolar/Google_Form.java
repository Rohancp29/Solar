package com.example.android.samrudhisolar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.samrudhisolar.utility.NetworkChangeListener;

public class Google_Form extends AppCompatActivity {
    WebView wvgf;
    ProgressDialog pr;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_form);
        wvgf=findViewById(R.id.webviewgf);

        pr = new ProgressDialog(Google_Form.this);
        pr.show();
        pr.setContentView(R.layout.progressdialog);
        pr.setCancelable(false);
        pr.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        wvgf.setWebViewClient(new WebViewClient() {


            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                wvgf.loadUrl("about:blank");

            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return !URLUtil.isNetworkUrl(url);
            }


        });
        wvgf.loadUrl("https://forms.gle/wNRxmcvtHLpBGJh66");
        WebSettings webSettings=wvgf.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);


        pr.dismiss();
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
        if (wvgf.canGoBack()) {
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