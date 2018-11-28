package com.example.chloechoi.cctvparkingcontrolproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class FragmentActivity extends android.support.v4.app.FragmentActivity{
    int curFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Fragment fragment = new MapFragment();
        curFragment = 0;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void ChangeFragment(View v){
        Fragment fragment;
        unsetBtnIcon();
        ImageView imageView;

        switch(v.getId()){
            case R.id.to_map_fragment:{
                fragment = new MapFragment();
                imageView = (ImageView) findViewById(R.id.to_map_icon);
                imageView.setImageResource(R.drawable.fragment_map_checked);
                curFragment = 0;
                break;
            }case R.id.to_write_fragment:{
                fragment = new WriteFragment();
                imageView = (ImageView) findViewById(R.id.to_write_icon);
                imageView.setImageResource(R.drawable.fragment_write_checked);
                curFragment = 1;
                break;
            }case R.id.to_history_fragment:{
                fragment = new HistoryFragment();
                imageView = (ImageView) findViewById(R.id.to_history_icon);
                imageView.setImageResource(R.drawable.fragment_history_checked);
                curFragment = 2;
                break;
            }case R.id.to_user_fragment:{
                fragment = new UserFragment();
                imageView = (ImageView) findViewById(R.id.to_user_icon);
                imageView.setImageResource(R.drawable.fragment_user_checked);
                curFragment = 3;
                break;
            }
            default:{
                fragment = new MapFragment();
                break;
            }
        }
        /*TODO
        이렇게 케이스를 나누지 말고 그냥 인트형 변수, 배열로 하는 간단한 방법은 없을까?
         */

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void unsetBtnIcon(){
        ImageView imageView;
        switch(curFragment){
            case 0:{
                imageView = (ImageView) findViewById(R.id.to_map_icon);
                imageView.setImageResource(R.drawable.fragment_map_unchecked);
                break;
            }
            case 1:{
                imageView = (ImageView) findViewById(R.id.to_write_icon);
                imageView.setImageResource(R.drawable.fragment_write_unchecked);
                break;
            }
            case 2:{
                imageView = (ImageView) findViewById(R.id.to_history_icon);
                imageView.setImageResource(R.drawable.fragment_history_unchecked);
                break;
            }
            case 3:{
                imageView = (ImageView) findViewById(R.id.to_user_icon);
                imageView.setImageResource(R.drawable.fragment_user_unchecked);
                break;
            }
            default:
                break;
        }
    }
}
