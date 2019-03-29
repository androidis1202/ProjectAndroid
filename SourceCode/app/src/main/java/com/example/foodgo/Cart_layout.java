package com.example.foodgo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.foodgo.DatabaseModel.MyHelper;
import com.example.foodgo.Entity.Cart;
import com.example.foodgo.Entity.Drink;
import com.example.foodgo.Entity.User;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Cart_layout extends AppCompatActivity {

    ListView card;
    TextView txtInformation;
    static TextView txtSum;
    Button btnPay, btnContinue;
    Toolbar toolbarcart;
    CartAdapter cartAdapter;
    MyHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_layout);
        card = findViewById(R.id.listviewCart);
        txtInformation = findViewById(R.id.txtInfor);
        txtSum = findViewById(R.id.txtSumOfMoney);
        btnPay = findViewById(R.id.btnSubmitCart);
        btnContinue = findViewById(R.id.btnContinue);
        myHelper = new MyHelper(this);
        if (MainMenu.cartArrayList.size() <= 0) {
            txtInformation.setVisibility(View.VISIBLE);
        } else {
            txtInformation.setVisibility(View.INVISIBLE);
        }




        sum();
        cartAdapter = new CartAdapter(this, MainMenu.cartArrayList);
        card.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Cart_layout.this);
                builder.setTitle("Delete product");
                builder.setMessage("Are you sure to delete product");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainMenu.cartArrayList.size() <= 0 || txtSum.getText().toString().equalsIgnoreCase("0$")) {
                            txtInformation.setVisibility(View.VISIBLE);
                        } else {
                            MainMenu.cartArrayList.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            sum();
                            if (MainMenu.cartArrayList.size() <= 0) {
                                txtInformation.setVisibility(View.VISIBLE);
                            } else {
                                txtInformation.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                sum();
                            }
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartAdapter.notifyDataSetChanged();
                        sum();
                    }
                });
                AlertDialog alert11 = builder.create();
                alert11.show();
                return true;
            }
        });
        card.setAdapter(cartAdapter);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LocationActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainMenu.cartArrayList.size() != 0)
                {
                    for(Cart cart : MainMenu.cartArrayList)
                    {
                        myHelper.insertCart(cart);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(Cart_layout.this);
                    builder.setMessage("You buy successful");
                    builder.setTitle("Success");
                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder.create();
                    alert11.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Cart_layout.this);
                    builder.setMessage("No cart.Please add your products");
                    builder.setTitle("Success");
                    builder.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder.create();
                    alert11.show();
                }
            }
        });
    }

    public static void sum() {
        float sum = 0;
        for (int i = 0; i < MainMenu.cartArrayList.size(); i++) {
            sum += MainMenu.cartArrayList.get(i).getPricename();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtSum.setText(decimalFormat.format(sum) + "$");
    }

}
