package com.example.chloechoi.cctvparkingcontrolproject.list;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chloechoi.cctvparkingcontrolproject.R;
import com.example.chloechoi.cctvparkingcontrolproject.item.History;

import java.util.ArrayList;

/**
 * Created by chloechoi on 12/12/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {
    ArrayList<History> mItem;

    public HistoryAdapter(ArrayList<History> searchDataSet, Activity activity) {
        mItem = searchDataSet;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_info_row,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    /*TODO onBindViewHolder parameterㄱㅏ 안바뀜;;;;*/
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.tvSimpleInfo.setText(mItem.get(position).simpleInfo);
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }
}
