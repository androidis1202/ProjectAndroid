package com.example.foodgo;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.foodgo.Entity.Cart;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> arrayListCart;

    public CartAdapter() {
    }

    public CartAdapter(Context context, ArrayList<Cart> arrayListCart) {
        this.context = context;
        this.arrayListCart = arrayListCart;
    }

    @Override
    public int getCount() {
        return arrayListCart.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListCart.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
