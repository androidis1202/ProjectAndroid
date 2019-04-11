package com.example.foodgo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodgo.DatabaseModel.MyHelper;
import com.example.foodgo.Entity.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class Login extends AppCompatActivity {

    private TextView txtForgot;

    private Button btnSign_up;
    private Button btnLogin;
    private EditText txtEmail, txtPassword;
    private TextView txtTitle;
    private MyHelper myHelper;
    private LoginButton btn_FB;
    private CheckBox cbShowPassword;
    private CallbackManager mCallbackManager;
    private static final String EMAIL = "email";
    public static User userinfor = new User();
    private boolean session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtForgot = findViewById(R.id.txtForgot);
        cbShowPassword = findViewById(R.id.cbShowPassword);
        btnSign_up = findViewById(R.id.btnSignup_login);
        btnLogin = findViewById(R.id.txtSign_in);
        txtEmail = findViewById(R.id.editText_email_sign_in);
        txtTitle = findViewById(R.id.sign_in_title);
        txtPassword = findViewById(R.id.txtPassword_login);
        myHelper = new MyHelper(this);
        session();
        btnSign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign_up = new Intent(Login.this, Sign_up.class);
                startActivityForResult(sign_up, 100);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkedAccount = myHelper.checkAccountLogin(txtEmail.getText().toString(), txtPassword.getText().toString());
                if (checkedAccount == true) {
                    save(getApplicationContext(), "session", "true");
                    final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Login.this, LocationActivity.class);
                            userinfor = myHelper.getDataUser(txtEmail.getText().toString());
                            startActivity(intent);
                            finish();
                            progressDialog.dismiss();
                        }
                    }, 2000);
                } else {
                    Toast.makeText(Login.this, "Your Account is incorrect! Please try again !", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(Login.this, ForgotPassword.class);
                startActivityForResult(forgotPassword, 100);
            }
        });
        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    txtPassword.setInputType(129);
                } else {
                    txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        btn_FB = (LoginButton) findViewById(R.id.btn_fb);
        btn_FB.setReadPermissions(Arrays.asList(EMAIL));
        mCallbackManager = CallbackManager.Factory.create();
        btn_FB.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(Login.this, "Your Account is IN FACEBOOK !", Toast.LENGTH_LONG).show();
                GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {
                                if (response.getError() != null) {
                                } else {
                                    userinfor.setPassword(me.optString("id"));
                                    userinfor.setFirstname(me.optString("name"));
                                    userinfor.setPhonenumber("Facebook account");
                                    userinfor.setUsername(me.optString("name") + "@gmail.com");
                                    myHelper.insertData(userinfor);
                                    userinfor = myHelper.getDataUser(me.optString("id"));
                                    Intent intent = new Intent(Login.this, LocationActivity.class);
                                    intent.putExtra("userinfor", userinfor);
                                    startActivity(intent);
                                }
                            }
                        }).executeAsync();

            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == 200) {
                User user = (User) data.getSerializableExtra("userinformation");
                userinfor = user;
                txtEmail.setText(String.valueOf(user.getUsername()));
                txtTitle.setText("Welcome " + String.valueOf(user.getFirstname()) + " " + String.valueOf(user.getLastname()));
            }
        }
//        if (requestCode == 120) {
//            if (resultCode == 220) {
//                Intent intent = new Intent(Login.this, LocationActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }

    }

    public void session() {
        session = Boolean.valueOf(read(getApplicationContext(), "session", "false"));
        if (session == false) {
            return;
        } else {
            Intent intent = new Intent(Login.this, LocationActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You are in login", Toast.LENGTH_LONG).show();
        }
    }

    public static void save(Context ctx, String name, String value) {
        SharedPreferences s = ctx.getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = s.edit();

        editor.putString(name, value);
        editor.apply();
    }

    public static String read(Context context, String name, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
        return sharedPreferences.getString(name, defaultValue);
    }


}
