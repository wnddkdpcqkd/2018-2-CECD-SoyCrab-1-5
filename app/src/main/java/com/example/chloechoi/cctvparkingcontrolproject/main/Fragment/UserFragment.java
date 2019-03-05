package com.example.chloechoi.cctvparkingcontrolproject.main.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.chloechoi.cctvparkingcontrolproject.R;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class UserFragment extends Fragment{
    int editState = 0;
    int userApp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user,container, false);

        ImageView bckPic = (ImageView) rootView.findViewById(R.id.user_background);

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.user_backgroundpic);

        Point deviceSize = getScreenSize(getActivity());

        Bitmap resized = Bitmap.createScaledBitmap(image, deviceSize.x, deviceSize.y/2, true);
        bckPic.setImageBitmap(resized);
        bckPic.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 레이아웃 크기에 이미지를 맞춘다
        bckPic.setPadding(3, 3, 3, 3);

        // get User Info
        userApp = 0; // 테스트를 위해서 일단은 이걸로 초기화

        final ImageView[] appIconViews = {
                (ImageView) rootView.findViewById(R.id.user_kakaonavi),
                (ImageView) rootView.findViewById(R.id.user_tmap),
                (ImageView) rootView.findViewById(R.id.user_googlemap)};
        final int[] setAppIcons = {
                R.drawable.user_kakaonavi,
                R.drawable.user_tmap,
                R.drawable.user_googlemap
        };
        final int[] unsetAppIcons = {
                R.drawable.user_kakaonavi_unchecked,
                R.drawable.user_tmap_unchecked,
                R.drawable.user_googlemap_unchecked
        };

        final ImageView editCompleted = (ImageView) rootView.findViewById(R.id.user_edit_app);

        rootView.findViewById(R.id.user_edit_app).setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(editState == 0){
                            editCompleted.setImageResource(R.drawable.user_edit_completed);

                            for(int i=0; i<3; i++){
                                if(i == userApp) appIconViews[i].setImageResource(setAppIcons[i]);
                                else appIconViews[i].setImageResource(unsetAppIcons[i]);
                            }

                            editState = 1;
                        }
                        else{
                            // 서버에 선택된 내비 보내고
                            editCompleted.setImageResource(R.drawable.user_change_app);

                            for(int i=0; i<3; i++)
                                appIconViews[i].setImageResource(android.R.color.transparent);

                            editState = 0;
                        }
                    }
                }
        );

        appIconViews[0].setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(editState == 1 && userApp != 0){
                            appIconViews[userApp].setImageResource(unsetAppIcons[userApp]);
                            userApp = 0;
                            appIconViews[userApp].setImageResource(setAppIcons[userApp]);
                        }
                    }
                }
        );

        appIconViews[1].setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(editState == 1 && userApp != 1){
                            appIconViews[userApp].setImageResource(unsetAppIcons[userApp]);
                            userApp = 1;
                            appIconViews[userApp].setImageResource(setAppIcons[userApp]);
                        }
                    }
                }
        );

        appIconViews[2].setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(editState == 1 && userApp != 2){
                            appIconViews[userApp].setImageResource(unsetAppIcons[userApp]);
                            userApp = 2;
                            appIconViews[userApp].setImageResource(setAppIcons[userApp]);
                        }
                    }
                }
        );

        return rootView;
    }

    public Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return  size;
    }
}
