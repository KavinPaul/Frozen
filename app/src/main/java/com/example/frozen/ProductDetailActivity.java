package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.frozen.databinding.ActivityProductDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class ProductDetailActivity extends AppCompatActivity {
    private static final String TAG = "ProductDetailActivity";
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getDataFromFeaturedBestSellerAndAllProductsRecyclerViews();
        showProductQtyCount();

    }

    private void showProductQtyCount() {

        binding.btnIncreaseQty.setOnClickListener(v -> {
            String currentValue = binding.btnCountQty.getText().toString();
            int qtyValue = Integer.parseInt(currentValue);
            qtyValue++;
            binding.btnCountQty.setText(String.valueOf(qtyValue));
        });

        binding.btnDecreaseQty.setOnClickListener(v -> {
            String currentValue = binding.btnCountQty.getText().toString();
            int qtyValue = Integer.parseInt(currentValue);

            if(qtyValue > 1) {
                qtyValue--;
                binding.btnCountQty.setText(String.valueOf(qtyValue));
            }
        });
    }

    private void getDataFromFeaturedBestSellerAndAllProductsRecyclerViews() {
        Featured featured = null;
        BestSeller bestSeller = null;
        AllProducts allProducts = null;

        Object object = getIntent().getParcelableExtra("productDetails");

        if (object instanceof Featured) {
            featured = (Featured) object;
        } else if (object instanceof BestSeller) {
            bestSeller = (BestSeller) object;
        } else if (object instanceof AllProducts) {
            allProducts = (AllProducts) object;
        }

        ValidateObjectContainsNullValues(featured,bestSeller,allProducts) ;
        navigationOfViews(featured,bestSeller,allProducts);
    }

    private void navigationOfViews(Featured featured, BestSeller bestSeller, AllProducts allProducts) {
        binding.btnAddToCart.setOnClickListener(v -> {

            String strQuantity = binding.btnCountQty.getText().toString();

            if (!strQuantity.isEmpty()) {
                int productQty = Integer.parseInt(strQuantity);

                if (featured != null) {
                    featured.setFeaturedProductQty(String.valueOf(productQty));
                    UtilClass.screenNavigationWithDataPassing(ProductDetailActivity.this,
                            CartActivity.class,"productQty", String.valueOf(productQty));
                    addProductToCart(featured);
                } else if (bestSeller != null) {
                    bestSeller.setBestsellerProductQty(String.valueOf(productQty));
                    UtilClass.screenNavigationWithDataPassing(ProductDetailActivity.this,
                            CartActivity.class, "productQty", String.valueOf(productQty));
                    addProductToCart(bestSeller);

                } else if (allProducts != null) {
                    allProducts.setProductQty(productQty);
                    UtilClass.screenNavigationWithDataPassing(ProductDetailActivity.this,
                            CartActivity.class, "productQty", String.valueOf(productQty));
                    addProductToCart(allProducts);
                }

            }
        });
    }

    private void addProductToCart(Object product) {

        firebaseFirestore.collection("Users")
                .document(Objects.requireNonNull(firebaseAuth.getUid()))
                .collection("Cart")
                .add(product)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Item Added to Cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed to Add Item", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void ValidateObjectContainsNullValues(Featured featured, BestSeller bestSeller, AllProducts allProducts) {
        if (featured != null) {
            getDataFromFeatured(featured);
        } else if (bestSeller != null) {
            getDataFromBestSeller(bestSeller);
        } else if (allProducts !=null) {
            getDataFromAllProducts(allProducts);

        }

    }

    private void getDataFromAllProducts(AllProducts allProducts) {
        Glide.with(ProductDetailActivity.this)
                .load(allProducts.getProductImageUrl()).into(binding.ivProductItem);
        String price = String.valueOf(allProducts.getProductPrice());
        binding.tvProductPrice.setText(price);
        binding.tvProductName.setText(allProducts.getProductName());
        binding.tvProductDescription.setText(allProducts.getProductDescription());
    }


    private void getDataFromBestSeller(BestSeller bestSeller) {
        Glide.with(ProductDetailActivity.this)
                .load(bestSeller.getBestsellerImageUrl()).into(binding.ivProductItem);
        String price = bestSeller.getBestsellerProductPrice();
        binding.tvProductPrice.setText(price);
        binding.tvProductName.setText(bestSeller.getBestsellerProductName());
        binding.tvProductDescription.setText(bestSeller.getBestsellerProductDescription());
    }

    private void getDataFromFeatured(Featured featured) {
        Glide.with(ProductDetailActivity.this)
                .load(featured.getFeaturedImageUrl()).into(binding.ivProductItem);
        String price = featured.getFeaturedProductPrice();
        binding.tvProductPrice.setText(price);
        binding.tvProductName.setText(featured.getFeaturedProductName());
        binding.tvProductDescription.setText(featured.getFeaturedProductDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_go_to_cart) {
            UtilClass.screenNavigation(ProductDetailActivity.this,CartActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }
}