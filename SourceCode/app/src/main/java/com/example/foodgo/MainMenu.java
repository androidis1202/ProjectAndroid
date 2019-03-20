package com.example.foodgo;

import android.content.Intent;
import android.location.Address;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    private ListView listView;
    private List<Category> categoryList;
    private ListViewAdapter myAdapter;
private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        view = findViewById(R.id.textView2);
        Intent intent = getIntent();
        UserAddress userAddress = (UserAddress) intent.getSerializableExtra("userinformation");
        listView = findViewById(R.id.list_view);
        view.setText("You are in " + userAddress.getCity());
        categoryList = new ArrayList<>();
        myAdapter = new ListViewAdapter(this, R.layout.listview_layout, categoryList);
        categoryList.add(new Category(1, "Drink Menu", R.drawable.drink));
        categoryList.add(new Category(2,"Fast Food Menu", R.drawable.fastfood));
        categoryList.add(new Category(3,"Soup Menu", R.drawable.soup));
        categoryList.add(new Category(4, "Main Meal Menu",R.drawable.mainmenu));
        listView.setAdapter(myAdapter);
    }
}
