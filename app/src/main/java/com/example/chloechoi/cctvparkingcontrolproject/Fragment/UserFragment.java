package com.example.chloechoi.cctvparkingcontrolproject.Fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.chloechoi.cctvparkingcontrolproject.R;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class UserFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user,container, false);

        ImageView bckPic = (ImageView) rootView.findViewById(R.id.user_background);

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.user_backgroundpic);

        Bitmap resized = Bitmap.createScaledBitmap(image, 450, 200, true);
        bckPic.setImageBitmap(resized);
        bckPic.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 레이아웃 크기에 이미지를 맞춘다
        bckPic.setPadding(3, 3, 3, 3);
//            iv.setOnClickListener(new OnClickListener(){
//                public void onClick(View arg0) {
//                    finish();
//                }

        return rootView;
    }

    public void user_change_app(View v){
        /*TODO
        * 1. change user_app_edit img
        * 2. show navi apps's icon
        * 3. set img onclick listener*/
    }
}
