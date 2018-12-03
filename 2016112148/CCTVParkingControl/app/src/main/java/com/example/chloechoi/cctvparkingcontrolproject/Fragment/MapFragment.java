package com.example.chloechoi.cctvparkingcontrolproject.Fragment;

import android.app.Fragment;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chloechoi.cctvparkingcontrolproject.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class MapFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map,container, false);
    }
}
