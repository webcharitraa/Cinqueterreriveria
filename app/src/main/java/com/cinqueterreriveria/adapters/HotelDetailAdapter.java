package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.models.SinglePlaceDetailModel;

import java.util.ArrayList;
import java.util.List;

public class HotelDetailAdapter extends RecyclerView.Adapter<HotelDetailAdapter.MyViewHolder> {

    Context context;
    List<SinglePlaceDetailModel.Detail_> details;

    public HotelDetailAdapter(Context context, List<SinglePlaceDetailModel.Detail_> details) {
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
        holder.tv_detail_name.setText(details.get(position).getName()+": ");
        holder.tv_detail_value.setText(details.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
TextView tv_detail_name,tv_detail_value;
        ImageView imageView1;

        public MyViewHolder(View view) {
            super(view);
            imageView1=view.findViewById(R.id.imageView1);
            tv_detail_name=view.findViewById(R.id.tv_detail_name);
            tv_detail_value=view.findViewById(R.id.tv_detail_value);
        }
    }
}

