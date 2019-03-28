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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_cart, null);
            viewHolder.txtNameFood = convertView.findViewById(R.id.txtlistview_foodName);
            viewHolder.txtPriceFood = convertView.findViewById(R.id.txtListViewFood_Price);
            viewHolder.imgCart = convertView.findViewById(R.id.img_Food);
            viewHolder.btnMinus = convertView.findViewById(R.id.btnMinus);
            viewHolder.btnSum = convertView.findViewById(R.id.btnNumber);
            viewHolder.btnPlus = convertView.findViewById(R.id.btnPlus);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Cart cart = (Cart) getItem(position);
        viewHolder.txtNameFood.setText(cart.getFoodname());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,####");
        viewHolder.txtPriceFood.setText(decimalFormat.format(cart.getPricename()) + "$");
        viewHolder.btnSum.setText(String.valueOf(MainMenu.cartArrayList.get(position).getFoodnumber()));
        viewHolder.imgCart.setImageResource(cart.getFoodimage());
        final ViewHolder finalViewHolder = viewHolder;
        final ViewHolder finalViewHolder1 = viewHolder;
        final ViewHolder finalViewHolder3 = viewHolder;
        viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numberupdate = Integer.parseInt(finalViewHolder.btnSum.getText().toString()) + 1;
                int numbernow = MainMenu.cartArrayList.get(position).getFoodnumber();
                float pricenow = MainMenu.cartArrayList.get(position).getPricename();
                MainMenu.cartArrayList.get(position).setFoodnumber(numberupdate);
                float pricenewest = (numberupdate * pricenow) / numbernow;
                MainMenu.cartArrayList.get(position).setPricename(pricenewest);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,####");
                finalViewHolder1.txtPriceFood.setText(decimalFormat.format(pricenewest) + "$");
                Cart_layout.sum();
                finalViewHolder3.btnSum.setText(String.valueOf(numberupdate));
            }
        });
        final ViewHolder finalViewHolder2 = viewHolder;
        viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numberupdate = Integer.parseInt(finalViewHolder.btnSum.getText().toString()) - 1;
                int numbernow = MainMenu.cartArrayList.get(position).getFoodnumber();
                float pricenow = MainMenu.cartArrayList.get(position).getPricename();
                MainMenu.cartArrayList.get(position).setFoodnumber(numberupdate);
                float pricenewest = (numberupdate * pricenow) / numbernow;
                if (numberupdate < 0) {
                    numberupdate = 0;
                    pricenewest = 0;
                    MainMenu.cartArrayList.get(position).setFoodnumber(0);
                }
                MainMenu.cartArrayList.get(position).setPricename(pricenewest);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,####");
                finalViewHolder1.txtPriceFood.setText(decimalFormat.format(pricenewest) + "$");
                Cart_layout.sum();
                finalViewHolder2.btnSum.setText(String.valueOf(numberupdate));
            }
        });
        return convertView;
    }
}
