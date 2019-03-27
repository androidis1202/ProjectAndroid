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
    private  TextView description;

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


        final Integer[] number = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, number);
        spinner.setAdapter(arrayAdapter);

        float sumofMoney = Integer.parseInt(spinner.getSelectedItem().toString()) * drink.getPrice();
        price.setText(String.valueOf(sumofMoney));

        addtocart = findViewById(R.id.addtocard);
        final int number1 = Integer.parseInt(spinner.getSelectedItem().toString());
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (MainMenu.cartArrayList.size() > 0) {
                 boolean checkExistCard = false;
                 for (int i = 0; i < MainMenu.cartArrayList.size(); i++) {
                     if (MainMenu.cartArrayList.get(i).getFoodid() == drink.getId()) {
                         MainMenu.cartArrayList.get(i).setFoodnumber(MainMenu.cartArrayList.get(i).getFoodnumber() + number1);
                     }
                     MainMenu.cartArrayList.get(i).setPricename(drink.getPrice() * MainMenu.cartArrayList.get(i).getFoodnumber());
                     checkExistCard = true;
                 }
                 if (checkExistCard == false) {
                     float sum = number1 * drink.getPrice();
                     MainMenu.cartArrayList.add(new Cart(drink.getId(), drink.getName(), sum, drink.getImage(), number1));
                 }
             } else {
                //int number = Integer.parseInt(spinner.getSelectedItem().toString());
                float sum = number1 * drink.getPrice();
                MainMenu.cartArrayList.add(new Cart(drink.getId(), drink.getName(), sum, drink.getImage(), number1));
           }
            Intent intent = new Intent(getApplicationContext(), Cart_layout.class);
                startActivity(intent);
            }
        });

    }
}
