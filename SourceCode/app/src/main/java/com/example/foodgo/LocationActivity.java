package com.example.foodgo;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodgo.DatabaseModel.MyHelper;
import com.example.foodgo.Entity.User;
import com.example.foodgo.Entity.UserAddress;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private Location location;
    public static User userInfor = new User();
    private MyHelper database;
    private GoogleApiClient gac;

    private TextView tvLocation;
    private Button btnUseCurrentLocation;
    private  TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        welcome = findViewById(R.id.textView2);
        Intent intent = this.getIntent();
        userInfor = (User) intent.getSerializableExtra("userinfor");
        btnUseCurrentLocation = findViewById(R.id.btnChooseLocation);
        tvLocation = (TextView) findViewById(R.id.txtChooseManually);
        welcome.setText("Hi " + userInfor.getFirstname() + ", Nice to meet you");
        if (checkPlayServices()) {
            buildGoogleApiClient();
        }

        btnUseCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAddress userAddress = new UserAddress();
                userAddress = getLocation();
                if (userAddress != null) {
                    Intent intent = new Intent(LocationActivity.this, MainMenu.class);
                    startActivity(intent);
                }
            }
        });


        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this, ChooseLocationActivity.class);
                intent.putExtra("emailAddressChoose",userInfor.getUsername());
                startActivity(intent);
            }
        });

    }

    public void dispLocation(View view) {
        getLocation();
    }


    private UserAddress getLocation() {
        List<Address> addresses = null;
        UserAddress userAddress = new UserAddress();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        } else {
            location = LocationServices.FusedLocationApi.getLastLocation(gac);

            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();


                Geocoder geocoder;

                geocoder = new Geocoder(this, Locale.getDefault());

                try {

                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    String address = addresses.get(0).getAddressLine(0);
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();


                    userAddress.setAddress(address);
                    userAddress.setCity(city);
                    userAddress.setCountry(country);
                    userAddress.setKnownName(knownName);
                    userAddress.setState(state);
                    userAddress.setPostalCode(postalCode);
                    userAddress.setEmail(userInfor.getUsername());
                    database = new MyHelper(this);
                    database.insertDataAddress(userAddress);

                    tvLocation.setText(city);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
                tvLocation.setText("(Không thể hiển thị vị trí. " +
                        "Bạn đã kích hoạt location trên thiết bị chưa?)");
            }
        }
        return userAddress;
    }



    protected synchronized void buildGoogleApiClient() {
        if (gac == null) {
            gac = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1000).show();
            } else {
                Toast.makeText(this, "Thiết bị này không hỗ trợ.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // getLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        gac.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Lỗi kết nối: " + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();

    }

    protected void onStart() {
        gac.connect();
        super.onStart();
    }

    protected void onStop() {
        gac.disconnect();
        super.onStop();
    }
}
