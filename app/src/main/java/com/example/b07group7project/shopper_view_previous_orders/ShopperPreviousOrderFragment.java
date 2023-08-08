package com.example.b07group7project.shopper_view_previous_orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.Customer;
import com.example.b07group7project.R;
import com.example.b07group7project.create_order.CheckoutRecyclerAdapter;
import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.Store;
import com.example.b07group7project.shopper_view_store.ShopperViewStoreFragment;

import java.util.ArrayList;

public class ShopperPreviousOrderFragment extends Fragment implements PreviousOrderClickListener{

    ArrayList<PreviousOrder> previousOrders = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static ShopperPreviousOrderFragment newInstance() {
        ShopperPreviousOrderFragment fragment = new ShopperPreviousOrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_previousorders, container, false);
        getPreviousOrdersInterface getPreviousOrders = new getPreviousOrderImplementation(); //TODO Change to new class once database is working
        getPreviousOrders.getPreviousOrders(previousOrders -> onReceivedOrder(previousOrders, view));
        return view;
    }

    public void onReceivedOrder(ArrayList<PreviousOrder> previousOrders, View view) {
        RecyclerView recyclerView = view.findViewById(R.id.viewPreviousOrders);
        PreviousOrderAdapter previousOrderAdapter = new PreviousOrderAdapter(requireContext(), previousOrders, this);
        recyclerView.setAdapter(previousOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onPreviousOrderClick(PreviousOrder previousOrder) {
        Toast.makeText(requireContext(), previousOrder.getPreviousorder(), Toast.LENGTH_SHORT).show();
    }


}
