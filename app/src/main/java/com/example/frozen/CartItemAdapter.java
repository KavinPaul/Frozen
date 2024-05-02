package com.example.frozen;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.frozen.databinding.RvCartItemRowBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Objects;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {
    List<FireStoreProducts> fireStoreProductsList;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    ItemRemoved itemRemoved;

    int quantity;

    public CartItemAdapter(List<FireStoreProducts> fireStoreProductsList, FirebaseAuth firebaseAuth, FirebaseFirestore firebaseFirestore, ItemRemoved itemRemoved, int quantity) {
        this.fireStoreProductsList = fireStoreProductsList;
        this.firebaseAuth = firebaseAuth;
        this.firebaseFirestore = firebaseFirestore;
        this.itemRemoved = itemRemoved;
        this.quantity = quantity;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvCartItemRowBinding binding = RvCartItemRowBinding.inflate(LayoutInflater.from
                (parent.getContext()), parent, false);
        return new CartItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        if (fireStoreProductsList != null && position < fireStoreProductsList.size()) {
            int quantity = Integer.parseInt(fireStoreProductsList.get(position).getFireStoreProductQty());
            holder.binding.tvCartProductQty.setText(String.valueOf(quantity));
        }

        assert fireStoreProductsList != null;
        FireStoreProducts products = fireStoreProductsList.get(position);
        int productQty = Integer.parseInt(products.getFireStoreProductQty());
        double productPrice = Double.parseDouble(products.getFireStoreProductPrice());

        double totalAmountOfProduct = productQty * productPrice;
        holder.binding.tvCartProductTotal.setText(String.valueOf((int) totalAmountOfProduct));

        holder.binding.tvCartProductName.setText(fireStoreProductsList.get(position).getFireStoreProductName());

        String price = String.valueOf(fireStoreProductsList.get(position).getFireStoreProductPrice());
        holder.binding.tvCartProductPrice.setText(price);

        Glide.with(holder.itemView.getContext())
                .load(fireStoreProductsList.get(position).getFireStoreProductImageUrl())
                .into(holder.binding.ivCartItemProduct);

        holder.binding.tvCartProductItemRemove.setOnClickListener(v -> {
            firebaseFirestore.collection("Users")
                    .document(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid())
                    .collection("Cart")
                    .document(fireStoreProductsList.get(holder.getAdapterPosition()).getFireStoreProductId()).delete()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            fireStoreProductsList.remove(fireStoreProductsList.get(holder.getAdapterPosition()));
                            notifyItemRemoved(holder.getAdapterPosition());
                            itemRemoved.onItemRemoved(fireStoreProductsList);
                            UtilClass.snackBar(v, "Product Removed!");
                        } else {
                            Toast.makeText(holder.itemView.getContext(), "Failed to Remove Product!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    @Override
    public int getItemCount() {
        return fireStoreProductsList.size();
    }

    static class CartItemViewHolder extends RecyclerView.ViewHolder {
        RvCartItemRowBinding binding;

        public CartItemViewHolder(RvCartItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ItemRemoved {
        void onItemRemoved(List<FireStoreProducts> itemList);
    }
}