package com.example.chloechoi.cctvparkingcontrolproject.test;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chloechoi.cctvparkingcontrolproject.R;

public class TestActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View v) {
        if (isMyServiceRunning(MyService.class)) return;
        Intent startIntent = new Intent(this, MyService.class);
        startIntent.setAction("start");
        startService(startIntent);
    }

//    public void stop(View v) {
//        if (!isMyServiceRunning(MyService.class)) return;
//        Log.d("0207", "start: my service is running");
//        Intent stopIntent = new Intent(this, MyService.class);
//        stopIntent.setAction("stop");
//        startService(stopIntent);
//    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
