package com.example.frozen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.categoryViewHolder> {
    Context context;
    List<Category>categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_category_item_row, parent, false);

        return new categoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position) {
        Glide.with(context).load(categoryList.get(position)
                .getCategoryImageUrl()).into(holder.categoryImage);

        holder.categoryImage.setOnClickListener(v -> {

            UtilClass.screenNavigationWithDataPassing(context, AllProductsActivity.class,
                    "categoryType",categoryList.get(position).getCategoryType());

        });


    }

    @Override
    public int getItemCount() {

        return categoryList.size();
    }

    static class categoryViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.iv_category);

        }
    }
}
