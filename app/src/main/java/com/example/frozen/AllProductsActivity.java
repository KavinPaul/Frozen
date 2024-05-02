package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.frozen.databinding.ActivityAllProductsBinding;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore. FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AllProductsActivity extends AppCompatActivity {

    private static final String TAG = "AllProductsActivity";
    AllProductsAdapter allProductsAdapter;
    List<AllProducts> allProductsList;

    FirebaseFirestore firebaseFirestore;

    ActivityAllProductsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseFirestore = FirebaseFirestore.getInstance();

        String categoryType = getIntent().getStringExtra("categoryType");
        initRecyclerView();
        getDataFromAllProducts(categoryType);
    }

    private void getDataFromAllProducts(String categoryType) {

        if (categoryType == null || categoryType.isEmpty()) {

            firebaseFirestore.collection("AllProducts")
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                AllProducts allProducts = documentSnapshot
                                        .toObject(AllProducts.class);
                                allProductsList.add(allProducts);
                                allProductsAdapter.notifyItemInserted
                                        (Objects.requireNonNull(documentSnapshot.getData()).size());
                            }
                        } else {
                            Log.e(TAG, "getDataFromAllProducts: ", task.getException());
                        }
                    });
        } else if (categoryType.equals("Chocolate")) {

            firebaseFirestore.collection("AllProducts")
                    .whereEqualTo("categoryType", "Chocolate")
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                AllProducts allProducts = documentSnapshot.toObject(AllProducts.class);
                                allProductsList.add(allProducts);
                                allProductsAdapter.notifyItemInserted(Objects.requireNonNull(documentSnapshot.getData()).size());
                            }
                        } else {
                            Log.e(TAG, "getDataFromAllProducts: ", task.getException());
                        }
                    });
        } else if (categoryType.equals("Cheesecake")) {

            firebaseFirestore.collection("AllProducts")
                    .whereEqualTo("categoryType", "Cheesecake")
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                AllProducts allProducts = documentSnapshot
                                        .toObject(AllProducts.class);
                                allProductsList.add(allProducts);
                                allProductsAdapter.notifyItemInserted
                                        (Objects.requireNonNull(documentSnapshot.getData()).size());
                            }
                        } else {
                            Log.e(TAG, "getDataFromAllProducts: ", task.getException());
                        }
                    });
        } else if (categoryType.equals("Berry")) {

            firebaseFirestore.collection("AllProducts")
                    .whereEqualTo("categoryType", "Berry")
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                AllProducts allProducts = documentSnapshot
                                        .toObject(AllProducts.class);
                                allProductsList.add(allProducts);
                                allProductsAdapter.notifyItemInserted
                                        (Objects.requireNonNull(documentSnapshot.getData()).size());
                            }
                        } else {
                            Log.e(TAG, "getDataFromAllProducts: ", task.getException());
                        }
                    });

        } else if (categoryType.equals("New")) {

            firebaseFirestore.collection("AllProducts")
                    .whereEqualTo("categoryType", "New")
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                AllProducts allProducts = documentSnapshot
                                        .toObject(AllProducts.class);
                                allProductsList.add(allProducts);
                                allProductsAdapter.notifyItemInserted
                                        (Objects.requireNonNull(documentSnapshot.getData()).size());
                            }
                        } else {
                            Log.e(TAG, "getDataFromAllProducts: ", task.getException());
                        }
                    });

        } else if (categoryType.equals("Nuts")) {

            firebaseFirestore.collection("AllProducts")
                    .whereEqualTo("categoryType", "Nuts")
                    .get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                AllProducts allProducts = documentSnapshot
                                        .toObject(AllProducts.class);
                                allProductsList.add(allProducts);
                                allProductsAdapter.notifyItemInserted
                                        (Objects.requireNonNull(documentSnapshot.getData()).size());
                            }
                        } else {
                            Log.e(TAG, "getDataFromAllProducts: ", task.getException());
                        }
                    });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        assert searchView != null;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchProduct(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void searchProduct(String query) {
        allProductsList.clear();
        firebaseFirestore.collection("AllProducts")
                .whereGreaterThanOrEqualTo("productName",query)
                .get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot documentSnapshot: task.getResult()) {
                            AllProducts allProducts = documentSnapshot.toObject(AllProducts.class);
                            allProductsList.add(allProducts);
                            allProductsAdapter.notifyItemRangeChanged(0,
                                    Objects.requireNonNull(documentSnapshot.getData()).size());
                        }
                    } else {
                        Log.e(TAG, "searchProduct: ",task.getException());
                    }
                });
    }

    private void initRecyclerView() {
        allProductsList = new ArrayList<>();
        allProductsAdapter = new AllProductsAdapter(AllProductsActivity.this,allProductsList);
        binding.rvAllProducts.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvAllProducts.setAdapter(allProductsAdapter);
    }
}