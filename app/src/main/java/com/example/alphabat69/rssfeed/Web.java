package com.example.alphabat69.rssfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Web extends AppCompatActivity {
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webview = findViewById(R.id.webview);
        String link = getIntent().getStringExtra("link");
        webview.loadUrl(link);
    }
}
