package com.example.foodgo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.Drink;

import java.util.List;

public class RecycleViewDrink_Adapter extends RecyclerView.Adapter<RecycleViewDrink_Adapter.DrinkViewHolder> {


        private List<Drink> DrinkList;
        private Context mContext;

        private LayoutInflater mLayoutInflater;

        public RecycleViewDrink_Adapter(Context context, List<Drink> datas) {
                mContext = context;
            DrinkList = datas;
                mLayoutInflater = LayoutInflater.from(context);
                }

        @Override
        public DrinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                //inflate view from row_item_song.xml
                View itemView = mLayoutInflater.inflate(R.layout.drink_layout, parent, false);
                return new DrinkViewHolder(itemView);
                }


        @Override
        public void onBindViewHolder(DrinkViewHolder holder, int position) {
                //get song in mSong via position
                Drink drink = DrinkList.get(position);

                //bind data to viewholder
               // holder.tvCode.setText(Integer.toString(cate.getId()));
                holder.tvPrice.setText("$"+Float.toString(drink.getPrice()));
                holder.tvName.setText(drink.getName());
                holder.imageView.setImageResource(drink.getImage());
                }
                @Override
                public int getItemCount() {
                return DrinkList.size();
        }

        class DrinkViewHolder extends RecyclerView.ViewHolder {
            private TextView tvCode;
            private TextView tvName;
            private TextView tvPrice;
            private ImageView imageView;

            public DrinkViewHolder(View itemView) {
                super(itemView);
                //tvCode = (TextView) itemView.findViewById(R.id.tv_code);
                tvName = (TextView) itemView.findViewById(R.id.tv_Name);
                tvPrice = (TextView) itemView.findViewById(R.id.tv_Price);
                imageView = (ImageView) itemView.findViewById(R.id.imageDrink);
            }
        }
        }

