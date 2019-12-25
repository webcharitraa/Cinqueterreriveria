package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.models.LuxuryCollectionModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class LuxuryCollectionAdapter extends RecyclerView.Adapter<LuxuryCollectionAdapter.MyViewHolder> {

    Context context;
    List<LuxuryCollectionModel.Detail> detail;

    public LuxuryCollectionAdapter(Context context, List<LuxuryCollectionModel.Detail> detail) {
        this.context = context;
        this.detail = detail;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_luxury_collection, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(detail.get(position).getImage()).apply(simpleOptions).into(holder.iv_luxury_collection);

        holder.tv_luxury_name.setText(detail.get(position).getTitle());
        holder.tv_location.setText(detail.get(position).getLocation());
        holder.tv_descrip.setText(detail.get(position).getShortDesc());
        holder.bt_book.setText(detail.get(position).getPrice()+" Book Now");
    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView iv_luxury_collection;
        TextView tv_luxury_name,tv_location,tv_descrip;

        Button bt_book;
        public MyViewHolder(View view) {
            super(view);
            iv_luxury_collection = view.findViewById(R.id.iv_luxury_collection);
            tv_luxury_name = view.findViewById(R.id.tv_luxury_name);
            tv_location = view.findViewById(R.id.tv_location);
            tv_descrip = view.findViewById(R.id.tv_descrip);
            bt_book = view.findViewById(R.id.bt_book);
        }
    }
}


