package com.example.chloechoi.cctvparkingcontrolproject.Fragment;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chloechoi.cctvparkingcontrolproject.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by chloechoi on 12/12/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {
    ArrayList<HistoryItem> mItem;

    public HistoryAdapter(ArrayList<HistoryItem> searchDataSet, Activity activity) {
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
