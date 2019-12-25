package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.PlaceListActivity;
import com.cinqueterreriveria.models.DashboardModel;

import java.util.List;

public class AccomodationAdapter extends RecyclerView.Adapter<AccomodationAdapter.MyViewHolder> {

    Context context;
    int lastPosition = -1;
    List<DashboardModel.Location> location;

    public AccomodationAdapter(Context context, List<DashboardModel.Location> location) {
        this.context = context;
        this.location = location;
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
        holder.tv_service_name.setText(location.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PlaceListActivity.class).
                        putExtra("place_name",location.get(position).getSlug()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return location.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_service_name;

        public MyViewHolder(View view) {
            super(view);
            tv_service_name = view.findViewById(R.id.tv_service_name);
        }
    }
}

