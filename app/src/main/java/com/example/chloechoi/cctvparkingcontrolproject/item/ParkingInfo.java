package com.example.chloechoi.cctvparkingcontrolproject.item;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ParkingInfo {
    private Context mContext;
    private double latitude;
    private double longitude;
    private List<Address> address;
    private SimpleDateFormat parkedTime;
    private int image;

    private Geocoder geocoder;

    public ParkingInfo(double latitude, double longitude, Context mContext){
        this.latitude = latitude;
        this.longitude = longitude;
        this.mContext = mContext;

        geocoder = new Geocoder(mContext);
        address = null;

        try {
            address = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Address ad : address){
            Log.d("~~~~", ad.toString());
        }
    }

    public String getSimpleAddress(){
        String simpleAddress = "";

        simpleAddress += address.get(0).getSubLocality();
        simpleAddress += address.get(0).getThoroughfare();
        simpleAddress += address.get(0).getSubThoroughfare();
        simpleAddress += address.get(0).getFeatureName();

        return simpleAddress;
    }
}
