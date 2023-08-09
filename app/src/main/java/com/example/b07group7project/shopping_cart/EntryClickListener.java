package com.example.b07group7project.shopping_cart;

import android.widget.TextView;

public interface EntryClickListener {
    void onEntryClick(CartEntry entry);

    void onRemoveClick(int entryPos);

    void onIncrement(CartEntry entry, TextView textView);

    void onDecrement(CartEntry entry, TextView textView);
}
