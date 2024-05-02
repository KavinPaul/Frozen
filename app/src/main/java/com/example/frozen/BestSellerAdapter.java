package com.example.frozen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder> {
    Context context;
    List<BestSeller> bestSellerList;

    public BestSellerAdapter(Context context, List<BestSeller> bestSellerList) {
        this.context = context;
        this.bestSellerList = bestSellerList;
    }

    @NonNull
    @Override
    public BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_best_seller_item_row,parent,false);
        return new BestSellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellerViewHolder holder, int position) {

        Glide.with(context).load(bestSellerList.get(position)
                .getBestsellerImageUrl()).into(holder.bestsellerImage);

        String strPrice = bestSellerList.get(position).getBestsellerProductPrice();
        holder.bestsellerProductPrice.setText(strPrice);
        holder.bestsellerProductPrice.setText(bestSellerList.get(position).getBestsellerProductPrice());
        holder.bestsellerProductName.setText(bestSellerList.get(position).getBestsellerProductName());
        holder.bestsellerProductName.setText(bestSellerList.get(position).getBestsellerProductName());

        holder.bestSellerItemRowCl.setOnClickListener(v -> {
            UtilClass.screenNavigationWithDataPassing(context, ProductDetailActivity.class,
                    "productDetails",bestSellerList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return bestSellerList.size();
    }

    static class BestSellerViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout bestSellerItemRowCl;

        ImageView bestsellerImage;

        TextView bestsellerProductName, bestsellerProductPrice;

        public BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);
            bestSellerItemRowCl = itemView.findViewById(R.id.cl_best_seller_item_row);
            bestsellerImage = itemView.findViewById(R.id.iv_best_seller_image);
            bestsellerProductName = itemView.findViewById(R.id.tv_best_seller_product_name);
            bestsellerProductPrice = itemView.findViewById(R.id.tv_best_seller_product_price);
        }
    }
}
