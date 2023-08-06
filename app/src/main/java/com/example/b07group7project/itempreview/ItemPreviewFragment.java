package com.example.b07group7project.itempreview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.b07group7project.R;
import com.example.b07group7project.database_abstractions.StoreProduct;
import com.example.b07group7project.shopping_cart.ShoppingCart;

public class ItemPreviewFragment extends Fragment {
    private TextView cartItemQtyTextView;
    private StoreProduct currentItem;
    private int cartItemQty = 0;

    public ItemPreviewFragment() {
        // Required empty public constructor
    }

    public static ItemPreviewFragment newInstance() {
        ItemPreviewFragment fragment = new ItemPreviewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the item ID from arguments (sent from the previous fragment)
        Bundle arguments = getArguments();
        if (arguments != null) {
                //String itemID = arguments.getString("itemID");
                //String storeID = arguments.getString("storeID");
                    }
        String itemID = "200";
        String storeID = "544b51d6-0328-4491-9a3d-f1532b6cbae0";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_preview, container, false);

        // Get references to TextView and Buttons
        cartItemQtyTextView = view.findViewById(R.id.cartItemQty);
        TextView textViewItemName = view.findViewById(R.id.itemName);
        TextView textViewItemPrice = view.findViewById(R.id.itemPrice);
        TextView textViewItemDesc = view.findViewById(R.id.itemDesc);

        Button addToCartButton = view.findViewById(R.id.button2);

        ImageButton incrementButton = view.findViewById(R.id.button3);
        ImageButton decrementButton = view.findViewById(R.id.button4);

        // Set OnClickListener for the increment button
        incrementButton.setOnClickListener(v -> {
            // Increment the cartItemQty and update the TextView
            cartItemQty++;
            cartItemQtyTextView.setText(String.valueOf(cartItemQty));
        });

        // Set OnClickListener for the decrement button
        decrementButton.setOnClickListener(v -> {
            // Decrement the cartItemQty (with a minimum value of 0) and update the TextView
            cartItemQty = Math.max(0, cartItemQty - 1);
            cartItemQtyTextView.setText(String.valueOf(cartItemQty));
        });

        // Set OnClickListener for the cart add button
        addToCartButton.setOnClickListener(v -> {
            // Call the addToCart method to add the item to the cart
            if (currentItem != null) {
                //addToCart(currentItem.getItemID(), currentItem.getItemName(), cartItemQty);
            }
        });

        return view;
    }


    private void addToCart(String itemId, String itemName, int quantityToAdd) {
        // You can implement the addToCart logic here, e.g., adding the item to the cart in the database
        // after database, framework provided
    }

    private void updateUIWithItemInfo() {
        TextView cartItemQtyTextView = getView().findViewById(R.id.cartItemQty);
        TextView textViewItemName = getView().findViewById(R.id.itemName);
        TextView textViewItemPrice = getView().findViewById(R.id.itemPrice);
        TextView textViewItemDesc = getView().findViewById(R.id.itemDesc);

        if (currentItem != null) {
            textViewItemName.setText(currentItem.getItemName());
            textViewItemPrice.setText("$" + currentItem.getPrice());
            textViewItemDesc.setText(currentItem.getDescription());
            cartItemQtyTextView.setText(String.valueOf(cartItemQty));
        }
    }
}