package com.example.foodgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkMenu extends AppCompatActivity {
    private ListView listView;
    private List<Drink> drinkList;
    private ListViewAdapter myAdapter;
    private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);


        listView = findViewById(R.id.listViewDrink);

        List<Drink> image_details = getListData();
        final ListView listView = (ListView) findViewById(R.id.listViewDrink);
        listView.setAdapter(new Drink_Adapter(this, image_details));

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
