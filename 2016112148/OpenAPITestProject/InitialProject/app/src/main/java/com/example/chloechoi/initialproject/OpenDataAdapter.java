package com.example.chloechoi.initialproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chloechoi on 2018. 10. 3..
 */

public class OpenDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class OpenDataViewHolder extends RecyclerView.ViewHolder{
        ImageView dataPic;
        TextView dataInfo;

        OpenDataViewHolder(View view){
            super(view);
            dataPic = view.findViewById(R.id.dataPic);
            dataInfo = view.findViewById(R.id.dataInfo);
        }
    }

    ArrayList<OpenDataItem> mItems;

    public OpenDataAdapter(ArrayList<OpenDataItem> items){
        this.mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_for_open_data, parent, false);
        return new OpenDataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        OpenDataViewHolder openDataViewHolder = (OpenDataViewHolder) holder;

         openDataViewHolder.dataPic.setImageResource(mItems.get(position).img);
         openDataViewHolder.dataInfo.setText(mItems.get(position).info);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
