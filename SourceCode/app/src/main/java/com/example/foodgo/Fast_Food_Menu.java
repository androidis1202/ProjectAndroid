package com.example.foodgo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.foodgo.Entity.Drink;
import com.example.foodgo.Entity.FastFood;

import java.util.ArrayList;
import java.util.List;

public class Fast_Food_Menu extends Fragment {

    private GridView gridView;
    private List<FastFood> fastFood;
    private ListViewAdapter myAdapter;
    private TextView view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_drink_menu, container, false);

        gridView = rootView.findViewById(R.id.gridViewDrink);

        List<FastFood> image_details = getListData();
        gridView.setAdapter(new FastFood_Adapter(getActivity(), image_details));

        return  rootView;
    }
    private  List<FastFood> getListData() {

        List<FastFood> list = new ArrayList<FastFood>();
        list.add(new FastFood("Drink Menu",1,125f, R.drawable.fastfood,"Good"));
        list.add(new FastFood("Fast Food Menu",2,125f, R.drawable.fastfood,"Good"));
        list.add(new FastFood("Soup Menu",3,125f, R.drawable.fastfood,"Good"));
        list.add(new FastFood("Main Meal Menu",4,125f,R.drawable.fastfood,"Good"));

        return list;
    }

}
