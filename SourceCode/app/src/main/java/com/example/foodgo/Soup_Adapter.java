package com.example.foodgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodgo.Entity.Drink;
import com.example.foodgo.Entity.Drink;

import java.util.ArrayList;
import java.util.List;

public class Soup_Adapter extends BaseAdapter implements Filterable {
    private List<Drink> drinks;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Drink> fastFoodList;
    private Soup_Adapter.CustomFilterSoup filter;

    public Soup_Adapter(Context aContext,  List<Drink> drinks) {
        this.context = aContext;
        this.drinks = drinks;
        layoutInflater = LayoutInflater.from(aContext);
        this.fastFoodList = drinks;
    }

    @Override
    public int getCount() {
        return drinks.size();
    }

    @Override
    public Object getItem(int position) {
        return drinks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Soup_Adapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.drink_layout, null);
            holder = new Soup_Adapter.ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_drink);
            holder.drinkName = (TextView) convertView.findViewById(R.id.txtName);
            holder.price = (TextView) convertView.findViewById(R.id.txtPrice);
            convertView.setTag(holder);
        } else {
            holder = (Soup_Adapter.ViewHolder) convertView.getTag();
        }

        Drink fastFood = this.drinks.get(position);
        holder.drinkName.setText(fastFood.getName());
        holder.imageView.setImageResource(fastFood.getImage());
        holder.price.setText("$"+Float.toString(fastFood.getPrice()));
        return convertView;
    }

    @Override
    public Filter getFilter() {

        if(filter == null){
            filter = new CustomFilterSoup();
        }

        return filter;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView drinkName;
        TextView price;
    }


    class CustomFilterSoup extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults  filterResults = new FilterResults();
            if(constraint !=null && constraint.length()>0){
                constraint = constraint.toString().toUpperCase();
                ArrayList<Drink> drinkArrayList  = new ArrayList<>();

                for(int i=0;i<fastFoodList.size();i++){
                    if(fastFoodList.get(i).getName().toUpperCase().contains(constraint)){
                        Drink drink = fastFoodList.get(i);
                        drinkArrayList.add(drink);
                    }
                }
                filterResults.count = drinkArrayList.size();
                filterResults.values = drinkArrayList;
            }else {
                filterResults.count = fastFoodList.size();
                filterResults.values = fastFoodList;
            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            drinks = (ArrayList<Drink>) results.values;
            notifyDataSetChanged();
        }
    }
}
