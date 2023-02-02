package com.example.android.samrudhisolar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NewCustomerRegisterActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer_register);

        if( ! CheckNetwork.isInternetAvailable(this)) //returns true if internet available
        {
            //if there is no internet do this
            setContentView(R.layout.activity_main);
            //Toast.makeText(this,"No Internet Connection, Chris",Toast.LENGTH_LONG).show();

            new AlertDialog.Builder(this) //alert the person knowing they are about to close
                    .setTitle("No internet connection available")
                    .setMessage("Please Check you're Mobile data or Wifi network.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    //.setNegativeButton("No", null)
                    .show();
        }else {
            //Webview stuff
            webview = findViewById(R.id.webView);
            String url = "https://forms.gle/gy3ZDwe67rNJVvXm7";
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl(url);


            //webview.getSettings().setJavaScriptEnabled(true);
            //webview.getSettings().setDomStorageEnabled(true);
            //webview.getSettings().setSupportMultipleWindows(true);
            //webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            //webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
            //webview.loadUrl("https://forms.gle/gy3ZDwe67rNJVvXm7");
            //webview.setWebViewClient(new WebViewClientDemo());
            //webview.setWebChromeClient(new WebChromeClient());

        }
    }
}
class CheckNetwork {

    private static final String TAG = CheckNetwork.class.getSimpleName();

    public static boolean isInternetAvailable(Context context)
    {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            Log.d(TAG,"no internet connection");
            return false;
        } else {
            if(info.isConnected()) {
                Log.d(TAG," internet connection available...");
                return true;
            } else {
                Log.d(TAG," internet connection");
                return true;
            }
        }
    }
}