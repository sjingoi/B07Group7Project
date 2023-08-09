package com.example.b07group7project;

import static com.example.b07group7project.shopper_view_previous_orders.OrderStatus.ORDER_COMPLETE;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.database.StoreOwnerMarkOrderDatabase;
import com.example.b07group7project.database.User;
import com.example.b07group7project.shopper_view_previous_orders.OrderedProduct;

import java.util.List;

/*
StoreOrders
    ->storeUUID
         ->datetime:order uuid
		->product name
		->product description
		->product image
		->product UUID
		->price
		->quantity
		->status
		->customeruuid
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<OrderedProduct> orders;

    public OrderAdapter(List<OrderedProduct> orders) {
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
        OrderedProduct order = orders.get(position);

        // Bind the data to the views in the ViewHolder.
        //holder.orderIdTextView.setText("Order ID: " + order.uuid);
        //holder.customerNameTextView.setText("Customer Name: " + order.customer.name);
        //holder.storeNameTextView.setText("Store Name: " + order.store.getStoreName());
        holder.orderStatusTextView.setText("Status: " + order.getOrderStatus());
        double price = order.getProduct().getPrice();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String formattedPrice = decimalFormat.format(price);

        holder.orderPriceTextView.setText("Price Per Unit: $" + formattedPrice);
        //holder.orderUserEmailView.setText("User Email: " + order.getProduct().getuserEmail());
        holder.orderItemNameTextView.setText("Item Name: " + order.getProduct().getItemName());
        holder.orderItemDesTextView.setText("Item Description: " + order.getProduct().getDescription());
        holder.orderQuantityTextView.setText("Quantity: " + order.getQuantity());

        // Handle the "Confirm" button click event here.
        holder.confirmButton.setOnClickListener(v -> {
            // Implement the order confirmation logic here, Call the confirmOrder() method of  OrderRepository to confirm the order.
            // For now, assume the confirmation is successful and update the order status locally.
            order.setOrderStatus(ORDER_COMPLETE);
            StoreOwnerMarkOrderDatabase storeOwnerMarkOrderDatabase = new StoreOwnerMarkOrderDatabase();
            storeOwnerMarkOrderDatabase.markOrderAsComplete(User.getCurrentUser(), order);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderItemNameTextView;
        //TextView orderUserEmailTextView;
        TextView orderItemDesTextView;
        TextView orderQuantityTextView;
        TextView orderPriceTextView;
        TextView orderStatusTextView;
        Button confirmButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize your views here based on the item layout.
            //orderUserEmailTextView = itemView.findViewById(R.id.orderUserEmailTextView);
            orderItemNameTextView = itemView.findViewById(R.id.orderItemNameTextView);
            orderItemDesTextView = itemView.findViewById(R.id.orderItemDesTextView);
            orderQuantityTextView = itemView.findViewById(R.id.orderQuantityTextView);
            orderPriceTextView = itemView.findViewById(R.id.orderPriceTextView);
            orderStatusTextView = itemView.findViewById(R.id.orderStatusTextView);
            confirmButton = itemView.findViewById(R.id.confirmButton);
        }
    }
}