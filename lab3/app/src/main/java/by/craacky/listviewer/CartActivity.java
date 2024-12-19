package by.craacky.listviewer;// CartActivity.java
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private GoodsAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cart_list_view);

        // Retrieve the selected goods from the intent
        ArrayList<Good> selectedGoods = getIntent().getParcelableArrayListExtra("selected_goods");

        // Initialize the adapter with the selected goods
        cartAdapter = new GoodsAdapter(this, selectedGoods, null); // No need for data change listener here
        cartListView.setAdapter(cartAdapter);

        // Calculate and display total price
        double totalPrice = calculateTotalPrice(selectedGoods);
    }

    private double calculateTotalPrice(ArrayList<Good> selectedGoods) {
        double total = 0.0;
        for (Good good : selectedGoods) {
            total += good.getPrice();
        }
        return total;
    }
}
