package com.example.chloechoi.cctvparkingcontrolproject;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chloechoi on 27/11/2018.
 */

public class ForegroundActivity extends AppCompatActivity {
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);

        final TextView counterView = (TextView)findViewById(R.id.printCounter);

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                counterView.setText(counter);
            }
        };

        Timer timer =  new Timer();
        timer.schedule(tt, 0, 1000);
    }
}
