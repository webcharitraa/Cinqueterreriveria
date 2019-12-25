package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.HowToReachDetailActivity;
import com.cinqueterreriveria.models.DashboardModel;

import java.util.ArrayList;
import java.util.List;

public class DashBoardReachAdapter extends RecyclerView.Adapter<DashBoardReachAdapter.MyViewHolder> {

    Context context;
    List<DashboardModel.HowReach> howReachList;

    public DashBoardReachAdapter(Context context, List<DashboardModel.HowReach> howReachList) {
        this.context = context;
        this.howReachList = howReachList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dashboard_reach, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(howReachList.get(position).getImage()).apply(simpleOptions).into(holder.iv_how_to_reach);
        holder.tv_how_to_reach.setText(howReachList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, HowToReachDetailActivity.class).
                        putExtra("how_to_reach_name",howReachList.get(position).getSlug()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return howReachList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_how_to_reach;
        TextView tv_how_to_reach;
        public MyViewHolder(View view) {
            super(view);
            iv_how_to_reach=view.findViewById(R.id.iv_how_to_reach);
            tv_how_to_reach=view.findViewById(R.id.tv_how_to_reach);
        }
    }
}



