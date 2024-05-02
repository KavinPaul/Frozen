package com.example.frozen;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frozen.databinding.ActivityAddAddressBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddAddressActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ActivityAddAddressBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        navigationOfViews();
    }

    private void navigationOfViews() {


        binding.btnConfirmAddress.setOnClickListener(view -> {

            if (!validateAndGetTheAddressFromUser().isEmpty()) {

                Map<String, String> hashMapAddress = new HashMap<>();
                String finalAddress = validateAndGetTheAddressFromUser();
                hashMapAddress.put("address", finalAddress);
                addAddressToFireStore(hashMapAddress);

            }
        });
    }


    private void addAddressToFireStore(Map<String, String> hashMapAddress) {

        if (hashMapAddress.containsKey("address")){
            firebaseFirestore.collection("Users")
                    .document(Objects.requireNonNull(firebaseAuth.getCurrentUser())
                            .getUid())
                    .collection("Address").add(hashMapAddress)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();

                            UtilClass.screenNavigation(AddAddressActivity.this, SelectAddressActivity.class);
                            finish();
                        }
                    });
        }
    }


    private String validateAndGetTheAddressFromUser() {

        String name = Objects.requireNonNull(binding.etAddName.getText()).toString();
        String address = Objects.requireNonNull(binding.etAddFullAddress.getText()).toString();
        String city = Objects.requireNonNull(binding.etAddCity.getText()).toString();
        String postalCode = Objects.requireNonNull(binding.etAddPostalCode.getText()).toString();
        String phoneNumber = Objects.requireNonNull(binding.etAddPhoneNumber.getText()).toString();
        String finalAddress = "";


        if (name.isEmpty()){
            Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();
        }
        else if (address.isEmpty()){
            Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();

        }else if (city.isEmpty()){
            Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();

        }else if (postalCode.isEmpty()){
            Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();

        }else if (phoneNumber.isEmpty()) {
            Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();

        }else {
            finalAddress = name+", " + address + ", " + city +", " + postalCode + ", " + phoneNumber;
        }
        return finalAddress;
    }
}