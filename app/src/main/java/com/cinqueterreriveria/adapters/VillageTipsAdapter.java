package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;

public class VillageTipsAdapter extends RecyclerView.Adapter<VillageTipsAdapter.MyViewHolder> {

    Context context;


    public VillageTipsAdapter(Context context) {
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

    }



    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        TextView tv_read_more;
        public MyViewHolder(View view) {
            super(view);

        }
    }
}

