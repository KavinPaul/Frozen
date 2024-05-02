package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;
import android.widget.Toast;

import static com.example.frozen.UtilClass.screenNavigation;
import com.example.frozen.databinding.ActivityLoginBinding;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        navigationOfViews();
    }

    private void navigationOfViews() {
        binding.btnLogin.setOnClickListener(v -> {

            loginValidation();
        });

        binding.tvSignup.setOnClickListener(v -> {
            screenNavigation(LoginActivity.this, SignUpActivity.class);
            finish();
        });
    }

    private void loginValidation() {

        String userEmail = Objects.requireNonNull(binding.etEmail.getText()).toString();
        String userPassword = Objects.requireNonNull(binding.etPassword.getText()).toString();

        if (!userEmail.isEmpty() && !userPassword.isEmpty()) {

            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    screenNavigation(LoginActivity.this, HomeActivity.class);
                    Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
                } else if (userPassword.length()<6) {
                    Toast.makeText(this,"Enter At Least 6 Character",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Failed To Login", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please Fill All The Details", Toast.LENGTH_SHORT).show();
        }

    }
}