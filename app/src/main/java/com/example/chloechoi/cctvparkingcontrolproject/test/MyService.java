package com.example.chloechoi.cctvparkingcontrolproject.test;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.chloechoi.cctvparkingcontrolproject.R;

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
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        h = new Handler();
//        r = new Runnable() {
//            @Override
//            public void run() {
//                startForeground(101, updateNotification());
//                Log.d("0207", "service - onCreate: started foreground");
//                h.postDelayed(this, 1000);
//            }
//        };
//    }

    private Notification updateNotification() {
        counter++;

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, TestActivity.class), 0);

        return new NotificationCompat.Builder(this)
                .setContentTitle("soyCrab")
                .setTicker("started foreground")
                .setContentText("testing foreground service")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setOngoing(true).build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().contains("start")) {
            startForeground(101, updateNotification());
            Intent forgroundActivityIntent = new Intent(this, ForegroundActivity.class);
            startActivity(forgroundActivityIntent);
        } else {
            stopForeground(true);
            stopSelf();

            Intent mainActivityIntent = new Intent(this, TestActivity.class);
            startActivity(mainActivityIntent);
        }

        return Service.START_STICKY;
    }
}