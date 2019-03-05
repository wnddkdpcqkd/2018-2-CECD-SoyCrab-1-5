package com.example.chloechoi.cctvparkingcontrolproject.main.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chloechoi.cctvparkingcontrolproject.main.Fragment.history.HistoryFragment;
import com.example.chloechoi.cctvparkingcontrolproject.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class FragmentActivity extends android.support.v4.app.FragmentActivity{
    int curFragment;
    private MyPermissionListner mPermissionListner;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean isGPSEnabled, isNetworkEnabled;

    private double myLatitude, myLongtitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        /*TODO
        * 사용자가 마지막으로 접근한 프래그먼트 띄우기*/

        Fragment fragment = new MapFragment();
        curFragment = 0;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();

        mPermissionListner = new MyPermissionListner();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        new TedPermission(this).setPermissionListener(mPermissionListner)
                .setDeniedMessage("need location authorization\n 1. press 설정 \n 2. press 권한 \n location on")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .setGotoSettingButton(true)
                .setGotoSettingButtonText("설정")
                .check();
    }

    private void mapSync() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.to_map_fragment);
        mapFragment.getMapAsync((OnMapReadyCallback) FragmentActivity.this); //onMapReady() 실행됨.
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
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(this, completeListener);
    }

    private class MyPermissionListner implements PermissionListener {
        private LocationManager locationManager = (LocationManager) FragmentActivity.this.getSystemService(Context.LOCATION_SERVICE);

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
            Toast.makeText(FragmentActivity.this, "permission needed : " + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void ChangeFragment(View v){
        Fragment fragment;
        unsetBtnIcon();
        ImageView imageView;

        /*TODO
        * 이부분 스위치케이스말고 배열써서 더 효율적으로 구현하기*/

        switch(v.getId()){
            case R.id.to_map_fragment:{
                fragment = new MapFragment();
                Bundle args = new Bundle();
                args.putDouble("myLatitude", myLatitude);
                args.putDouble("mymyLongtitude", myLongtitude);
                fragment.setArguments(args);
                imageView = (ImageView) findViewById(R.id.to_map_icon);
                imageView.setImageResource(R.drawable.fragment_map_checked);
                curFragment = 0;
                break;
            }case R.id.to_write_fragment:{
                fragment = new WriteFragment();
                imageView = (ImageView) findViewById(R.id.to_write_icon);
                imageView.setImageResource(R.drawable.fragment_write_checked);
                curFragment = 1;
                break;
            }case R.id.to_history_fragment:{
                fragment = new HistoryFragment();
                imageView = (ImageView) findViewById(R.id.to_history_icon);
                imageView.setImageResource(R.drawable.fragment_history_checked);
                curFragment = 2;
                break;
            }case R.id.to_user_fragment:{
                fragment = new UserFragment();
                imageView = (ImageView) findViewById(R.id.to_user_icon);
                imageView.setImageResource(R.drawable.fragment_user_checked);
                curFragment = 3;
                break;
            }
            default:{
                fragment = new MapFragment();
                break;
            }
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void unsetBtnIcon(){
        ImageView imageView;
        switch(curFragment){
            case 0:{
                imageView = (ImageView) findViewById(R.id.to_map_icon);
                imageView.setImageResource(R.drawable.fragment_map_unchecked);
                break;
            }
            case 1:{
                imageView = (ImageView) findViewById(R.id.to_write_icon);
                imageView.setImageResource(R.drawable.fragment_write_unchecked);
                break;
            }
            case 2:{
                imageView = (ImageView) findViewById(R.id.to_history_icon);
                imageView.setImageResource(R.drawable.fragment_history_unchecked);
                break;
            }
            case 3:{
                imageView = (ImageView) findViewById(R.id.to_user_icon);
                imageView.setImageResource(R.drawable.fragment_user_unchecked);
                break;
            }
            default:
                break;
        }
    }
}
