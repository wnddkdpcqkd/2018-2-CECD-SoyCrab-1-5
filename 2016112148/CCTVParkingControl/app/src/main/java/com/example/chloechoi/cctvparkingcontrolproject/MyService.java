package com.example.chloechoi.cctvparkingcontrolproject;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by chloechoi on 27/11/2018.
 */

public class MyService extends Service {

    private Handler h;
    private Runnable r;

    int counter = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        h = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                startForeground(101, updateNotification());
                Log.d("0207", "service - onCreate: started foreground");
                h.postDelayed(this, 1000);
            }
        };
    }

    private Notification updateNotification() {
        counter++;

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        String info = counter + "";
        return new NotificationCompat.Builder(this)
                .setContentTitle(info)
                .setTicker(info)
                .setContentText(info)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setOngoing(true).build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("0207", "service - onStartCommand");

        if (intent.getAction().contains("start")) {
            Log.d("0207", "service - start: my service is not running");
            h = new Handler();
            r = new Runnable() {
                @Override
                public void run() {
                    startForeground(101, updateNotification());
                    Log.d("0207", "service - start: started foreground");
                    h.postDelayed(this, 1000);
                }
            };

            h.post(r);
        } else {
            Log.d("0207", "service - stop: my service is running");
            h.removeCallbacks(r);
            stopForeground(true);
            Log.d("0207", "service - stop: stoped foreground");
            stopSelf();
        }

        return Service.START_STICKY;
    }
}