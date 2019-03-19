package com.example.foodgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.foodgo.Entity.Category;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    private ListView listView;
    private List<Category> categoryList;
    private ListViewAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        listView = findViewById(R.id.list_view);
        categoryList = new ArrayList<>();
        myAdapter = new ListViewAdapter(this, R.layout.listview_layout, categoryList);
        categoryList.add(new Category(1, "Drink Menu", R.drawable.drink));
        categoryList.add(new Category(2,"Fast Food Menu", R.drawable.fastfood));
        categoryList.add(new Category(3,"Soup Menu", R.drawable.soup));
        categoryList.add(new Category(4, "Main Meal Menu",R.drawable.mainmenu));
        listView.setAdapter(myAdapter);
    }
}
