package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.models.DashboardModel;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder> {

    Context context;
    int lastPosition = -1;
    List<DashboardModel.Activity> activity;

    public ExperienceAdapter(Context context, List<DashboardModel.Activity> activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_side_menu, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.down_from_top
                        : R.anim.up_from_bottom);
        holder.itemView.startAnimation(animation);
        holder.tv_service_name.setText(activity.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(activity.get(position).getUrl()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activity.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_service_name;

        public MyViewHolder(View view) {
            super(view);
            tv_service_name = view.findViewById(R.id.tv_service_name);
        }
    }
}


