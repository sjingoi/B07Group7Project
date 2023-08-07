package com.example.b07group7project.shopper_view_previous_orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.Customer;
import com.example.b07group7project.R;
import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.shopper_view_store.ShopperViewStoreFragment;
import com.example.b07group7project.shopper_view_store.StoreRecyclerViewAdapter;

import java.util.ArrayList;

public class ShopperPreviousOrderFragment extends Fragment {

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
        RecyclerView recyclerView = view.findViewById(R.id.viewPreviousOrders);

        getPreviousOrderImplementation getPreviousOrders = new getPreviousOrderImplementation();
        ArrayList<PreviousOrder> order = getPreviousOrders.getPreviousOrders();

        PreviousOrderAdapter adapter = new PreviousOrderAdapter(requireContext(), order);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        
        return view;
    }
    /*
    private void setUpPreviousOrders() {
        previousOrders = getPreviousOrders(new Customer());
    }
    */

}
