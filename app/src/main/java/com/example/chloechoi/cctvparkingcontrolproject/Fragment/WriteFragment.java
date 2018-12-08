package com.example.chloechoi.cctvparkingcontrolproject.Fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chloechoi.cctvparkingcontrolproject.PermissionUtils;
import com.example.chloechoi.cctvparkingcontrolproject.R;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by chloechoi on 28/11/2018.
 */

public class WriteFragment extends Fragment{

    int state;

    TextView parking_state;
    TextView parking_info;
    TextView parking_time;
    EditText parkingExtraInfo;
    ImageView parking_info_pic;
    ImageView parking_get_pic;

    public static final String FILE_NAME = "temp.jpg";
    public static final int CAMERA_PERMISSIONS_REQUEST = 2;
    public static final int CAMERA_IMAGE_REQUEST = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // 서버에서 사용자의 state를 받아옴
        // state에 따라 주차기록하기 뷰 || 주차중 뷰 출력
        final View rootView = inflater.inflate(R.layout.fragment_write,container, false);
        state = 0;

        if(state == 0){ // 주차기록하기뷰
            // 서버에서 사용자 위치, 시간 받아와서
            // 맵에 표시 및 구 뒤의 간단한 주소명 출력
            String addressInfo = "testInfo";
            String parkedTime = "50분 전";

            parking_info = (TextView) rootView.findViewById(R.id.parking_info);
            parking_time = (TextView) rootView.findViewById(R.id.parking_time);

            parking_info.setText(addressInfo);
            parking_time.setText(parkedTime);

            rootView.findViewById(R.id.write_register).setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 서버 API에 맞춰 서버에 전달
                            parkingExtraInfo = (EditText) v.findViewById(R.id.parking_extra_info);
                            String strExtraInfo = parkingExtraInfo.getText().toString();
                        }
                    }
            );

            rootView.findViewById(R.id.write_get_from_camera).setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            Log.d("0233", "startCamera: touched");

                            if (PermissionUtils.requestPermission(
                                    getActivity(),
                                    CAMERA_PERMISSIONS_REQUEST,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.CAMERA)) {
                                parking_info_pic = (ImageView) rootView.findViewById(R.id.write_info_pic);
                                parking_get_pic = (ImageView) rootView.findViewById(R.id.write_get_from_camera);

                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                Uri photoUri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", getCameraFile());
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                startActivityForResult(intent, CAMERA_IMAGE_REQUEST);
                            }
                        }
                    }
            );
        }else{
            // 주차기록 정보 서버에서 받아와서 뿌려주기
            parking_state = (TextView) rootView.findViewById(R.id.parking_state);
            parking_state.setText("주차");

            String addressInfo = "testInfo";
            String strExtraInfo = "testInfo";
            String parkedTime = "50분 전";

            parking_info = (TextView) rootView.findViewById(R.id.parking_info);
            parking_time = (TextView) rootView.findViewById(R.id.parking_time);
            parkingExtraInfo = (EditText) rootView.findViewById(R.id.parking_extra_info);

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

        return rootView;
    }

    public File getCameraFile() {
        File dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri photoUri = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", getCameraFile());
            uploadImage(photoUri);
        }
    }

    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                bitmap = bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                parking_info_pic.setImageBitmap(bitmap); // 이미지뷰에 이미지 삽입
                parking_get_pic.setImageResource(android.R.color.transparent);
            } catch (IOException e) {
                //Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            //Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }
}
