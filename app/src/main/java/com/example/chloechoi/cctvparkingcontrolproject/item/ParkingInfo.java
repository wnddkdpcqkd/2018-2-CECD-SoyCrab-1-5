package com.example.chloechoi.cctvparkingcontrolproject.item;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ParkingInfo {
    private Context mContext;
    private double latitude;
    private double longitude;
    private List<Address> address;
    //private SimpleDateFormat parkedTime;
    private String parkedTime;
    private int image;

    private Geocoder geocoder;

    public ParkingInfo(double latitude, double longitude, Context mContext){
        this.latitude = latitude;
        this.longitude = longitude;
        this.mContext = mContext;

        setData();
    }

    private void setData(){
        geocoder = new Geocoder(mContext);
        address = null;

        try {
            address = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        parkedTime = sdf.format(date);

        Log.d("~~~~", parkedTime);
    }

    public String getSimpleAddress(){
        String simpleAddress = "";

        simpleAddress += (address.get(0).getSubLocality() + " ");
        simpleAddress += (address.get(0).getThoroughfare() + " ");
        simpleAddress += address.get(0).getSubThoroughfare();

        return simpleAddress;
    }

    public String getSimpleTime(){
        String[] strArr = parkedTime.split(" ");

        String simpleTime = strArr[2];

        return simpleTime;
    }
}
