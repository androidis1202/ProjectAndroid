package com.example.foodgo;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodgo.Entity.Category;

import java.util.List;

public class MainMenu extends AppCompatActivity {

    private ListView listView;
    private List<Category> categoryList;
    private ListViewAdapter myAdapter;
    private TextView view;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        view = findViewById(R.id.textView2);
        listView = findViewById(R.id.list_view);
        view = findViewById(R.id.textView2);
        drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);

//        Intent intent = getIntent();
////        UserAddress userAddress = (UserAddress) intent.getSerializableExtra("userAddress");
////        Intent intent2 = getIntent();
////        UserAddress userAddress2 = (UserAddress) intent2.getSerializableExtra("userAddressManual");
////        setSupportActionBar(toolbar);
//
////        categoryList = new ArrayList<>();
////        myAdapter = new ListViewAdapter(this, R.layout.listview_layout, categoryList);
////        categoryList.add(new Category(1, "Drink Menu", R.drawable.drink));
////        categoryList.add(new Category(2,"Fast Food Menu", R.drawable.fastfood));
////        categoryList.add(new Category(3,"Soup Menu", R.drawable.soup));
////        categoryList.add(new Category(4, "Main Meal Menu",R.drawable.mainmenu));
////        listView.setAdapter(myAdapter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListViewFragement()).commit();
        }




//        if(userAddress == null)
//        {
//            view.setText("You are in " + userAddress2.getCity());
//        }else{
//            view.setText("You are in " + userAddress.getCity());
//        }




    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen((GravityCompat.START))){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
}
