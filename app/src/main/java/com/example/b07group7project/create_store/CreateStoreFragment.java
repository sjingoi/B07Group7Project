package com.example.b07group7project.create_store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.b07group7project.R;
import com.example.b07group7project.database.StoreDatabase;

public class CreateStoreFragment extends Fragment {

    private SaveStore saveStore;

    public CreateStoreFragment() {
        // Required empty public constructor
    }

    public static CreateStoreFragment newInstance() {
        CreateStoreFragment fragment = new CreateStoreFragment();
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
        View rootView = inflater.inflate(R.layout.store_owner_create_store, container, false);

        EditText storeNameEditText = rootView.findViewById(R.id.edit_text_store_name);
        EditText storeDescEditText = rootView.findViewById(R.id.edit_text_description);
        EditText storeURLEditText = rootView.findViewById(R.id.edit_text_Store_Icon_URL);
        Button saveButton = rootView.findViewById(R.id.button_save);

        // Instantiate the SaveProductImplementation and assign it to saveProduct
        saveStore = new StoreDatabase();

        saveButton.setOnClickListener(v -> {
            String storeName = storeNameEditText.getText().toString().trim();
            String storeDesc = storeDescEditText.getText().toString().trim();
            String storeURL = storeURLEditText.getText().toString().trim();

            if (!storeName.isEmpty() && !storeDesc.isEmpty() && !storeURL.isEmpty()) {

                saveStore.saveStoreToFirebase(storeName, storeDesc, storeURL);

                // Clear the EditText fields after saving if the data is valid and saved
                storeNameEditText.setText("");
                storeDescEditText.setText("");
                storeURLEditText.setText("");

                Toast.makeText(getContext(), "Store Created", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}