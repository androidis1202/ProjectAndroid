package com.example.foodgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodgo.DatabaseModel.MyHelper;

public class ConfirmPassword extends AppCompatActivity {

    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private MyHelper myHelper;
    private Button btnReset;

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);

        myHelper = new MyHelper(this);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        btnReset = findViewById(R.id.btnReset);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");
        setTitle("Reset Password");

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });
    }
    private void updatePassword(){
        String value1 = txtPassword.getText().toString().trim();
        String value2 = txtConfirmPassword.getText().toString().trim();

        if(value1.isEmpty() && value2.isEmpty()){
            Toast.makeText(this, "fill all fields ", Toast.LENGTH_LONG).show();
            return;
        }
        if (!value1.contentEquals(value2)){
            Toast.makeText(this, "password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }
        if (!myHelper.checkExistAccount(email)) {

            Toast.makeText(this, "email doesn't exist", Toast.LENGTH_LONG).show();
            return;

        } else {

            myHelper.updatePassword(email,value1);

            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }
    private void emptyInputEditText()
    {
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }
}
