package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.models.SinglePlaceDetailModel;

import java.util.ArrayList;

public class HotelDetailAdapter extends RecyclerView.Adapter<HotelDetailAdapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> personImages;
    SinglePlaceDetailModel.Details details;

    public HotelDetailAdapter(Context context) {
        this.context = context;
        this.details = details;

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_single_place_detail, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.imageView1.setImageResource(personImages.get(position));
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        public MyViewHolder(View view) {
            super(view);
            imageView1=view.findViewById(R.id.imageView1);
        }
    }
}

