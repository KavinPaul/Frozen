package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import static com.example.frozen.UtilClass.screenNavigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.frozen.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    boolean isFragmentVisible = false;
    Fragment homeFragment;

    AllProductsAdapter allProductsAdapter;
    List<AllProducts> allProductsList;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();

        homeFragment = new HomeFragment();

        loadFragment(homeFragment);

        initRecyclerView();
        searchFilter();

    }

    private void initRecyclerView() {

        allProductsList = new ArrayList<>();
        allProductsAdapter = new AllProductsAdapter(HomeActivity.this, allProductsList);
        binding.rvProductSearch.setLayoutManager(new LinearLayoutManager(this));
        binding.rvProductSearch.setAdapter(allProductsAdapter);

        setVisibilityVisibleToFragmentHomeContainer();
        setVisibilityGoneToActivityRvProduct();
    }

    private void searchFilter() {

        binding.etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void afterTextChanged(Editable editTextUserInput) {
                String searchQuery = editTextUserInput.toString();
                if (searchQuery.isEmpty()) {
                    allProductsList.clear();
                    allProductsAdapter.notifyDataSetChanged();
                }
                binding.ivSearchIcon.setOnClickListener(v -> {
                    searchProduct(searchQuery);
                });

            }
        });
    }

    private void searchProduct(String searchedText) {
        if (!searchedText.isEmpty()) {
            firebaseFirestore.collection("AllProducts")
                    .whereGreaterThanOrEqualTo("productName", searchedText)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                AllProducts allProducts = documentSnapshot.toObject(AllProducts.class);
                                allProductsList.add(allProducts);
                                setVisibilityVisibleToActivityRvProduct();
                                setVisibilityGoneToFragmentHomeContainer();
                                allProductsAdapter.notifyItemChanged(Objects.requireNonNull(documentSnapshot.getData()).size());

                            }
                        } else {
                            Log.e(TAG, "search product: ", task.getException());
                        }
                    });
        }
    }

    private void setVisibilityVisibleToFragmentHomeContainer() {
        binding.homeContainer.setVisibility(View.VISIBLE);
        isFragmentVisible = false;
    }

    private void setVisibilityGoneToActivityRvProduct() {
        binding.rvProductSearch.setVisibility(View.GONE);
        isFragmentVisible = true;
    }

    private void setVisibilityGoneToFragmentHomeContainer() {
        binding.homeContainer.setVisibility(View.GONE);
        isFragmentVisible = true;
    }

    private void setVisibilityVisibleToActivityRvProduct() {
        binding.rvProductSearch.setVisibility(View.VISIBLE);
        isFragmentVisible = false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, fragment, TAG);
        transaction.addToBackStack(TAG);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_log_out) {
            firebaseAuth.signOut();
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            screenNavigation(HomeActivity.this, WelcomeActivity.class);
            finish();
        } else if (item.getItemId() == R.id.action_go_to_cart) {
            UtilClass.screenNavigation(this, CartActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }
}