package com.example.foodgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.foodgo.Entity.Cart;

public class DetailFood extends AppCompatActivity {

    private Spinner spinner;
    private Button addtocart;

    private int id = 0;
    private String nameOfFood = "";
    private int moneyOffood = 0;
    private String imageDetail = "";
    private String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        Integer[] number = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, number);
        spinner.setAdapter(arrayAdapter);
        addtocart = findViewById(R.id.addtocard);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainMenu.cartArrayList.size() > 0) {
                      int number = Integer.parseInt(spinner.getSelectedItem().toString());
                      boolean checkExistCard = false;
                      for(int i = 0; i <MainMenu.cartArrayList.size(); i++)
                      {
                          if(MainMenu.cartArrayList.get(i).getFoodid() == id)
                          {
                              MainMenu.cartArrayList.get(i).setFoodnumber(MainMenu.cartArrayList.get(i).getFoodnumber() + number);
                          }
                          MainMenu.cartArrayList.get(i).setPricename(moneyOffood * MainMenu.cartArrayList.get(i).getFoodnumber());
                          checkExistCard = true;
                      }
                      if (checkExistCard == false){
                          int number1 = Integer.parseInt(spinner.getSelectedItem().toString());
                          long sum = number1 * moneyOffood;
                          MainMenu.cartArrayList.add(new Cart(id,nameOfFood,sum,imageDetail,number1));
                      }
                }

                else {
                    int number = Integer.parseInt(spinner.getSelectedItem().toString());
                    long sum = number * moneyOffood;
                    MainMenu.cartArrayList.add(new Cart(id,nameOfFood,sum,imageDetail,number));
                }
                Intent intent = new Intent(getApplicationContext(), Cart_layout.class);
                startActivity(intent);
            }
        });

    }
}
