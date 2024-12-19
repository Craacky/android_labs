package by.craacky.listviewer;// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivity.OnDataChangeListener {

    private ListView listView;
    private GoodsAdapter goodsAdapter;
    private TextView tv_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        tv_count = findViewById(R.id.tv_count);

        ArrayList<Good> arr_goods = new ArrayList<>();

        // Fill the list with 100 items
        for (int i = 1; i <= 100; i++) {
            arr_goods.add(new Good(i, "Товар " + i, i * 10.0));
        }

        // Initialize the adapter with data and listener
        goodsAdapter = new GoodsAdapter(this, arr_goods, this); // Pass this as listener

        listView.setAdapter(goodsAdapter);

        // Button to show selected items in cart
        Button btnShowCart = findViewById(R.id.btn_show_cart);
        btnShowCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            intent.putParcelableArrayListExtra("selected_goods", goodsAdapter.getSelectedGoods());
            startActivity(intent);
        });

        // Update the count of selected items initially
        updateCount();
    }

    @Override
    public void onDataChanged() {
        updateCount(); // Update count when data changes (checkbox state changes)
    }

    private void updateCount() {
        int size = goodsAdapter.getSelectedGoods().size();
        tv_count.setText("Count of goods = " + size);
    }

    // Define the OnDataChangeListener interface
    public interface OnDataChangeListener {
        void onDataChanged();
    }
}
