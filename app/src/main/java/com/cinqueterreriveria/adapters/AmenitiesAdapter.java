package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;

import java.util.ArrayList;

public class AmenitiesAdapter extends RecyclerView.Adapter<AmenitiesAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> amentiesList;

    public AmenitiesAdapter(Context context, ArrayList<String> amentiesList) {
        this.context = context;
        this.amentiesList = amentiesList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_amenities, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_amenties.setText(amentiesList.get(position));
    }

    @Override
    public int getItemCount() {
        return amentiesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView tv_amenties;
        public MyViewHolder(View view) {
            super(view);
            tv_amenties=view.findViewById(R.id.tv_amenties);

        }
    }
}

