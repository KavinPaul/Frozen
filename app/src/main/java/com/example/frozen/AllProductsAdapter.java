package com.example.frozen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frozen.databinding.RvProductSearchItemRowBinding;

import java.util.List;

public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.AllProductsViewHolder> {
    Context context;
    List<AllProducts> allProductsList;

    public AllProductsAdapter(Context context, List<AllProducts> allProductsList) {
        this.context = context;
        this.allProductsList = allProductsList;
    }

    @NonNull
    @Override
    public AllProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvProductSearchItemRowBinding binding = RvProductSearchItemRowBinding
                .inflate(LayoutInflater.from(context),parent,false);
        return new AllProductsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductsViewHolder holder, int position) {

        String strPrice = String.valueOf(allProductsList.get(position).getProductPrice());
        holder.binding.tvProductPrice.setText(strPrice);

        holder.binding.tvProductName.setText(allProductsList.get(position).getProductName());

        if (!(context instanceof HomeActivity)) {
            Glide.with(context).
                    load(allProductsList.get(position)
                            .getProductImageUrl()).into(holder.binding.ivProductImage);

        } else {
            holder.binding.ivProductImage.setVisibility(View.GONE);
        }

        holder.binding.cvAllProductItemRow.setOnClickListener(v -> {
            UtilClass.screenNavigationWithDataPassing(context, ProductDetailActivity.class,"productDetails",allProductsList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return allProductsList.size();
    }

    static class AllProductsViewHolder extends RecyclerView.ViewHolder {

        RvProductSearchItemRowBinding binding;

        public AllProductsViewHolder(RvProductSearchItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
