package com.example.chloechoi.cctvparkingcontrolproject.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chloechoi.cctvparkingcontrolproject.R;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class UserFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        /*TODO : 사용자 백드라운드 이미지 사용자 기기에 맞게 넑히기
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_detail);
            ImageView iv = (ImageView)findViewById(R.id.imageView);

            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.test02);
            Bitmap resized = Bitmap.createScaledBitmap(image, 450, 200, true);
            iv.setImageBitmap(resized);
            iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 레이아웃 크기에 이미지를 맞춘다
            iv.setPadding(3, 3, 3, 3);
            iv.setOnClickListener(new OnClickListener(){
                public void onClick(View arg0) {
                    finish();
                }
            });
        }
    }
     * */


        return inflater.inflate(R.layout.fragment_user,container, false);
    }
}
