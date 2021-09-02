package com.example.mysecondapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.model.Toy;

import java.util.List;

public class CustomAdapterDeleteToyPrice extends RecyclerView.Adapter<CustomAdapterDeleteToyPrice.ViewHolder> {
    private List<Toy> tOYS;
    public CustomAdapterDeleteToyPrice(List<Toy> toys){
        this.tOYS = toys;
    }

    @Override
    public CustomAdapterDeleteToyPrice.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_delete_price, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(CustomAdapterDeleteToyPrice.ViewHolder holder, int position) {
        holder.textView.setText(this.tOYS.get(position).getName());
        holder.price.setText("KSH "+this.tOYS.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return this.tOYS.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private TextView price;
        private ImageView addPriceButton;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.textView = view.findViewById(R.id.tvToyName);
            this.price = view.findViewById(R.id.tvToyPrice);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.textView.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}