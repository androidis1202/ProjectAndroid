package com.example.foodgo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.Drink;

import java.util.List;

import static java.lang.Package.getPackages;

public class ListViewAdapter extends BaseAdapter {
    private Activity context;
    private int layout;
    private List<Category> list;

    public ListViewAdapter(Activity context, int layout, List<Category> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View myView = convertView;
        if (myView == null) {
            myView = context.getLayoutInflater().inflate(layout, null);
        }

        TextView txtName = myView.findViewById(R.id.textView3);
        ImageView imageView = myView.findViewById(R.id.imageView);
        txtName.setText(list.get(position).getName());
        imageView.setImageResource(list.get(position).getImage());

        if (position == 0) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailFoodOrder.class);
                    context.startActivity(intent);
                    context.finish();
                }
                });

        } else if (position == 1) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Drink.class);
                    context.startActivity(intent);
                    context.finish();
                }
            });
        } else if (position == 2) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Soup_Menu.class);
                    context.startActivity(intent);
                    context.finish();
                }
            });
        } else if (position == 3) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Fast_Food_Menu.class);
                    context.startActivity(intent);
                    context.finish();
                }
            });
        }
        return myView;
    }
}
