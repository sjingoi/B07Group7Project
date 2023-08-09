package com.example.b07group7project.itempreview;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b07group7project.R;
import com.example.b07group7project.database.AccountDatabase;
import com.example.b07group7project.database.CartListenerImplementation;
import com.example.b07group7project.database.ImageDownloader;
import com.example.b07group7project.database.User;
import com.example.b07group7project.database.StoreProductDatabase;
import com.example.b07group7project.database_abstractions.StoreProduct;

public class ItemPreviewFragment extends Fragment {
    private TextView cartItemQtyTextView;
    private int cartItemQty = 0;
    private StoreProduct currentItem;
    String itemID;
    String storeID;
    private AccountDatabase accountDatabase;

    public ItemPreviewFragment() {
        // Required empty public constructor
        accountDatabase = new AccountDatabase();
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

        // Get the item ID and store ID from arguments
        Bundle arguments = getArguments();
        if (arguments != null) {
            itemID = arguments.getString("itemID");
            storeID = arguments.getString("storeID");
            // Now you can use the itemID and storeID variables as needed
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_preview, container, false);

        CartListenerImplementation cartListener = new CartListenerImplementation(getContext());

        // Get references to TextView and Buttons
        cartItemQtyTextView = view.findViewById(R.id.cartItemQty);
        TextView textViewItemName = view.findViewById(R.id.itemName);
        TextView textViewItemPrice = view.findViewById(R.id.itemPrice);
        TextView textViewItemDesc = view.findViewById(R.id.itemDesc);
        ImageView itemImageView = view.findViewById(R.id.itemImage);

        Button addToCartButton = view.findViewById(R.id.button2);
        ImageButton incrementButton = view.findViewById(R.id.button3);
        ImageButton decrementButton = view.findViewById(R.id.button4);

        // Get the item information passed through bundle
        GetProductInfo getProductInfo = new StoreProductDatabase();
        getProductInfo.getProductFromFirebase(storeID, itemID, storeProduct -> {
            // Set the currentItem with the retrieved StoreProduct
            currentItem = storeProduct;
            updateUIWithItemInfo();
        });


       if (getArguments() != null) {
            currentItem = (StoreProduct) getArguments().getSerializable("product");
        }

            if (currentItem != null) {
                textViewItemName.setText(currentItem.getItemName());
                textViewItemPrice.setText("$" + currentItem.getPrice());
                textViewItemDesc.setText(currentItem.getDescription());
                ImageDownloader.setImageResource(itemImageView, currentItem.getImageURL());
                cartItemQtyTextView.setText(String.valueOf(cartItemQty));
            }


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
            // Call the getUserUUID method to fetch user's UUID
            User currentUser = User.getCurrentUser();
            if (currentUser != null) {
                accountDatabase.getUserUUID(currentUser, userUUID -> {
                    if (userUUID != null) {
                        String customerUUID = userUUID;
                        cartListener.addToCart(storeID, customerUUID, itemID, cartItemQty);
                    } else {
                        // Handle the case where userUUID is null
                    }
                });
            }
        });


        return view;
    }

    private void updateUIWithItemInfo() {
        View rootView = getView();
        if (rootView != null) {
            TextView textViewItemName = rootView.findViewById(R.id.itemName);
            TextView textViewItemPrice = rootView.findViewById(R.id.itemPrice);
            TextView textViewItemDesc = rootView.findViewById(R.id.itemDesc);

            if (currentItem != null) {
                textViewItemName.setText(currentItem.getItemName());
                textViewItemPrice.setText("$" + currentItem.getPrice());
                textViewItemDesc.setText(currentItem.getDescription());
                cartItemQtyTextView.setText(String.valueOf(cartItemQty));
            }
        }
    }

}