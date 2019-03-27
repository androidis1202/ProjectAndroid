package com.example.foodgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodgo.DatabaseModel.MyHelper;

public class ForgotPassword extends AppCompatActivity {

    private EditText txtEmail;
    private Button btnResetPassword;
    private MyHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        myHelper = new MyHelper(this);

        txtEmail = findViewById(R.id.txtEmail);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyFromSQlite();
            }
        });
    }
    private void verifyFromSQlite(){
        String email = txtEmail.getText().toString();
        Boolean checkExistAcc = myHelper.checkExistAccount(email);
        if(txtEmail.getText().toString().isEmpty()){
            Toast.makeText(this,"Please fill your email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(checkExistAcc){
            Intent accountIntents = new Intent(this, ConfirmPassword.class);
            accountIntents.putExtra("EMAIL", txtEmail.getText().toString().trim());
            startActivity(accountIntents);
        }else if(checkExistAcc == false){
            Toast.makeText(this, "Email not exist", Toast.LENGTH_LONG).show();
            return;
        }
    }
}
