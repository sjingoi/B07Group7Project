package com.example.b07group7project.view_products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;
import com.example.b07group7project.database.ImageDownloader;
import com.example.b07group7project.database_abstractions.StoreProduct;

import java.util.ArrayList;

// RecylerViewAdapter for ViewProductFragment
public class ViewProductAdapter extends RecyclerView.Adapter<ViewProductAdapter.MyViewHolder>{

    Context context;
    ArrayList<StoreProduct> items;
    ProductClickListener listener;

    public ViewProductAdapter(Context context, ArrayList<StoreProduct> items, ProductClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }


    @NonNull
    public ViewProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Give Look To Each Row
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_product_row_data, parent, false);

        return new ViewProductAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProductAdapter.MyViewHolder holder, int position) {
        // Assign Value to Each Row as They Come On Screen
        StoreProduct product = items.get(position);
        holder.tvName.setText(product.getItemName());
        //TODO: get the price from the product
        ImageDownloader.setImageResource(holder.imageView, product.getImageURL());

        holder.cardView.setOnClickListener(view -> listener.onProductClicked(product));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvName;
        View cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.Product_Image);
            tvName = itemView.findViewById(R.id.Product_Name);
            cardView = itemView.findViewById(R.id.ProductCardView);
        }

    }
}
