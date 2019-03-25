package com.example.foodgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodgo.Entity.FastFood;
import com.example.foodgo.Entity.MainMeal;

import java.util.List;

public class MainMeal_Adapter extends BaseAdapter {
    private List<MainMeal> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public MainMeal_Adapter(Context aContext,  List<MainMeal> listData) {
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
        MainMeal_Adapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.drink_layout, null);
            holder = new MainMeal_Adapter.ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_drink);
            holder.drinkName = (TextView) convertView.findViewById(R.id.txtName);
            holder.price = (TextView) convertView.findViewById(R.id.txtPrice);
            convertView.setTag(holder);
        } else {
            holder = (MainMeal_Adapter.ViewHolder) convertView.getTag();
        }

        MainMeal mainMeal = this.listData.get(position);
        holder.drinkName.setText(mainMeal.getName());
        holder.imageView.setImageResource(mainMeal.getImage());
        holder.price.setText("$"+Float.toString(mainMeal.getPrice()));
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView drinkName;
        TextView price;
    }
}
