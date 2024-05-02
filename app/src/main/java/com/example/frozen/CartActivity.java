package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.frozen.databinding.ActivityCartBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {

    FireStoreProducts fireStoreProducts;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    List<FireStoreProducts> fireStoreProductsList;
    CartItemAdapter cartItemAdapter;
    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerViewInit();
        getCartData();
        navigationOfViews();
    }

    private void navigationOfViews() {
        binding.btnOrderNow.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, SelectAddressActivity.class);
            intent.putExtra("fireStoreProductList", new ArrayList<>(fireStoreProductsList)); // Pass as Serializable
            startActivity(intent);
        });
    }

    private void getCartData() {
        fireStoreProductsList = new ArrayList<>(); // Initialize the list
        firebaseFirestore.collection("Users")
                .document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid())
                .collection("Cart")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentChange documentChange : task.getResult().getDocumentChanges()) {
                            String strDocumentId = documentChange.getDocument().getId();
                            if (documentChange.getDocument().contains("bestSellerProductName")) {
                                fireStoreProducts = documentChange.getDocument().toObject(BestSeller.class);
                            } else if (documentChange.getDocument().contains("featuredProductName")) {
                                fireStoreProducts = documentChange.getDocument().toObject(Featured.class);
                            } else {
                                fireStoreProducts = documentChange.getDocument().toObject(AllProducts.class);
                            }
                            fireStoreProducts.setFireStoreProductId(strDocumentId);
                            fireStoreProductsList.add(fireStoreProducts);
                        }
                        calculateTotalAmountOfProducts(fireStoreProductsList);
                        int quantity = getIntent().getIntExtra("productQty", 1);
                        cartItemAdapter = new CartItemAdapter(fireStoreProductsList, firebaseAuth,
                                firebaseFirestore, itemList -> calculateTotalAmountOfProducts(fireStoreProductsList), quantity);
                        binding.rvCart.setAdapter(cartItemAdapter);
                    } else {
                        Toast.makeText(this, "Failed To Load Cart Data", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void recyclerViewInit() {
        fireStoreProductsList = new ArrayList<>();
        binding.rvCart.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCart.setHasFixedSize(true);
    }

    private void calculateTotalAmountOfProducts(List<FireStoreProducts> fireStoreProductsList) {
        double totalAmount = 0.0;
        for (FireStoreProducts products : fireStoreProductsList) {
            int productQuantity = Integer.parseInt(products.getFireStoreProductQty());
            double productPrice = Double.parseDouble(products.getFireStoreProductPrice());
            totalAmount += productQuantity * productPrice;
        }
        binding.tvTotalValue.setText(String.valueOf(totalAmount)); // Display total amount
    }
}