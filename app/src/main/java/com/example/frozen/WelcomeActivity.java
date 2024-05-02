package com.example.frozen;

import static com.example.frozen.UtilClass.screenNavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    Button loginBtn, signUpBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        firebaseAuth = FirebaseAuth.getInstance();
        loginBtn=findViewById(R.id.btn_login);
        signUpBtn=findViewById(R.id.btn_signup);

        navigationOfViews();
    }
    private void navigationOfViews()
    {
        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this,SignUpActivity.class);
            startActivity(intent);
            screenNavigation(WelcomeActivity.this, SignUpActivity.class);
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            screenNavigation(WelcomeActivity.this,HomeActivity.class);
            finish();
        }
    }
}