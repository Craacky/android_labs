package com.example.listview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView footerTextView;
    Button showCheckedItems;
    MyAdapter adapter;
    ArrayList<Product> productList = new ArrayList<>();
    ArrayList<Product> selectedProducts = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        footerTextView = findViewById(R.id.footerTextView);
        showCheckedItems = findViewById(R.id.showCheckedItems);

        // Populate product list
        for (int i = 1; i <= 100; i++) {
            productList.add(new Product(i, "My good №" + i, i * 10));
        }

        adapter = new MyAdapter(this, productList);
        listView.setAdapter(adapter);

        adapter.setOnCheckedChangeListener(selectedCount -> footerTextView.setText("Selected = " + selectedCount));

        showCheckedItems.setOnClickListener(v -> {
            selectedProducts.clear();
            for (Product product : productList) {
                if (product.isSelected()) {
                    selectedProducts.add(product);
                }
            }

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("selectedProducts", selectedProducts);
            startActivity(intent);
        });
    }
}
