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

    public String getSimpleLocation(){

        /**
         * TODO
         * 멤버변수 locationStr을 이용
         * return 간단한 형태의 location
         * ex. 을지로 4가 36-2
         */

        return "TODO";
    }
}
