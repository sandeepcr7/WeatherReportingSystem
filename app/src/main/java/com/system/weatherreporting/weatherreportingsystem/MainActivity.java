package com.system.weatherreporting.weatherreportingsystem;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SwipeRefreshLayout swipe;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadWeb();
            }
        });
        LoadWeb();

    }

    public void LoadWeb() {

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        swipe.setRefreshing(true);
        webView.getSettings().setAppCacheEnabled(true);

        webView.loadUrl("http://www.computronicslab.com/iot/weather_reporting/show.php");
        webView.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

//              webView.loadUrl("file:///android_asset/error.html");
//              Toast.makeText(getApplicationContext(),"no Internet",Toast.LENGTH_LONG).show();
                setContentView(R.layout.nointernet);
            }

            public void onPageFinished(WebView view, String url) {

                //Hide the SwipeReefreshLayout

                swipe.setRefreshing(false);
            }

        });
    }

}


