package com.example.chloechoi.cctvparkingcontrolproject.main.Join;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.chloechoi.cctvparkingcontrolproject.R;
import com.example.chloechoi.cctvparkingcontrolproject.main.MainActivity;

/**
 * Created by chloechoi on 04/12/2018.
 */

public class JoinCompletedActivity extends android.support.v4.app.FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_completed);

        findViewById(R.id.btn_start_app).setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(JoinCompletedActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
