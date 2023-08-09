package com.example.b07group7project.create_product;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b07group7project.R;
import com.example.b07group7project.database.SaveProduct;
import com.example.b07group7project.database.SaveProductImplementation;

public class CreateProductFragment extends Fragment {

    private SaveProduct saveProduct;

    public CreateProductFragment() {
        // Required empty public constructor
    }

    public static CreateProductFragment newInstance() {
        CreateProductFragment fragment = new CreateProductFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_create_product, container, false);

        EditText itemNameEditText = rootView.findViewById(R.id.edit_text_item_name);
        EditText itemDescEditText = rootView.findViewById(R.id.edit_text_description);
        EditText itemURLEditText = rootView.findViewById(R.id.edit_text_itemurl);
        EditText itemPriceEditText = rootView.findViewById(R.id.edit_text_price);
        Button saveButton = rootView.findViewById(R.id.button_save);

        // Instantiate the SaveProductImplementation and assign it to saveProduct
        saveProduct = new SaveProductImplementation();

        saveButton.setOnClickListener(v -> {
            String itemName = itemNameEditText.getText().toString().trim();
            String itemDesc = itemDescEditText.getText().toString().trim();
            String itemURL = itemURLEditText.getText().toString().trim();
            String itemPriceText = itemPriceEditText.getText().toString().trim();

            if (!itemName.isEmpty() && !itemDesc.isEmpty() && !itemURL.isEmpty() && !itemPriceText.isEmpty()) {
                try {
                    double itemPrice = Double.parseDouble(itemPriceText);
                    saveProduct.saveProductToFirebase(itemName, itemDesc, itemURL, itemPrice);

                    // Clear the EditText fields after saving if the data is valid and saved
                    itemNameEditText.setText("");
                    itemDescEditText.setText("");
                    itemURLEditText.setText("");
                    itemPriceEditText.setText("");

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Invalid item price input.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}