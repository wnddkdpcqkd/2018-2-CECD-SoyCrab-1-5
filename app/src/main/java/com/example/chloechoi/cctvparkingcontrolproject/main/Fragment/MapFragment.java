package com.example.chloechoi.cctvparkingcontrolproject.main.Fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chloechoi.cctvparkingcontrolproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double myLatitude, myLongtitude;

    private FusedLocationProviderClient fusedLocationProviderClient;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            myLatitude = getArguments().getDouble("myLatitude");
            myLongtitude = getArguments().getDouble("myLongtitude");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container, false);
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
        MarkerOptions myMarker = new MarkerOptions().position(myLocationLating).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_user_loca)).title("MyLocation");

        CircleOptions circle = new CircleOptions().center(myLocationLating).radius(60).strokeWidth(0f).fillColor(Color.parseColor("#33ff0000"));
        mMap.addCircle(circle);
        mMap.addMarker(myMarker);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocationLating,16));

        LatLng cctvLocationLating = new LatLng(myLatitude-0.1, myLongtitude+0.1);
        MarkerOptions cctvMarker = new MarkerOptions().position(cctvLocationLating).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_target_loca));
        mMap.addMarker(cctvMarker);
    }
}
