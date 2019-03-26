package com.example.foodgo;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodgo.Entity.Cart;

import java.text.DecimalFormat;
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

    public class ViewHolder {

        public TextView txtNameFood, txtPriceFood;
        public ImageView imgCart;
        public Button btnMinus, btnSum, btnPlus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder.txtNameFood = convertView.findViewById(R.id.txtlistview_foodName);
            viewHolder.txtPriceFood = convertView.findViewById(R.id.txtListViewFood_Price);
            viewHolder.imgCart = convertView.findViewById(R.id.img_Food);
            viewHolder.btnMinus = convertView.findViewById(R.id.btnMinus);
            viewHolder.btnSum = convertView.findViewById(R.id.btnNumber);
            viewHolder.btnPlus = convertView.findViewById(R.id.btnPlus);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Cart cart = (Cart) getItem(position);
        viewHolder.txtNameFood.setText(cart.getFoodname());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,####");
        viewHolder.txtPriceFood.setText(decimalFormat.format(decimalFormat.format(cart.getPricename())) + "$");
        viewHolder.imgCart.setImageResource(cart.getFoodimage());
        return convertView;
    }
}
