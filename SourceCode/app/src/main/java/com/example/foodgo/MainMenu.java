package com.example.foodgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodgo.DatabaseModel.MyHelper;
import com.example.foodgo.Entity.Cart;
import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.User;
import com.example.foodgo.Entity.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private List<Category> categoryList;
    private ListViewAdapter myAdapter;
    private TextView view;
    private DrawerLayout drawer;
    private String email;
    private MyHelper myHelper;
    private TextView location;
    public static ArrayList<Cart> cartArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        view = findViewById(R.id.textView2);
        listView = findViewById(R.id.list_view);
        Intent intent = this.getIntent();
        view = findViewById(R.id.textView2);
        myHelper = new MyHelper(this);

        UserAddress userAddress = new UserAddress();
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView username = headerView.findViewById(R.id.txtNameOfUser);
        username.setText(userAddress.getEmail());
        userAddress = myHelper.getDataAddress(Login.userinfor.getUsername());
        User user = new User();
        user = myHelper.getDataUser(userAddress.getEmail());
        TextView firstname = headerView.findViewById(R.id.txtFirstOfUser);
        firstname.setText("Hello " + Login.userinfor.getFirstname());
        location = headerView.findViewById(R.id.locationOfUser);
        if (userAddress.getCity() == null) {
            location.setText("You need to open GPS");
        } else {
            location.setText("Your location : " + userAddress.getCity());
        }
        if (cartArrayList != null) {

        } else {
            cartArrayList = new ArrayList<>();
        }

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

        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_order: {
                Intent intent = new Intent(MainMenu.this, Cart_layout.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_drink: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DrinkMenu()).commit();
                break;
            }
            case R.id.nav_fastfood: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fast_Food_Menu()).commit();
                break;
            }
            case R.id.nav_mealmain: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Meal_Menu()).commit();
                break;
            }
            case R.id.nav_soup: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Soup_Menu()).commit();
                break;
            }
            case R.id.nav_logout: {

                Intent it = new Intent(MainMenu.this, Login.class);
                startActivity(it);
                Login.save(getApplicationContext(), "session", "false");
                break;
            }
            case R.id.nav_contact: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Contact_Fragment()).commit();
                break;
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen((GravityCompat.START))) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}
