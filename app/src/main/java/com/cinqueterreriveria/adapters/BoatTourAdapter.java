package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.SingleBoatTourActivity;

public class BoatTourAdapter extends RecyclerView.Adapter<BoatTourAdapter.MyViewHolder> {

    Context context;


    public BoatTourAdapter(Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blog_accomodation, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.tv_boat_read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SingleBoatTourActivity.class));
            }
        });
    }



    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        TextView tv_boat_read_more;
        public MyViewHolder(View view) {
            super(view);
            tv_boat_read_more=view.findViewById(R.id.tv_boat_read_more);
        }
    }
}

