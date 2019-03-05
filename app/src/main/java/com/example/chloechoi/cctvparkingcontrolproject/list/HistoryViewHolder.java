package com.example.chloechoi.cctvparkingcontrolproject.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chloechoi.cctvparkingcontrolproject.R;

/**
 * Created by chloechoi on 15/12/2018.
 */

public class HistoryViewHolder extends RecyclerView.ViewHolder{
    TextView tvSimpleInfo;

    public HistoryViewHolder(View itemView) {
        super(itemView);
        tvSimpleInfo = (TextView) itemView.findViewById(R.id.history_simple_address);
    }
}
