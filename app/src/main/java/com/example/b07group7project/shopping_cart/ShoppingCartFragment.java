package com.example.b07group7project.shopping_cart;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.b07group7project.R;
import com.example.b07group7project.create_order.CheckoutFragment;
import com.example.b07group7project.database.AccountDatabase;
import com.example.b07group7project.database.CartListenerImplementation;
import com.example.b07group7project.database.User;
import com.example.b07group7project.itempreview.ItemPreviewFragment;
import com.example.b07group7project.nav.Navigation;
import com.example.b07group7project.database.CartDatabase;

import java.util.List;
public class ShoppingCartFragment extends Fragment implements EntryClickListener {

    List<CartEntry> cartEntriesList;

    RecyclerView recyclerView;

    String userID;

    CartListenerImplementation cli;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    public static ShoppingCartFragment newInstance() {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
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
        // Inflate the layout for this fragment
        View shoppingCartLayout = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        cli = new CartListenerImplementation(requireContext());

        recyclerView = shoppingCartLayout.findViewById(R.id.cartItemList);

        AccountDatabase adb = new AccountDatabase();
        adb.getUserUUID(User.getCurrentUser(), uuid -> userID = uuid);

        (new CartDatabase()).getCartEntries(cartEntries -> {
                cartEntriesList = cartEntries;
                onData(recyclerView, cartEntries);
            });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));



        // Button Stuff
        Button checkoutButton = shoppingCartLayout.findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(view -> {
            if (requireActivity() instanceof Navigation) {
                Navigation nav = (Navigation) requireActivity();
                nav.replaceFragment(CheckoutFragment.newInstance(), true);
            }

        });

        return shoppingCartLayout;
    }

    private void onData(RecyclerView recyclerView, List<CartEntry> cart) {
        recyclerView.setAdapter(new CartAdapter(requireContext().getApplicationContext(), cart, this));
    }

    @Override
    public void onEntryClick(CartEntry entry) {
        Activity activity = requireActivity();
        if (activity instanceof Navigation) {
            Navigation nav = (Navigation) activity;
            Bundle bundle = new Bundle();
            bundle.putString("itemID", entry.getProduct().getProductID());
            bundle.putString("storeID", entry.getStore().getStoreUUID());
            nav.replaceFragment(ItemPreviewFragment.newInstance(), true, bundle);
        }
    }

    @Override
    public void onRemoveClick(int entryPosition) {
        //Delete Entry
//        Toast.makeText(requireContext(), "DELETE", Toast.LENGTH_SHORT).show();
        CartEntry entry = cartEntriesList.get(entryPosition);
        cli.removeFromCart(entry.getStore().getStoreUUID(), userID, entry.getProduct().getProductID());
        cartEntriesList.remove(entryPosition);
        onData(recyclerView, cartEntriesList);

    }

    public void onIncrement(CartEntry entry, TextView textView) {
        entry.setQuantity(entry.getQuantity() + 1);
        String str = Integer.toString(entry.getQuantity());
        textView.setText(str);
        cli.setCartQuantity(entry.getStore().getStoreUUID(), userID, entry.getProduct().getProductID(), entry.getQuantity());
    }

    public void onDecrement(CartEntry entry, TextView textView) {
        if (entry.getQuantity() - 1 > 0) {
            entry.setQuantity(entry.getQuantity() - 1);
            String str = Integer.toString(entry.getQuantity());
            textView.setText(str);
            cli.setCartQuantity(entry.getStore().getStoreUUID(), userID, entry.getProduct().getProductID(), entry.getQuantity());
        }
    }
}