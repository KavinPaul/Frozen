package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Bundle;
import android.content.Intent;

public class SplashActivity extends AppCompatActivity {

    static final int TIME_LIMIT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        },TIME_LIMIT);
    }
}