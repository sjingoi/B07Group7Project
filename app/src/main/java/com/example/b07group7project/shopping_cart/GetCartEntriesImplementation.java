package com.example.b07group7project.shopping_cart;

import com.example.b07group7project.database.OnComplete;
import com.example.b07group7project.database_abstractions.StoreProduct;

import java.util.ArrayList;
import java.util.List;

public class GetCartEntriesImplementation implements GetCartEntries{

    @Override
    public void getCartEntries(OnComplete<List<CartEntry>> onComplete) {
        List<CartEntry> cart = new ArrayList<>();


        StoreProduct sp = new StoreProduct("Test Product", "hi", "https://inst-fs-yul-prod.inscloudgate.net/thumbnails/9cb9e948-8651-4297-bb0b-41c2610739f7?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2OTEyMTQwMDgsInVzZXJfaWQiOiIxMTgzNDAwMDAwMDA2MzIyMTUiLCJyZXNvdXJjZSI6Ii90aHVtYm5haWxzLzljYjllOTQ4LTg2NTEtNDI5Ny1iYjBiLTQxYzI2MTA3MzlmNyIsImp0aSI6IjEzNzRjYjZlLWM2MzMtNGM0MS05NDFiLTZiYWU5NmJkODc1YyIsImhvc3QiOiJxLnV0b3JvbnRvLmNhIiwib3JpZ2luYWxfdXJsIjoiaHR0cHM6Ly9xLnV0b3JvbnRvLmNhL2ltYWdlcy90aHVtYm5haWxzLzE0NTIwNjQ2L2RaVndaYXFldEd2MkNXUHpHTWRZTFRzU2lZejZJSU5GTTgxd0piaHo_bm9fY2FjaGU9dHJ1ZVx1MDAyNnJlZGlyZWN0PXRydWUiLCJleHAiOjE2OTEzMDA0MDh9.xc6hmQ8SmXPW5tUq0c100sdCMw6GwE2S84kV2XwbHNoN_QpkpPqga-0TXUSF80gQwGuuvpDQRyOFpyTRwv9gjw", 69.99);
        cart.add(new CartEntry(sp, null, 2));
        cart.add(new CartEntry(sp, null, 2));
        cart.add(new CartEntry(sp, null, 2));

        onComplete.onComplete(cart);
    }
}
