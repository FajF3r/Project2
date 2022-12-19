package com.barstool.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private LocationListener locationListener;
    private LocationManager locationManager;

    ImageView img;
    Button btn;
    Switch onOff;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.imageView);
        btn = findViewById(R.id.butName);
        onOff = findViewById(R.id.onOff);
        txt = findViewById(R.id.lokalizacjaText);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                try{
                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
                    String adres = addresses.get(0).getAddressLine(0);
                    txt.setText("\nKoordynaty: " + location.getLatitude() + " , " + location.getLongitude() + "\n\n\nAdres: " + adres);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
    }
}