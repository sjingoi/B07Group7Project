package com.example.b07group7project.shopper_view_previous_orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.b07group7project.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.RecyclerViewAdapter;

import java.util.ArrayList;

public class PreviousOrderAdapter extends RecyclerView.Adapter<PreviousOrderAdapter.MyViewHolder> {

    Context context;
    ArrayList<PreviousOrder> previousOrders;
    PreviousOrderClickListener listener;

    public PreviousOrderAdapter(Context context, ArrayList<PreviousOrder> previousOrders, PreviousOrderClickListener listener) {
        this.context = context;
        this.previousOrders = previousOrders;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PreviousOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.previous_order_row, parent, false);
        return new PreviousOrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousOrderAdapter.MyViewHolder holder, int position) {
        holder.previousOrderView.setText(previousOrders.get(position).getPreviousorder());
        holder.orderStatusView.setText(previousOrders.get(position).getOrderstatus());
        holder.cardView.setOnClickListener(view -> listener.onPreviousOrderClick(previousOrders.get(position)));
    }

    @Override
    public int getItemCount() {
        return previousOrders.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView previousOrderView;
        TextView orderStatusView;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            previousOrderView = itemView.findViewById(R.id.previousOrder);
            orderStatusView = itemView.findViewById(R.id.orderStatus);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
