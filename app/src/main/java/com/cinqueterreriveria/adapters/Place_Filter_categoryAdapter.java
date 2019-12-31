package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;

public class Place_Filter_categoryAdapter extends RecyclerView.Adapter<Place_Filter_categoryAdapter.MyViewHolder> {
    Context context;

    public Place_Filter_categoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Place_Filter_categoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom_place_items, parent, false);

        return new Place_Filter_categoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Place_Filter_categoryAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);

        }
    }
}

