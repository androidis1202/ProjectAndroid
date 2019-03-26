package com.example.foodgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class Cart_layout extends AppCompatActivity {

    ListView card;
    TextView txtInformation;
    TextView txtSum;
    Button btnPay, btnContinue;
    Toolbar toolbarcart;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);
        card = findViewById(R.id.listviewCart);
        txtInformation = findViewById(R.id.txtInfor);
        txtSum = findViewById(R.id.txtSumOfMoney);
        btnPay = findViewById(R.id.btnSubmitCart);
        btnContinue = findViewById(R.id.btnContinue);
        toolbarcart = findViewById(R.id.toolbarcart);
        cartAdapter = new CartAdapter(this,MainMenu.cartArrayList);
        card.setAdapter(cartAdapter);
    }
}
