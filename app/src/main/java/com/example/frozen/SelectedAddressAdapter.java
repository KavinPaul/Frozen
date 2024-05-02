package com.example.frozen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SelectedAddressAdapter extends RecyclerView.Adapter<SelectedAddressAdapter.SelectAddressViewHolder> {
    Context context;

    List<SelectedAddress> addressList;

    RadioButton selectedRadioButton;

    public SelectedAddressAdapter(Context context, List<SelectedAddress> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public SelectAddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_search_address_item_row,
                parent, false);
        return new SelectAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectAddressViewHolder holder, int position) {
        holder.addressTv.setText(addressList.get(position).getAddress());

        RadioButton radioButton = holder.selectRbAddress;
        radioButton.setChecked(addressList.get(position).isSelected());

        radioButton.setOnClickListener(view -> {

            for (SelectedAddress selectedAddress : addressList) {
                selectedAddress.setSelected(false);
            }
            validateRadioButton(view, position);
        });
    }

    void validateRadioButton(View view, int position) {

        addressList.get(position).setSelected(true);

        if (selectedRadioButton != null && !view.equals(selectedRadioButton))
            selectedRadioButton.setChecked(false);

        selectedRadioButton = (RadioButton) view;
        selectedRadioButton.setChecked(true);

    }


    @Override
    public int getItemCount() {
        return addressList.size();
    }

    static class SelectAddressViewHolder extends RecyclerView.ViewHolder {

        TextView addressTv;
        RadioButton selectRbAddress;

        public SelectAddressViewHolder(@NonNull View itemView) {
            super(itemView);

            addressTv = itemView.findViewById(R.id.tv_address);
            selectRbAddress = itemView.findViewById(R.id.rb_select);
        }
    }
}