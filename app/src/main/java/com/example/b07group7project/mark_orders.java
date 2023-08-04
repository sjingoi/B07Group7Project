package com.example.b07group7project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mark_orders#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mark_orders extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public mark_orders() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mark_orders.
     */
    // TODO: Rename and change types and number of parameters
    public static mark_orders newInstance(String param1, String param2) {
        mark_orders fragment = new mark_orders();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private void markOrderAsComplete(String storeId, String orderId) {
        DatabaseReference orderRef = database.child("stores").child(storeId).child("orders").child(orderId);

        // Update the 'status' field to "complete"
        orderRef.child("status").setValue("complete", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    // Handle any error that occurred during the update
                    Log.e("OrderManagementActivity", "Failed to mark order as complete: " + databaseError.getMessage());
                } else {
                    // Order marked as complete successfully
                    // You can perform any necessary actions here, e.g., update the UI
                    Log.d("OrderManagementActivity", "Order marked as complete successfully");
                }
            }
        });
    }

    /**
     * What we do with the database is that we get customer orders and has a backlog of orders,
     * We have the store owner has stores, each store has a backlog of orders
     * What I have to do is if store owner account, we have access each store, then in each store we have access to backlogs of orders
     * */

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mark_orders, container, false);
    }
}