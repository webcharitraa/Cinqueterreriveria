package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.PlaceListActivity;
import com.cinqueterreriveria.models.DashboardModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder> {

    Context context;
    List<DashboardModel.Location> location;

    public PlacesAdapter(Context context, List<DashboardModel.Location> location) {
        this.context = context;
        this.location = location;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_places, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(location.get(position).getImage()).apply(simpleOptions).into(holder.imageView1);
        holder.tv_place_name.setText(location.get(position).getTitle());
        if(location.get(position).getPrice().equals(""))
        {
            holder.tv_start_price.setText("€ 0");

        }
        else
        {
            holder.tv_start_price.setText("€"+location.get(position).getPrice());

        }
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

        RoundedImageView imageView1;
        TextView tv_place_name,tv_start_price;

        public MyViewHolder(View view) {
            super(view);
            imageView1 = view.findViewById(R.id.imageView1);
            tv_place_name = view.findViewById(R.id.tv_place_name);
            tv_start_price = view.findViewById(R.id.tv_start_price);
        }
    }
}
