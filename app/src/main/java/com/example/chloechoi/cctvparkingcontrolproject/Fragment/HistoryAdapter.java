package com.example.chloechoi.cctvparkingcontrolproject.Fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chloechoi.cctvparkingcontrolproject.R;

import org.w3c.dom.Text;

/**
 * Created by chloechoi on 12/12/2018.
 */

public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvSimpleInfo;

        HistoryViewHolder(View view){
            super(view);
            tvSimpleInfo = view.findViewById(R.id.history_simple_address);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
