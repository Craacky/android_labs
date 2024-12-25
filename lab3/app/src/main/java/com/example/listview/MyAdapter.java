package com.example.listview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private final List<Product> productList;
    private final LayoutInflater inflater;
    private OnCheckedChangeListener listener;

    public MyAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            holder = new ViewHolder();
            holder.itemId = convertView.findViewById(R.id.itemId);
            holder.itemName = convertView.findViewById(R.id.itemName);
            holder.itemPrice = convertView.findViewById(R.id.itemPrice);
            holder.itemCheckBox = convertView.findViewById(R.id.itemCheckBox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product product = productList.get(position);

        holder.itemId.setText(String.valueOf(product.getId()));
        holder.itemName.setText(product.getName());
        holder.itemPrice.setText(String.format("$%.2f", product.getPrice()));

        holder.itemCheckBox.setOnCheckedChangeListener(null);

        holder.itemCheckBox.setChecked(product.isSelected());

        holder.itemCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            product.setSelected(isChecked);
            if (listener != null) {
                listener.onCheckedChanged(getSelectedCount());
            }
        });

        return convertView;
    }

    private int getSelectedCount() {
        int count = 0;
        for (Product product : productList) {
            if (product.isSelected()) {
                count++;
            }
        }
        return count;
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int selectedCount);
    }

    private static class ViewHolder {
        TextView itemId;
        TextView itemName;
        TextView itemPrice;
        CheckBox itemCheckBox;
    }
}
