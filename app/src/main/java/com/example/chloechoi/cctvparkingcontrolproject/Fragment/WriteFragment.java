package com.example.chloechoi.cctvparkingcontrolproject.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chloechoi.cctvparkingcontrolproject.R;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class WriteFragment extends Fragment{

    int state;

    TextView parking_state;
    TextView parking_info;
    TextView parking_time;
    EditText parkingExtraInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 서버에서 사용자의 state를 받아옴
        // state에 따라 주차기록하기 뷰 || 주차중 뷰 출력
        state = 0;

        if(state == 0){ // 주차기록하기뷰
            // 서버에서 사용자 위치, 시간 받아와서
            // 맵에 표시 및 구 뒤의 간단한 주소명 출력
            String addressInfo = "testInfo";
            String parkedTime = "50분 전";

            parking_info = (TextView) getView().findViewById(R.id.parking_info);
            parking_time = (TextView) getView().findViewById(R.id.parking_time);

            parking_info.setText(addressInfo);
            parking_time.setText(parkedTime);
        }else{
            // 주차기록 정보 서버에서 받아와서 뿌려주기
            parking_state = (TextView) getView().findViewById(R.id.parking_state);
            parking_state.setText("주차");

            String addressInfo = "testInfo";
            String strExtraInfo = "testInfo";
            String parkedTime = "50분 전";

            parking_info = (TextView) getView().findViewById(R.id.parking_info);
            parking_time = (TextView) getView().findViewById(R.id.parking_time);
            parkingExtraInfo = (EditText) getView().findViewById(R.id.parking_extra_info);

            parkingExtraInfo.setFocusable(false);
            parkingExtraInfo.setFocusableInTouchMode(false);
            parkingExtraInfo.setClickable(false);

            parking_info.setText(addressInfo);
            parking_time.setText(parkedTime);
            parkingExtraInfo.setText(strExtraInfo);

            /*TODO
            1. 주차모드 해제는???
            2. 카메라 연동
             */
        }

        return inflater.inflate(R.layout.fragment_write,container, false);
    }

   public void registerHistory(View v){
       parkingExtraInfo = (EditText) getView().findViewById(R.id.parking_extra_info);
        String strExtraInfo = parkingExtraInfo.getText().toString();

        // 서버 API에 맞춰 서버에 요
    }
}
