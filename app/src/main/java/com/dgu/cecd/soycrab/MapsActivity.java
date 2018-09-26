package com.dgu.cecd.soycrab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean isGPSEnabled, isNetworkEnabled;
    private MyPermissionListner mPermissionListner;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double myLatitude, myLongtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mPermissionListner = new MyPermissionListner();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        new TedPermission(this).setPermissionListener(mPermissionListner)
                .setDeniedMessage("need location authorization\n 1. press 설정 \n 2. press 권한 \n location on")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .setGotoSettingButton(true)
                .setGotoSettingButtonText("설정")
                .check();

    }

    private class MyPermissionListner implements PermissionListener {
        private LocationManager locationManager = (LocationManager) MapsActivity.this.getSystemService(Context.LOCATION_SERVICE);

        @Override
        public void onPermissionGranted() {
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isNetworkEnabled || !isGPSEnabled) {
                Log.v("dek", "permission denied");
            } else {
                Log.v("dek", "permission success");
                getCurrentLocation();
            }
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MapsActivity.this, "permission needed : " + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        OnCompleteListener<Location> completeListener = new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    Location currentlocation = task.getResult();
                    myLatitude = currentlocation.getLatitude();
                    myLongtitude = currentlocation.getLongitude();
                    mapSync();
                } else {
                }
            }
        };
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(MapsActivity.this, completeListener);
    }

    private void mapSync() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this); //onMapReady() 실행됨.
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.521504, 126.954152),10)); //start at Seoul

        LatLng myLocationLating = new LatLng(myLatitude,myLongtitude);
        MarkerOptions myMarker = new MarkerOptions().position(myLocationLating).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_location)).title("MyLocation");

        CircleOptions circle = new CircleOptions().center(myLocationLating).radius(60).strokeWidth(0f).fillColor(Color.parseColor("#33ff0000"));
        mMap.addCircle(circle);
        mMap.addMarker(myMarker);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocationLating,16));
    }
}
