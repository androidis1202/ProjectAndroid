package com.example.foodgo;

import android.content.Intent;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.Drink;

import java.util.ArrayList;
import java.util.List;


public class DrinkMenu extends Fragment {
    private GridView gridView;
//    private List<Drink> drinkList;
//    private ListViewAdapter myAdapter;
//    private TextView view;
    private SearchView searchView;
    private Drink_Adapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_drink_menu, container, false);
        ListView listView = rootView.findViewById(R.id.list_view);
        gridView =  rootView.findViewById(R.id.gridViewDrink);
        searchView = rootView.findViewById(R.id.svDrink);
        List<Drink> list= getListData();

        adapter = new Drink_Adapter(getActivity(),list);
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
        list.add(new Drink("Coffee",1,80f, R.drawable.cup,"Its a very good drink!!!"));
        list.add(new Drink("Tea",2,50f, R.drawable.tea1,"The best food ever!!!"));
        list.add(new Drink("Juice",3,40f, R.drawable.juice,"You must try it!!!"));
        list.add(new Drink("Water",4,10f,R.drawable.water,"Let's enjoy you meal!!!"));
        return list;
    }


}

