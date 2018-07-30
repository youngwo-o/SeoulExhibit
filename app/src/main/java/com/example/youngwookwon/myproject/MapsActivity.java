package com.example.youngwookwon.myproject;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.youngwookwon.myproject.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback{
    String title;
    String place;
    Double latitude;
    Double longitude;

    GoogleMap mMap;
    Geocoder geocoder;

    @BindView(R.id.map_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        place = intent.getExtras().getString("place");
        latitude = intent.getDoubleExtra("latitude", -1);
        longitude = intent.getDoubleExtra("longitude", -1);

        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng address = new LatLng(latitude, longitude);// LatLng: 위도 경도 쌍을 나타냄

        MarkerOptions mOptions = new MarkerOptions();
        mOptions.position(address);
        mMap.addMarker(mOptions);// 마커(핀) 추가
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(address,15));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}