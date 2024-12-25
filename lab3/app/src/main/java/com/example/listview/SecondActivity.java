package com.example.listview;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView cartListView;
    ArrayList<Product> selectedProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);

        selectedProducts = (ArrayList<Product>) getIntent().getSerializableExtra("selectedProducts");

        CartAdapter cartAdapter = new CartAdapter(this, selectedProducts);
        cartListView.setAdapter(cartAdapter);
    }
}
