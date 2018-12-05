package com.example.chloechoi.cctvparkingcontrolproject.Join;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chloechoi.cctvparkingcontrolproject.R;

public class JoinStageActivity extends android.support.v4.app.FragmentActivity{
    String[] appList = {"카카오내비", "T map", "네이버 지도"};

    /*TODO 사진 연동 & 모든 항목 채워지면 다음버튼 활성화*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Intent intent = new Intent(this.getIntent());
        String s = intent.getStringExtra("userName");
        EditText userName = (EditText) findViewById(R.id.join_userName);
        userName.setText(s);

        CheckItemThread thread = new CheckItemThread();
        thread.start();
    }

    public void toNextStage(View v){
        Log.d("0102", "btn touched");

        ImageView toNextBtn = (ImageView) findViewById(R.id.join_complete_btn);
        if(toNextBtn.getDrawable().getConstantState().equals
                (getResources().getDrawable(R.drawable.join_next_valid).getConstantState())){
            // 서버에 사용자 정보 전송
            Intent intent = new Intent(JoinStageActivity.this, JoinCompletedActivity.class);
            startActivity(intent);
        }
        else{
            Log.d("0102", "toNextStage: it isnt same drawanle");
        }
    }

    public void setUserApp(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("사용중인 내비앱을 선택하세요")
                .setSingleChoiceItems(appList, -1, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int index){
                        Toast.makeText(getApplicationContext(), appList[index], Toast.LENGTH_SHORT).show();
                        TextView userApp = (TextView) findViewById(R.id.join_userApp);
                        userApp.setText(appList[index]);
                    }

                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class CheckItemThread extends Thread {
        int state;
        public CheckItemThread(){
            state = 0;
        }

        @Override
        public void run() {
            super.run();
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int preState = state;
                EditText checkUserName = (EditText) findViewById(R.id.join_userName);
                TextView checkUserApp = (TextView) findViewById(R.id.join_userApp);
                ImageView toNextBtn = (ImageView) findViewById(R.id.join_complete_btn);

                if(checkUserName.getText().toString().equals
                        (getResources().getString(R.string.join_default_name))){
                    if(state == 1){
                        state = 0;
                        Log.d("inThread", "run: user name == defalt");
                        toNextBtn.setImageResource(R.drawable.join_next_invalid);
                    }
                    continue;
                }

                if(checkUserApp.getText().toString().equals
                        (getResources().getString(R.string.join_default_app))){
                    if(state == 1){
                        state = 0;
                        Log.d("inThread", "run: user app == defalt");
                        toNextBtn.setImageResource(R.drawable.join_next_invalid);
                    }
                    continue;
                }
                state = 1;
                if(preState == 0) toNextBtn.setImageResource(R.drawable.join_next_valid);
            }
        }
    }
}