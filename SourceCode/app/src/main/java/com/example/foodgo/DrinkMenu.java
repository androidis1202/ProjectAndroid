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
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.Drink;

import java.util.ArrayList;
import java.util.List;


public class DrinkMenu extends AppCompatActivity {
    private GridView gridView;
    private List<Drink> drinkList;
    private ListViewAdapter myAdapter;
    private TextView view;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);

        gridView = findViewById(R.id.gridViewDrink);
        searchView = findViewById(R.id.svDrink);

        final Drink_Adapter adapter = new Drink_Adapter(this, this.getListData());
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

    }
    private  List<Drink> getListData() {
        List<Drink> list = new ArrayList<Drink>();
        list.add(new Drink("Drink Menu",1,125f, R.drawable.drink,"Its a very good drink!!!"));
        list.add(new Drink("Fast Food Menu",2,125f, R.drawable.fastfood,"The best food ever!!!"));
        list.add(new Drink("Soup Menu",3,125f, R.drawable.soup,"You must try it!!!"));
        list.add(new Drink("Main Meal Menu",4,125f,R.drawable.mainmenu,"Let's enjoy you meal!!!"));

        return list;
    }


}

