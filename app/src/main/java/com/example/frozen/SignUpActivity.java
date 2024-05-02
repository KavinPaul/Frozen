package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import static com.example.frozen.UtilClass.screenNavigation;
import static com.example.frozen.UtilClass.snackBar;

import android.view.View;
import android.widget.Toast;

import com.example.frozen.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        navigationOfViews();
    }
    private void navigationOfViews() {
        binding.btnSignup.setOnClickListener(v -> {
            signUpValidation(v);
        });

        binding.tvLogin.setOnClickListener(v -> {
            screenNavigation(SignUpActivity.this, LoginActivity.class);
            finish();
        });
    }

    private void signUpValidation(View view) {

        String userName = Objects.requireNonNull(binding.etName.getText()).toString();
        String userEmail = Objects.requireNonNull(binding.etEmail.getText()).toString();
        String userPassword = Objects.requireNonNull(binding.etPassword.getText()).toString();

        if(!userName.isEmpty() && !userEmail.isEmpty() && !userPassword.isEmpty()) {

            mAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    screenNavigation(SignUpActivity.this, HomeActivity.class);
                    finish();
                }
                else {
                    Toast.makeText(this, "Failed To Register", Toast.LENGTH_SHORT).show();
                }
            });
        }  else {
            snackBar(view,"Please Fill All The Details");
        }
    }
}