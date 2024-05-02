package com.example.frozen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {
    Context context;
    List<Featured> featuredList;

    public FeaturedAdapter(Context context, List<Featured> featuredList) {
        this.context = context;
        this.featuredList = featuredList;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_featured_item_row,parent,false);
        return new FeaturedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        Glide.with(context)
                .load(featuredList.get(position)
                        .getFeaturedImageUrl()).into(holder.featuredImage);

        String strPrice = featuredList.get(position).getFeaturedProductPrice();
        holder.featuredProductPrice.setText(strPrice);
        holder.featuredProductPrice.setText(featuredList.get(position).getFeaturedProductPrice());

        holder.featuredProductName.setText(featuredList.get(position).getFeaturedProductName());

        holder.featuredItemRowCl.setOnClickListener(v -> {
            UtilClass.screenNavigationWithDataPassing(context, ProductDetailActivity.class,
                    "productDetails",featuredList.get(position));
        });
    }

    @Override
    public int getItemCount() {

        return featuredList.size();
    }

    static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout featuredItemRowCl;

        ImageView featuredImage;
        TextView featuredProductPrice, featuredProductName;
        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            featuredItemRowCl = itemView.findViewById(R.id.cl_featured_item_row);
            featuredImage = itemView.findViewById(R.id.iv_featured_image);
            featuredProductName = itemView.findViewById(R.id.tv_featured_product_name);
            featuredProductPrice = itemView.findViewById(R.id.tv_featured_product_price);
        }
    }
}
