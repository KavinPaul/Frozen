package com.example.frozen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frozen.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    FeaturedAdapter featuredAdapter;
    List<Featured> featuredList;

    BestSellerAdapter bestSellerAdapter;
    List<BestSeller> bestSellerList;

    FirebaseFirestore firebaseFirestore;
    FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        firebaseFirestore = FirebaseFirestore.getInstance();

        initRecyclerView();
        setNavigationOfViews();

        getCategoryDataFromFireStore();
        getFeaturedDataFromFireStore();
        getBestSellerDataFromFireStore();

        return view;
    }

    private void setNavigationOfViews() {
        binding.tvSeeAllCategory.setOnClickListener(v -> {
            UtilClass.screenNavigation(requireContext(), AllProductsActivity.class);
        });

        binding.tvSeeAllFeatured.setOnClickListener(v -> {
            UtilClass.screenNavigation(requireContext(), AllProductsActivity.class);
        });

        binding.tvSeeAllBestSeller.setOnClickListener(v -> {
            UtilClass.screenNavigation(requireContext(), AllProductsActivity.class);
        });
    }

    private void initRecyclerView() {
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(requireContext(), categoryList);
        binding.rvCategories.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.rvCategories.setAdapter(categoryAdapter);

        featuredList = new ArrayList<>();
        featuredAdapter = new FeaturedAdapter(requireContext(), featuredList);
        binding.rvFeatured.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.rvFeatured.setAdapter(featuredAdapter);

        bestSellerList = new ArrayList<>();
        bestSellerAdapter = new BestSellerAdapter(requireContext(), bestSellerList);
        binding.rvBestSeller.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));
        binding.rvBestSeller.setAdapter(bestSellerAdapter);
    }

    private void getCategoryDataFromFireStore() {
        firebaseFirestore.collection("Category").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Category category = document.toObject(Category.class);
                    categoryList.add(category);
                }
                categoryAdapter.notifyDataSetChanged();
            } else {
                Log.e(TAG, "Error getting documents: ", task.getException());
            }
        });
    }

    private void getFeaturedDataFromFireStore() {
        firebaseFirestore.collection("Featured").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Featured featured = document.toObject(Featured.class);
                    featuredList.add(featured);
                }
                featuredAdapter.notifyDataSetChanged();
            } else {
                Log.e(TAG, "Error getting documents: ", task.getException());
            }
        });
    }

    private void getBestSellerDataFromFireStore() {
        firebaseFirestore.collection("BestSeller").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    BestSeller bestSeller = document.toObject(BestSeller.class);
                    bestSellerList.add(bestSeller);
                }
                bestSellerAdapter.notifyDataSetChanged();
            } else {
                Log.e(TAG, "Error getting documents: ", task.getException());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}