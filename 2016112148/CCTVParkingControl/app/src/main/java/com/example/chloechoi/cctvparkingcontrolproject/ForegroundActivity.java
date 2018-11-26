package com.example.chloechoi.cctvparkingcontrolproject;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chloechoi on 27/11/2018.
 */

public class ForegroundActivity extends AppCompatActivity {
    int counter = 0;

    private Handler h;
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);

        final TextView counterView = (TextView)findViewById(R.id.printCounter);
            h = new Handler();
            r = new Runnable() {
                @Override
                public void run() {
                    Log.d("0207", "forgroind : counter++");
                    counter++;
                    counterView.setText(String.valueOf(counter));
                    h.postDelayed(this, 1000);
                }
            };

            h.post(r);
    }

    public void stop(View v) {
        if (!isMyServiceRunning(MyService.class)) return;
        Intent stopIntent = new Intent(this, MyService.class);
        stopIntent.setAction("stop");
        startService(stopIntent);
    }

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
