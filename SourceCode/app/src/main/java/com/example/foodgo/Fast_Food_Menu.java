package com.example.foodgo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

public class Fast_Food_Menu extends Fragment {

    private GridView gridView;
    private List<Drink> fastFood;
    private ListViewAdapter myAdapter;
    private TextView view;
    private SearchView searchView;
    private FastFood_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_drink_menu, container, false);

        gridView = rootView.findViewById(R.id.gridViewDrink);
        searchView = rootView.findViewById(R.id.svDrink);
        List<Drink> list= getListData();

        adapter = new FastFood_Adapter(getActivity(),list);
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
        list.add(new Drink("Hamberger",5,200f, R.drawable.hamburger,"Good"));
        list.add(new Drink("French Fries",6,105f, R.drawable.french_fries,"Good"));
        list.add(new Drink("Bread",7,135f, R.drawable.bread,"Good"));
        list.add(new Drink("Pizza",8,100f,R.drawable.pizza,"Good"));

        return list;
    }

}
