package com.example.frozen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.frozen.databinding.ActivityPaymentBinding;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {

    List<FireStoreProducts> allProductsList = null;

    ActivityPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        calculateTotalAmountOfProductsFromSelectedAddressActivity();

        navigationOfViews();

    }

    private void calculateTotalAmountOfProductsFromSelectedAddressActivity() {

        allProductsList = (List<FireStoreProducts>) getIntent().getSerializableExtra("fireStoreProductList");

        double totalAmount = 0.0;
        assert allProductsList != null;
        for (FireStoreProducts fireStoreProducts: allProductsList){  // Adds all products amount
            int productQty = Integer.parseInt(fireStoreProducts.getFireStoreProductQty());
            double productPrice = Double.parseDouble(fireStoreProducts.getFireStoreProductPrice());
            totalAmount += productQty * productPrice;
        }
        binding.tvPaymentTotalValue.setText(String.valueOf(totalAmount));
    }

    private void navigationOfViews() {
        binding.btnCheckOut.setOnClickListener(v -> {
            Toast.makeText(this, "Transaction Successful", Toast.LENGTH_SHORT).show();
        });
    }
}