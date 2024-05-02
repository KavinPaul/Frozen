package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.frozen.databinding.ActivitySelectAddressBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectAddressActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private SelectedAddressAdapter selectAddressAdapter;
    private List<SelectedAddress> addressList;
    List<FireStoreProducts> fireStoreProductsList;

    ActivitySelectAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getProductListFromCartActivity();
        navigationOfViews();
        initRecyclerView();
        getAddressFromFireStore();

    }

    void initRecyclerView() {

        addressList = new ArrayList<>();
        binding.rvAddress.setLayoutManager(new LinearLayoutManager(SelectAddressActivity.this));
        selectAddressAdapter = new SelectedAddressAdapter(SelectAddressActivity.this, addressList);
        binding.rvAddress.setAdapter(selectAddressAdapter);

    }

    private void getProductListFromCartActivity() {

        Serializable serializableExtra = getIntent().getSerializableExtra("fireStoreProductList");

        if (serializableExtra instanceof ArrayList) {
            fireStoreProductsList = (ArrayList<FireStoreProducts>) serializableExtra;
        }
    }
    private void navigationOfViews() {

        binding.btnAddAddress.setOnClickListener(view -> {
            UtilClass.screenNavigation(SelectAddressActivity.this, AddAddressActivity.class);
            finish();
        });

        binding.btnContinuePayment.setOnClickListener(v -> {
            if (fireStoreProductsList != null && fireStoreProductsList.size()>0){

                UtilClass.screenNavigationWithDataPassingSerialize(
                        SelectAddressActivity.this, PaymentActivity.class,
                        "fireStoreProductList", (Serializable) fireStoreProductsList
                );

            }});

    }

    private void getAddressFromFireStore() {

        firebaseFirestore.collection("Users")
                .document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid())
                .collection("Address")
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        for(DocumentSnapshot documentSnapshot : task.getResult()){
                            SelectedAddress showAddress = documentSnapshot.toObject(SelectedAddress.class);
                            addressList.add(showAddress);
                            selectAddressAdapter.notifyItemInserted(Objects.requireNonNull(documentSnapshot.getData()).size());
                        }

                    }
                });
    }
}