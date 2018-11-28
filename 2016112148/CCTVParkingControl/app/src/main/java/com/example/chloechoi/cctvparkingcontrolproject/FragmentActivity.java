package com.example.chloechoi.cctvparkingcontrolproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class FragmentActivity extends AppCompatActivity{


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_fragment);

        Fragment fragment = new MapFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void ChangeFragment(View v){
        Fragment fragment;

        switch(v.getId()){
            case R.id.to_map_fragment:{
                fragment = new MapFragment();
                break;
            }case R.id.to_write_fragment:{
                fragment = new WriteFragment();
                break;
            }case R.id.to_history_fragment:{
                fragment = new HistoryFragment();
                break;
            }case R.id.to_user_fragment:{
                fragment = new UserFragment();
                break;
            }
            default:{
                fragment = new MapFragment();
                break;
            }
        }

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
