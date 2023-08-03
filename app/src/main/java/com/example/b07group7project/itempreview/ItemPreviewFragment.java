package com.example.b07group7project.itempreview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.b07group7project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemPreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemPreviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemPreviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemPreviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemPreviewFragment newInstance(String param1, String param2) {
        ItemPreviewFragment fragment = new ItemPreviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_preview, container, false);

        // Get references to TextView and Buttons
        TextView cartItemQtyTextView = view.findViewById(R.id.cartItemQty);
        TextView textViewItemName = view.findViewById(R.id.itemName);
        TextView textViewItemPrice = view.findViewById(R.id.itemPrice);
        TextView textViewItemDesc = view.findViewById(R.id.itemDesc);

        GetItemInfo itemInterface = new GetItemInfoImplementation();
        StoreItem item = itemInterface.getItemInformation();

        String itemName = item.getItemName();
        double itemPrice = item.getItemPrice();

        textViewItemName.setText(itemName);
        textViewItemPrice.setText("$" + itemPrice);
        textViewItemDesc.setText(item.getItemDesc());

        ImageButton incrementButton = view.findViewById(R.id.button3);
        ImageButton decrementButton = view.findViewById(R.id.button4);
        Button addToCart = view.findViewById(R.id.button2);

        // Initial value for cartItemQty
        final int[] cartItemQty = {0};
        cartItemQtyTextView.setText(String.valueOf(cartItemQty[0]));

        // Set OnClickListener for the increment button
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Increment the cartItemQty and update the TextView
                cartItemQty[0]++;
                cartItemQtyTextView.setText(String.valueOf(cartItemQty[0]));
            }
        });

        // Set OnClickListener for the decrement button
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Decrement the cartItemQty (with a minimum value of 0) and update the TextView
                cartItemQty[0] = Math.max(0, cartItemQty[0] - 1);
                cartItemQtyTextView.setText(String.valueOf(cartItemQty[0]));
            }
        });

        // Set OnClickListener for the cart add button
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add to cart (shopping cart)
            }
        });

        return view;
    }
}