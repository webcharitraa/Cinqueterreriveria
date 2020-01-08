package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.models.SinglePlaceDetailModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class NearByAdapter extends RecyclerView.Adapter<NearByAdapter.MyViewHolder> {

    Context context;
    ArrayList<SinglePlaceDetailModel.Nearby> nearByList;

    public NearByAdapter(Context context, ArrayList<SinglePlaceDetailModel.Nearby> nearByList) {
        this.context = context;
        this.nearByList = nearByList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_neaby, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(nearByList.get(position).getImage()).apply(simpleOptions).into(holder.riv_nearby);
        holder.tv_near_by.setText(nearByList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(nearByList.get(position).getSlug())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return nearByList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView riv_nearby;
        TextView tv_near_by;

        public MyViewHolder(View view) {
            super(view);
            riv_nearby = view.findViewById(R.id.riv_nearby);
            tv_near_by = view.findViewById(R.id.tv_near_by);
        }
    }
}

