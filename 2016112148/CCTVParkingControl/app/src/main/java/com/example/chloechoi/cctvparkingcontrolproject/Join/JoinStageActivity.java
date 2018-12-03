package com.example.chloechoi.cctvparkingcontrolproject.Join;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chloechoi.cctvparkingcontrolproject.R;

/**
 * Created by chloechoi on 03/12/2018.
 */

public class JoinStageActivity extends android.support.v4.app.FragmentActivity{
    String[] appList = {"카카오내비", "T map", "네이버 지도"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Intent intent = new Intent(this.getIntent());
        String s = intent.getStringExtra("userName");
        EditText userName = (EditText) findViewById(R.id.join_userName);
        userName.setText(s);
    }

    public void setUserApp(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("사용중인 내비앱을 선택하세요")
                .setSingleChoiceItems(appList, -1, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int index){
                        Toast.makeText(getApplicationContext(), appList[index], Toast.LENGTH_SHORT).show();
                        TextView userName = (TextView) findViewById(R.id.join_userApp);
                        userName.setText(appList[index]);
                    }

                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
