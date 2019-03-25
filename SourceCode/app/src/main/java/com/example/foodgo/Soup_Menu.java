package com.example.foodgo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.foodgo.Entity.MainMeal;
import com.example.foodgo.Entity.Soup;

import java.util.ArrayList;
import java.util.List;

public class Soup_Menu extends Fragment {

    private GridView gridView;
    private List<MainMeal> fastFood;
    private ListViewAdapter myAdapter;
    private TextView view;
    private SearchView searchView;
    private Soup_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_drink_menu, container, false);

        gridView = rootView.findViewById(R.id.gridViewDrink);
        searchView = rootView.findViewById(R.id.svDrink);
        List<Soup> list= getListData();

        adapter = new Soup_Adapter(getActivity(),list);
        gridView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);


                return false;
            }
        });


        return  rootView;
    }
    private  List<Soup> getListData() {

        List<Soup> list = new ArrayList<Soup>();
        list.add(new Soup("Drink Menu",1,125f, R.drawable.soup,"Good"));
        list.add(new Soup("Fast Food Menu",2,125f, R.drawable.soup,"Good"));
        list.add(new Soup("Soup Menu",3,125f, R.drawable.soup,"Good"));
        list.add(new Soup("Main Meal Menu",4,125f,R.drawable.soup,"Good"));

        return list;
    }
}
