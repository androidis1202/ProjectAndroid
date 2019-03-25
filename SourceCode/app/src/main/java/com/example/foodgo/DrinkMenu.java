package com.example.foodgo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.Drink;

import java.util.ArrayList;
import java.util.List;


public class DrinkMenu extends Fragment {
    private GridView gridView;
    private List<Drink> drinkList;
    private ListViewAdapter myAdapter;
    private TextView view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_drink_menu, container, false);

        gridView = rootView.findViewById(R.id.gridViewDrink);

        List<Drink> image_details = getListData();
        gridView.setAdapter(new Drink_Adapter(getActivity(), image_details));

        return  rootView;
    }
    private  List<Drink> getListData() {

        List<Drink> list = new ArrayList<Drink>();
        list.add(new Drink("Drink Menu",1,125f, R.drawable.drink));
        list.add(new Drink("Fast Food Menu",2,125f, R.drawable.fastfood));
        list.add(new Drink("Soup Menu",3,125f, R.drawable.soup));
        list.add(new Drink("Main Meal Menu",4,125f,R.drawable.mainmenu));

        return list;
    }


}

