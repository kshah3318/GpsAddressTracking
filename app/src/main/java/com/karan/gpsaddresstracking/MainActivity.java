package com.karan.gpsaddresstracking;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvAddress;
    AppLocationService appLocationService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvAddress=(TextView)findViewById(R.id.txtDispAddress);
        appLocationService=new AppLocationService(this);
        if(appLocationService.canGetLocation())
        {
            double latitude=appLocationService.getLatitude();
            double longtitude=appLocationService.getLongitude();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longtitude
                    , Toast.LENGTH_LONG).show();
            tvAddress.setText(appLocationService.getAddressLineOne(this)+appLocationService.getLocality(this)+appLocationService.getAddressLineTwo(this));
        }
        else
        {
            appLocationService.showSettingsAlert();
        }
    }
}

