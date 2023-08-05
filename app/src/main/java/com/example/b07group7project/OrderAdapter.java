package com.example.b07group7project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout and return a new instance of the ViewHolder.
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        // Bind the data to the views in the ViewHolder.
        holder.orderIdTextView.setText(order.getOrderId());
        holder.customerIdTextView.setText(order.getCustomerId());
        holder.productNameTextView.setText(order.getProductName());
        holder.quantityTextView.setText(String.valueOf(order.getQuantity()));
        holder.statusTextView.setText(order.getStatus());

        holder.confirmButton.setOnClickListener(v -> {
            // Handle the "Confirm" button click event here.
            // You can update the status of the order to "Confirmed" in the database.
            // Since we are not dealing with the database here, we'll just update the local data.
            order.setStatus("Confirmed");
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        // Declare your views here for each item in the RecyclerView item layout.
        TextView orderIdTextView;
        TextView customerIdTextView;
        TextView productNameTextView;
        TextView quantityTextView;
        TextView statusTextView;
        Button confirmButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize your views here based on the item layout.
            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
            customerIdTextView = itemView.findViewById(R.id.customerIdTextView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            confirmButton = itemView.findViewById(R.id.confirmButton);
        }
    }
}