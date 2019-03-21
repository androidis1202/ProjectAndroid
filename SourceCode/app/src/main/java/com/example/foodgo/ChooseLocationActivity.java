package com.example.foodgo;

import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodgo.DatabaseModel.MyHelper;
import com.example.foodgo.Entity.Category;
import com.example.foodgo.Entity.User;
import com.example.foodgo.Entity.UserAddress;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChooseLocationActivity extends AppCompatActivity {

    private TextView txtAddress;
    private TextView txtCity;
    private TextView txtState;
    private TextView txtCountry;
    private TextView txtPostalCode;
    private String email;
    private MyHelper myHelper;
    private Button btnOk;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_location);

        txtAddress = findViewById(R.id.txtAddress);
        txtCity = findViewById(R.id.txtCity);
        txtState = findViewById(R.id.txtState);
        txtCountry = findViewById(R.id.txtCountry);
        txtPostalCode = findViewById(R.id.txtPostalCode);
        btnOk = findViewById(R.id.btnOKChooseLocation);
        btnCancel = findViewById(R.id.btnCancelLocation);
        myHelper = new MyHelper(this);

        Intent intent = this.getIntent();
        email = intent.getStringExtra("emailAddressChoose");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAddress userAddress = new UserAddress();
                userAddress.setAddress(txtAddress.getText().toString());
                userAddress.setCity(txtCity.getText().toString());
                userAddress.setState(txtState.getText().toString());
                userAddress.setCountry(txtCountry.getText().toString());
                userAddress.setPostalCode(txtPostalCode.getText().toString());
                userAddress.setKnownName("VietNam");
                userAddress.setEmail(email);
                myHelper.insertDataAddress(userAddress);

                //  txtAddress.setText(userAddress.toString());
                Intent intent = new Intent(ChooseLocationActivity.this, MainMenu.class);
                intent.putExtra("userAddressManual", userAddress);
                startActivity(intent);
                finish();
            }
        });


    }
}
