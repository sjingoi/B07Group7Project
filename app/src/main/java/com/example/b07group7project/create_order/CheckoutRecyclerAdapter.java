package com.example.b07group7project.create_order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;


import java.util.ArrayList;
import java.util.List;

public class CheckoutRecyclerAdapter extends RecyclerView.Adapter<CheckoutRecyclerAdapter.MyViewHolder>{
    Context context;
    ArrayList<Product> products;


    public CheckoutRecyclerAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }


    @NonNull
    public CheckoutRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Give Look To Each Row
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout. order_row_data, parent, false);

        return new CheckoutRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutRecyclerAdapter.MyViewHolder holder, int position) {
        String name = String.valueOf(products.get(position).getItemName());
        String QTY = String.valueOf(products.get(position).getQuantity());
        String price = String.valueOf((products.get(position).getPrice() * products.get(position).getQuantity()));

        holder.tvName.setText(name);
        holder.tvQTY.setText(QTY);
        holder.tvPrice.setText("$" + price);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvQTY, tvPrice;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.Prod_Name);
            tvQTY = itemView.findViewById(R.id.Prod_QTY);
            tvPrice = itemView.findViewById(R.id.Prod_Price);
        }
    }

}
