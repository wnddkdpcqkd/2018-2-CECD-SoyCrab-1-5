package com.example.chloechoi.cctvparkingcontrolproject.Fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chloechoi.cctvparkingcontrolproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.gun0912.tedpermission.TedPermission;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean isGPSEnabled, isNetworkEnabled;
    private MyPermissionListner mPermissionListner;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double myLatitude, myLongtitude;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container, false);

        mPermissionListner = new MyPermissionListner();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        new TedPermission(this).setPermissionListener(mPermissionListner)
                .setDeniedMessage("need location authorization\n 1. press 설정 \n 2. press 권한 \n location on")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .setGotoSettingButton(true)
                .setGotoSettingButtonText("설정")
                .check();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
