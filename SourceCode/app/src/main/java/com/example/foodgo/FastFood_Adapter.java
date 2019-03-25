package com.example.foodgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodgo.Entity.Drink;
import com.example.foodgo.Entity.FastFood;

import java.util.List;

public class FastFood_Adapter extends BaseAdapter {
    private List<FastFood> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public FastFood_Adapter(Context aContext,  List<FastFood> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        FastFood_Adapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.drink_layout, null);
            holder = new FastFood_Adapter.ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_drink);
            holder.drinkName = (TextView) convertView.findViewById(R.id.txtName);
            holder.price = (TextView) convertView.findViewById(R.id.txtPrice);
            convertView.setTag(holder);
        } else {
            holder = (FastFood_Adapter.ViewHolder) convertView.getTag();
        }

        FastFood fastFood = this.listData.get(position);
        holder.drinkName.setText(fastFood.getName());
        holder.imageView.setImageResource(fastFood.getImage());
        holder.price.setText("$"+Float.toString(fastFood.getPrice()));
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView drinkName;
        TextView price;
    }
}
