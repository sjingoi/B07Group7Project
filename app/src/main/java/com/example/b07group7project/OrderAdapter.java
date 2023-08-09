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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        // Bind the data to the views in the ViewHolder.
        holder.orderIdTextView.setText("Order ID: " + order.uuid);
        holder.customerNameTextView.setText("Customer Name: " + order.customer.name);
        holder.storeNameTextView.setText("Store Name: " + order.store.getStoreName());
        holder.orderStatusTextView.setText("Status: " + order.order_complete);

        // Handle the "Confirm" button click event here.
        holder.confirmButton.setOnClickListener(v -> {
            // Implement the order confirmation logic here, Call the confirmOrder() method of  OrderRepository to confirm the order.
            // For now, assume the confirmation is successful and update the order status locally.
            order.order_complete = "true";
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderIdTextView;
        TextView customerNameTextView;
        TextView storeNameTextView;
        TextView orderStatusTextView;
        Button confirmButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize your views here based on the item layout.
            orderIdTextView = itemView.findViewById(R.id.orderIdTextView);
            customerNameTextView = itemView.findViewById(R.id.customerNameTextView);
            storeNameTextView = itemView.findViewById(R.id.storeNameTextView);
            orderStatusTextView = itemView.findViewById(R.id.orderStatusTextView);
            confirmButton = itemView.findViewById(R.id.confirmButton);
        }
    }
}