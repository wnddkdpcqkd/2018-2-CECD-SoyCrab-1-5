package com.example.chloechoi.cctvparkingcontrolproject.Join;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chloechoi.cctvparkingcontrolproject.PermissionUtils;
import com.example.chloechoi.cctvparkingcontrolproject.R;

import java.io.File;
import java.io.IOException;

public class JoinStageActivity extends android.support.v4.app.FragmentActivity{
    String[] appList = {"카카오내비", "T map", "네이버 지도"};

    public static final String FILE_NAME = "temp.jpg";
    private static final int GALLERY_PERMISSIONS_REQUEST = 0;
    private static final int GALLERY_IMAGE_REQUEST = 1;

    ImageView userPic;

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

        findViewById(R.id.join_get_from_gallery).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { startGalleryChooser(); }
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri photoUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", getCameraFile());
            uploadImage(photoUri);
        }
    }

    public void startGalleryChooser() {
        if (PermissionUtils.requestPermission(this, GALLERY_PERMISSIONS_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT); // 이미지 가져오기
            startActivityForResult(Intent.createChooser(intent, "Select a photo"),
                    GALLERY_IMAGE_REQUEST);
        }
    }

    public File getCameraFile() {
        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(dir, FILE_NAME);
    }

    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //bitmap = bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

                userPic = (ImageView) findViewById(R.id.join_userPic);
                userPic.setImageBitmap(bitmap); // 이미지뷰에 이미지 삽입
            } catch (IOException e) {
                //Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            //Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
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
