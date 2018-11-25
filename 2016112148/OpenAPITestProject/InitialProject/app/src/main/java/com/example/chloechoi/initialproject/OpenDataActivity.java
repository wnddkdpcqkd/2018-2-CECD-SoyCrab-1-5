package com.example.chloechoi.initialproject;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class OpenDataActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_data);

        mRecyclerView = findViewById(R.id.recyclerViewForData);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Log.d("test", "show comment");

        findViewById(R.id.getData).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v){
                        ArrayList<OpenDataItem> openDataArrayList = new ArrayList<>();
//                        openDataArrayList.add(new OpenDataItem(R.drawable.test, "first info"));
//                        openDataArrayList.add(new OpenDataItem(R.drawable.test, "second info"));
//                        openDataArrayList.add(new OpenDataItem(R.drawable.test, "third info"));

                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        BufferedReader br = null;
                        String json;
                        try{
                            String protocol = "GET";
                            String parkingCtr = "주정차단속 CCTV";
                            String urlstr = "";
                            URL url = new URL(urlstr);
                            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                            urlConnection.setRequestMethod(protocol);
                            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                            json = br.readLine();

                            Log.d("check", json);

                            JSONParser parser = new JSONParser();
                            JSONObject obj = (JSONObject)parser.parse(json);
                            JSONObject channel = (JSONObject)obj.get("TB_GC_VVTV_INFO_ID01");

                            JSONArray row = (JSONArray)channel.get("row");

                            for(int i=0; i<30; i++){
                                JSONObject temp = (JSONObject)row.get(i);
                                String purpose = (String)temp.get("GC_MAPNAME");
                                if(purpose.equals(parkingCtr)){
                                    String address = (String)temp.get("GC_MAPADDRESS");
                                    //String latitude = (String)temp.get("GC_MAPX");
                                    //String longitude = (String)temp.get("GC_MAPY");
                                    // 디비에 넣을 때는 위의 위도 경도 데이터 이용하기
                                    openDataArrayList.add(new OpenDataItem(R.drawable.test, address));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("check", "in catch");
                        }

                        OpenDataAdapter openDataAdapter = new OpenDataAdapter(openDataArrayList);
                        mRecyclerView.setAdapter(openDataAdapter);
                    }
                }
        );
    }
}
