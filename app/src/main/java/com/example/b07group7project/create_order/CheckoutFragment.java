package com.example.b07group7project.create_order;


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


import java.text.DecimalFormat;
import java.util.ArrayList;

public class CheckoutFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkout_fragment, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.Checkout_RecyclerView);
        // TODO: Update Accordinlgy based on Implemenetation of GetCartInterface
        GetCartInterface cartInterface = new GetCartImplementation();
        cartInterface.getCart(
                products -> onReceivedCart(products, view)
        );




        return view;
    }

    public void onReceivedCart(ArrayList<Product> cart, View view){
        RecyclerView recyclerView = view.findViewById(R.id.Checkout_RecyclerView);
        CheckoutRecyclerAdapter adapter = new CheckoutRecyclerAdapter(requireContext(), cart);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));



        // Price
        TextView totals = view.findViewById(R.id.OrderTotals);
        totals.setText("TOTAL: $" + calculateTotal(cart));


        // Button Stuff
        Button orderButton = view.findViewById(R.id.PlaceOrderButton);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              // TODO: Update As Needed When PlaceOrder Is Implemented Through Databse
                PlaceOrderInterface orderInterface = new PlaceOrderImplementation();
                orderInterface.placeOrder(cart, "EXAMPLE USER HERE");

                Toast.makeText(requireContext(), "ORDER PLACED", Toast.LENGTH_SHORT).show();

                // Cart Is Now Empty As Everything Has Ordered

            }
        });
    }


    public String calculateTotal(ArrayList<Product> cart){
        double total = 0;
        for (int i = 0; i < cart.size(); i++){
            total += (cart.get(i).price * cart.get(i).getQuantity());
        }


        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String formattedPrice = decimalFormat.format(total);

        // Convert total to 2 decimal places
        return formattedPrice;
    }

}
