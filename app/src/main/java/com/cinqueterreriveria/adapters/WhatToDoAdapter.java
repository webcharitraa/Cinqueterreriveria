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
import com.cinqueterreriveria.activities.WhatToDoDetailActivity;
import com.cinqueterreriveria.models.DashboardModel;

import java.util.List;

public class WhatToDoAdapter extends RecyclerView.Adapter<WhatToDoAdapter.MyViewHolder> {

    Context context;
    List<DashboardModel.WhatToDo> whatToDo;

    public WhatToDoAdapter(Context context, List<DashboardModel.WhatToDo> whatToDo) {
        this.context = context;
        this.whatToDo = whatToDo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_what_to_do, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(whatToDo.get(position).getImage()).apply(simpleOptions).into(holder.iv_what_to_do);
        holder.tv_what_to_do.setText(whatToDo.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, WhatToDoDetailActivity.class).
                        putExtra("what_name",whatToDo.get(position).getSlug()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return whatToDo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_what_to_do;
        TextView tv_what_to_do;
        public MyViewHolder(View view) {
            super(view);
            iv_what_to_do=view.findViewById(R.id.iv_what_to_do);
            tv_what_to_do=view.findViewById(R.id.tv_what_to_do);
        }
    }
}
