package com.example.foodgo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodgo.DatabaseModel.MyHelper;
import com.example.foodgo.Entity.User;

public class Sign_up extends AppCompatActivity {

    private EditText firstname, lastname, address, phonenumber, email, password, repassword;
    private Button btnSign_up;
    MyHelper myDB;
    private TextView txtError;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstname = findViewById(R.id.editFirstName);
        lastname = findViewById(R.id.editLastName);
        address = findViewById(R.id.editAddress);
        phonenumber = findViewById(R.id.editPhoneNumber);
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        repassword = findViewById(R.id.edit_confirm_pass);
        btnSign_up = findViewById(R.id.btnSignup_activity_sign_up);
        txtError = findViewById(R.id.txtError_Sign_up);
        myDB = new MyHelper(this);
        btnSign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!password.getText().toString().equals(repassword.getText().toString()) || repassword.getText().toString().equals("") || password.getText().toString().equals("")) {
                    txtError.setText("Confirm password is incorrect. Please input confirm password again !");
                    return;
                } else if (myDB.checkExistAccount(email.getText().toString())) {
                    txtError.setText("Account is already existed. Please input another account !");
                    return;
                } else if (firstname.getText().toString().equals("") || lastname.getText().toString().equals("") || address.getText().toString().equals("") || phonenumber.getText().toString().equals("") || email.getText().toString().equals("")) {
                    Toast.makeText(Sign_up.this, "You need to fill all information", Toast.LENGTH_LONG).show();
                    txtError.setText("You need to fill all information!");
                    return;
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    Toast.makeText(Sign_up.this, "Email is invalid ! Please input again", Toast.LENGTH_LONG).show();
                    return;
                } else if (phonenumber.getText().toString().length() < 10 || password.getText().toString().length() > 11) {
                    Toast.makeText(Sign_up.this, "Phonenumber is invalid ! Please input again", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    final User user = new User();
                    user.setFirstname(firstname.getText().toString());
                    user.setLastname(lastname.getText().toString());
                    user.setAddress(address.getText().toString());
                    user.setPhonenumber(phonenumber.getText().toString());
                    user.setUsername(email.getText().toString());
                    user.setPassword(password.getText().toString());
                    boolean isInserted = myDB.insertData(user);
                    if (isInserted == false) {
                        txtError.setText("Inserted is unsuccessful. Please try again!");
                        return;
                    } else {
                        final ProgressDialog progressDialog = new ProgressDialog(Sign_up.this);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                        new android.os.Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(Sign_up.this, Login.class);
                                intent.putExtra("userinformation", user);
                                setResult(200, intent);
                                finish();
                                progressDialog.dismiss();
                            }
                        }, 3000);
                    }
                }
            }
        });

    }
}
