package com.example.b07group7project.shopper_view_previous_orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b07group7project.Customer;
import com.example.b07group7project.R;
import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.shopper_view_store.ShopperViewStoreFragment;

import java.util.ArrayList;

public class ShopperPreviousOrderFragment extends Fragment {

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
        RecyclerView previousOrderView = view.findViewById(R.id.viewPreviousOrders);

        setUpPreviousOrders();
        PreviousOrderAdapter adapter = new PreviousOrderAdapter(requireContext(), previousOrders);
        previousOrderView.setAdapter(adapter);
        previousOrderView.setLayoutManager(new LinearLayoutManager(requireContext()));


        return view;
    }

    private void setUpPreviousOrders() {
        getPreviousOrderImplementation getPreviousOrders = new getPreviousOrderImplementation();
        previousOrders = getPreviousOrders.getPreviousOrders();
    }

}
