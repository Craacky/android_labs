package by.craacky.listviewer;// GoodsAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Good> goods;

    public GoodsAdapter(Context context, ArrayList<Good> goods) {
        this.context = context;
        this.goods = goods;
    }

    @Override
    public int getCount() {
        return goods.size();
    }

    @Override
    public Object getItem(int position) {
        return goods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return goods.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_good, parent, false);
            holder = new ViewHolder();
            holder.tvId = convertView.findViewById(R.id.tv_id);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.tvPrice = convertView.findViewById(R.id.tv_price);
            holder.cbSelect = convertView.findViewById(R.id.cb_select);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Good good = goods.get(position);

        holder.tvId.setText(String.valueOf(good.getId()));
        holder.tvName.setText(good.getName());
        holder.tvPrice.setText(String.valueOf(good.getPrice()));

        // Устанавливаем состояние чекбокса на основе модели данных
        holder.cbSelect.setChecked(good.isChecked());

        // Обработчик изменения состояния чекбокса
        holder.cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> good.setChecked(isChecked));

        return convertView;
    }

    static class ViewHolder {
        TextView tvId;
        TextView tvName;
        TextView tvPrice;
        CheckBox cbSelect;
    }
}
