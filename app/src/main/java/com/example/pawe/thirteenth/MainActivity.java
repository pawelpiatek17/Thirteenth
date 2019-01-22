package com.example.pawe.thirteenth;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                Toast.makeText(MainActivity.this, "bca", Toast.LENGTH_SHORT).show();
                TextView LatTv = findViewById(R.id.textView2);
                TextView LongTv = findViewById(R.id.textView4);
                TextView UpdateTv = findViewById(R.id.textView6);
                if (location != null) {
                    LatTv.setText(String.valueOf(location.getLatitude()));
                    LongTv.setText(String.valueOf(location.getLongitude()));
                    UpdateTv.setText(new Date().toString());
                    SharedPreferences sharedPreferences = getSharedPreferences("com.example.pawe.thirteenth.locFile",MODE_PRIVATE);
                    sharedPreferences.edit().putString(new Date().toString(),location.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Location null, check location settings", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        Toast.makeText(this, "abc", Toast.LENGTH_LONG).show();
    }
}
