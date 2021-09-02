package com.example.mysecondapp.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.model.Toy;

import java.util.List;


public class CustomAdapterUpdateToyPrice extends RecyclerView.Adapter<CustomAdapterUpdateToyPrice.ViewHolder> {
    // Define a custom click listener interface
    public interface OnItemClickListener {
        void onItemClick(Toy toy);
    }
    private List<Toy> toys;
    private final OnItemClickListener listener;

    public CustomAdapterUpdateToyPrice(List<Toy> toys, OnItemClickListener listener){
        this.toys = toys;
        this.listener = listener;
    }

    @Override
    public CustomAdapterUpdateToyPrice.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_update_toy_price, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(CustomAdapterUpdateToyPrice.ViewHolder holder, int position) {
        final Toy toy = toys.get(position);
        holder.textView.setText(this.toys.get(position).getName());
        holder.price.setText("KSH "+this.toys.get(position).getPrice());
        // Attach a click listener to the add price button
        holder.addPriceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(toy);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.toys.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView price;
        private ImageView addPriceButton;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.tvToyName);
            this.price = view.findViewById(R.id.tvToyPrice);
            // get a reference to the addPriceButton in the view hierarchy.
            this.addPriceButton = view.findViewById(R.id.addPriceButton);
        }

    }
}