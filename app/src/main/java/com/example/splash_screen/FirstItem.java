package com.example.splash_screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class FirstItem extends AppCompatActivity {

    private Button btn, locbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_section1);
        Button call_btn = (Button) findViewById(R.id.call_button);
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Phone_no = "tel:(+91)9871448226";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(Phone_no));
                startActivity(intent);
            }
        });
        Button buttonn = (Button) findViewById(R.id.bookbtn_id);
        //booking button
        buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity4Intent = new Intent(getApplicationContext(), Get_booking_details.class);
                startActivity(activity4Intent);
            }
        });


        //button
        locbtn = findViewById(R.id.location_button);
        locbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstItem.this, Map_splash.class);
                startActivity(intent);
            }
        });
       /* mMapType = getIntent().getIntExtra("map_type", GoogleMap.MAP_TYPE_NORMAL);
        mMapSpinner = findViewById(R.id.map_types_spinner);
        mMapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mMapType = GoogleMap.MAP_TYPE_HYBRID;
                        break;
                    case 1:
                        mMapType = GoogleMap.MAP_TYPE_SATELLITE;
                        break;
                    case 2:
                        mMapType = GoogleMap.MAP_TYPE_NORMAL;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(28.618405020120065, 77.20254573444758151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Delhi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        //ending of map section

//calling section

    */}

}