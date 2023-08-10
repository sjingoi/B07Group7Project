package com.example.b07group7project.create_order;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;
import com.example.b07group7project.database.CartDatabase;
import com.example.b07group7project.database.User;
import com.example.b07group7project.nav.Navigation;
import com.example.b07group7project.shopping_cart.CartEntry;
import com.example.b07group7project.shopping_cart.GetCartEntries;
import com.example.b07group7project.shopping_cart.ShoppingCartFragment;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class CheckoutFragment extends Fragment {

    public static CheckoutFragment newInstance() {
        CheckoutFragment fragment = new CheckoutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkout_fragment, container, false);

        GetCartEntries cartInterface = new CartDatabase();
        cartInterface.getCartEntries(
                products -> onReceivedCart(products, view)
        );

        return view;
    }

    public void onReceivedCart(ArrayList<CartEntry> cart, View view){
        RecyclerView recyclerView = view.findViewById(R.id.Checkout_RecyclerView);
        Context context = getContext();
        if(context == null)
            return;

        CheckoutRecyclerAdapter adapter = new CheckoutRecyclerAdapter(context, cart);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // Price
        TextView totals = view.findViewById(R.id.OrderTotals);
        totals.setText(String.format("%s%s", getString(R.string.total), calculateTotal(cart)));


        // Button Stuff
        Button orderButton = view.findViewById(R.id.PlaceOrderButton);
        orderButton.setOnClickListener(view1 -> {
            PlaceOrderInterface orderInterface = new CartDatabase();
            orderInterface.placeOrder(cart, User.getCurrentUser());
            if (requireActivity() instanceof Navigation) {
                Navigation nav = (Navigation) requireActivity();
                nav.replaceFragment(ShoppingCartFragment.newInstance(), false);
            }
            Toast.makeText(context, "Order Placed Successfully.", Toast.LENGTH_SHORT).show();
        });
    }


    public String calculateTotal(ArrayList<CartEntry> cart){
        double total = 0;
        for (int i = 0; i < cart.size(); i++){
            total += (cart.get(i).getProduct().getPrice() * cart.get(i).getQuantity());
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        // Convert total to 2 decimal places
        return decimalFormat.format(total);
    }

}
