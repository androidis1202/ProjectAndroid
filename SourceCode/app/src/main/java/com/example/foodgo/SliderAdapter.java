package com.example.foodgo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_background = {
            R.drawable.anh1,
            R.drawable.anh3,
            R.drawable.anh2,
    };




    public String[] title_text =
            {
                    "Discover place near you",
                    "Search for place easily",
                    "Order from the best local restaurants",
            };

    public String[] information_text =
            {
                    "Find the best restaurant you want by your location or neighborhood",
                    "Decide where to eat with family and friends to enjoy the beautiful day",
                    "Order with easy your meal from your favorite restaurants"
            };

    @Override
    public int getCount() {
        return slide_background.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);
        view.setBackgroundResource(slide_background[position]);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        TextView txtInfor = view.findViewById(R.id.txtInfor);
        txtTitle.setText(title_text[position]);
        txtInfor.setText(information_text[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
