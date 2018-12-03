package com.example.chloechoi.cctvparkingcontrolproject.Join;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.example.chloechoi.cctvparkingcontrolproject.R;

/**
 * Created by chloechoi on 03/12/2018.
 */

public class JoinStageActivity extends android.support.v4.app.FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Intent intent = new Intent(this.getIntent());
        String s = intent.getStringExtra("userName");
        EditText userName = (EditText) findViewById(R.id.join_userName);
        userName.setText(s);
    }
}
