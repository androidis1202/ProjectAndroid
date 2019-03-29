package com.example.foodgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.foodgo.Entity.Cart;
import com.example.foodgo.Entity.Drink;

public class DetailFood extends AppCompatActivity {

    private Spinner spinner;
    private Button addtocart;

    private ImageView image;
    private TextView namefood;
    private TextView price;
    private TextView description;
    //public int number1;
    public Integer[] number = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        image = findViewById(R.id.imageView3);
        namefood = findViewById(R.id.txtFoodName);
        price = findViewById(R.id.txtFoodPrice);
        description = findViewById(R.id.txtDescription);
        Intent intent = getIntent();
        final Drink drink = (Drink) intent.getSerializableExtra("info");

        namefood.setText(drink.getName());
        price.setText(String.valueOf(drink.getPrice()));
        image.setImageResource(drink.getImage());
        description.setText(drink.getDescription());

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, number);
        spinner.setAdapter(arrayAdapter);

        float sumofMoney = (Integer.parseInt(spinner.getSelectedItem().toString()) + 1) * drink.getPrice();
        price.setText(String.valueOf(sumofMoney));

        addtocart = findViewById(R.id.addtocard);
        //number1 = Integer.parseInt(spinner.getSelectedItem().toString());
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainMenu.cartArrayList.size() > 0) {
                    int checkExistCard = 0;
                    for (int i = 0; i < MainMenu.cartArrayList.size(); i++) {
                        if (MainMenu.cartArrayList.get(i).getFoodid() == drink.getId()) {
                            MainMenu.cartArrayList.get(i).setFoodnumber(MainMenu.cartArrayList.get(i).getFoodnumber() + Integer.parseInt(spinner.getSelectedItem().toString()));
                            checkExistCard = 1;
                        }
                        MainMenu.cartArrayList.get(i).setPricename(drink.getPrice() * MainMenu.cartArrayList.get(i).getFoodnumber());
                    }
                    if (checkExistCard == 0) {
                        float sum = Integer.parseInt(spinner.getSelectedItem().toString()) * drink.getPrice();
                        MainMenu.cartArrayList.add(new Cart(drink.getId(), drink.getName(), sum, drink.getImage(), Integer.parseInt(spinner.getSelectedItem().toString()), LocationActivity.userInfor.getUsername()));
                    }
                } else {
                    //int number = Integer.parseInt(spinner.getSelectedItem().toString());
                    float sum = Integer.parseInt(spinner.getSelectedItem().toString()) * drink.getPrice();
                    MainMenu.cartArrayList.add(new Cart(drink.getId(), drink.getName(), sum, drink.getImage(), Integer.parseInt(spinner.getSelectedItem().toString()), LocationActivity.userInfor.getUsername()));
                }
                Intent intent = new Intent(getApplicationContext(), Cart_layout.class);
                startActivity(intent);
            }
        });

    }
}
