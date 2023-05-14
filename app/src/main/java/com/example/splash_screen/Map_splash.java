package com.example.splash_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map_splash extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Spinner mMapSpinner;
    private Button locationbtn;
    private int mMapType = GoogleMap.MAP_TYPE_NORMAL;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_splash);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapContainer);
        mapFragment.getMapAsync(this);
locationbtn=findViewById(R.id.map_btn);
        //button
        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map_splash.this, Map_splash.class);
                intent.putExtra("map_types",mMapType);
                startActivity(intent);
            }
        });
        mMapType=getIntent().getIntExtra("map_type", GoogleMap.MAP_TYPE_NORMAL);
        mMapSpinner = findViewById(R.id.map_types_spinner);
        mMapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mMapType=GoogleMap.MAP_TYPE_HYBRID;
                        break;
                    case 1:
                        mMapType=GoogleMap.MAP_TYPE_SATELLITE;
                        break;
                    case 2:
                        mMapType=GoogleMap.MAP_TYPE_NORMAL;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap=googleMap;
        LatLng sydney = new LatLng(28.618405020120065, 77.20254573444758151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Delhi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
