package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Product> products;

    public CartAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() { return products.size(); }
    @Override
    public Object getItem(int position) { return products.get(position); }
    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        }

        Product product = products.get(position);
        TextView id = convertView.findViewById(R.id.itemId);
        TextView name = convertView.findViewById(R.id.itemName);
        id.setText(String.valueOf(product.getId()));
        name.setText(product.getName());

        return convertView;
    }
}
