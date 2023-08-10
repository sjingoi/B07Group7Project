package com.example.b07group7project.shopper_view_previous_orders;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.R;
import com.example.b07group7project.database.PreviousOrdersDatabase;
import com.example.b07group7project.database.User;

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
        PreviousOrdersDatabase getPreviousOrders = new PreviousOrdersDatabase(); //TODO Change to new class once database is working
        getPreviousOrders.getPreviousOrders(User.getCurrentUser(), previousOrders -> onReceivedOrder(previousOrders, view));
        return view;
    }

    public void onReceivedOrder(ArrayList<PreviousOrder> previousOrders, View view) {
        RecyclerView recyclerView = view.findViewById(R.id.viewPreviousOrders);
        Context context = getContext();
        if(context == null)
            return;

        PreviousOrderAdapter previousOrderAdapter = new PreviousOrderAdapter(context, previousOrders, this);
        recyclerView.setAdapter(previousOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void onPreviousOrderClick(PreviousOrder previousOrder) {
        Context context = getContext();
        if(context == null)
            return;
        Toast.makeText(context, previousOrder.getCurrentDate(), Toast.LENGTH_SHORT).show();
    }


}
