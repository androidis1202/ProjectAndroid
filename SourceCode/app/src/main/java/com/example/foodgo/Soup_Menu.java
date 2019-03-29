package com.example.foodgo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.foodgo.Entity.Drink;
import com.example.foodgo.Entity.Drink;

import java.util.ArrayList;
import java.util.List;

public class Soup_Menu extends Fragment {

    private GridView gridView;
    private List<Drink> fastFood;
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
        List<Drink> list= getListData();

        adapter = new Soup_Adapter(getActivity(),list);
        gridView.setAdapter(adapter);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Drink drink = (Drink) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DetailFood.class);
                intent.putExtra("info",drink);
                startActivity(intent);
            }
        });

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
    private  List<Drink> getListData() {

        List<Drink> list = new ArrayList<Drink>();
        list.add(new Drink("Chicken Noodle",13,80f, R.drawable.chicken_noodle_soup,"Good"));
        list.add(new Drink("Potato Soup",14,80f, R.drawable.potato_soup,"Good"));
        list.add(new Drink("Pumpkin Soup",15,70f, R.drawable.pumpkin_soup,"Good"));
        list.add(new Drink("Spiced Carrot Soup",16,90f,R.drawable.spiced_carrot_soup,"Good"));

        return list;
    }
}
