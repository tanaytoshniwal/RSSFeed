package com.example.alphabat69.rssfeed;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.textView);
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setVisibility(View.VISIBLE);
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(textView.getText().toString().concat("."));
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(textView.getText().toString().concat("."));
                                h.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(textView.getText().toString().concat("."));
                                        h.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                startActivity(new Intent(Splash.this, Selection.class));
                                            }
                                        }, 500);
                                    }
                                }, 500);
                            }
                        }, 500);
                    }
                }, 500);
            }
        }, 1500);
    }
}
