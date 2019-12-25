package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.cinqueterreriveria.models.DashboardModel;

import java.util.List;

public class BookingUniqueExperienceAdapter extends RecyclerView.Adapter<BookingUniqueExperienceAdapter.MyViewHolder> {

    Context context;
    List<DashboardModel.Activity> personImages;

    public BookingUniqueExperienceAdapter(Context context, List<DashboardModel.Activity> personImages) {
        this.context = context;
        this.personImages = personImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book_unique, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

         RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(personImages.get(position).getImage()).apply(simpleOptions).into(holder.iv_dashboard_unique);
        holder.tv_dashboard_unique.setText(personImages.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(personImages.get(position).getUrl()));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personImages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_dashboard_unique;
        TextView tv_dashboard_unique;
        public MyViewHolder(View view) {
            super(view);
            iv_dashboard_unique=view.findViewById(R.id.iv_dashboard_unique);
            tv_dashboard_unique=view.findViewById(R.id.tv_dashboard_unique);
        }
    }
}
