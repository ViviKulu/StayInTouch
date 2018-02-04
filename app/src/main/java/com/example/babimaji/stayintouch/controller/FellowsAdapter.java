package com.example.babimaji.stayintouch.controller;

import android.arch.persistence.room.Database;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babimaji.stayintouch.FavoriteListener;
import com.example.babimaji.stayintouch.R;
import com.example.babimaji.stayintouch.backend.AppDatabase;
import com.example.babimaji.stayintouch.model.Fellow;

import java.util.ArrayList;
import java.util.List;

import com.example.babimaji.stayintouch.view.FellowsViewHolder;

public class FellowsAdapter extends RecyclerView.Adapter<FellowsViewHolder>{

    private List<Fellow> fellowList = new ArrayList<>();
    private FavoriteListener favoriteListener;

    public FellowsAdapter(List<Fellow> favoriteFellows) {
        this.fellowList = favoriteFellows;
    }

    public FellowsAdapter(FavoriteListener favoriteListener) {
        this.favoriteListener = favoriteListener;
    }

    @Override
    public FellowsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_layout, parent, false);
        return new FellowsViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(FellowsViewHolder holder, int position) {
        Fellow fellow = fellowList.get(position);
        holder.onBind(fellow, favoriteListener);
    }

    @Override
    public int getItemCount() {
        return fellowList.size();
    }

    public void setData(List<Fellow> fellowList) {
        this.fellowList = fellowList;

    }
}
