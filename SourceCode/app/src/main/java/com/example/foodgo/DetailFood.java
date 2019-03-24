package com.example.foodgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DetailFood extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);
        Integer[] number = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,number);
        spinner.setAdapter(arrayAdapter);
    }
}
