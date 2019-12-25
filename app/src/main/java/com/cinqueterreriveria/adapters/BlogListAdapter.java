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
import com.cinqueterreriveria.activities.SingleBlogDetailActivity;

import java.util.ArrayList;

public class BlogListAdapter extends RecyclerView.Adapter<BlogListAdapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> personImages;

    public BlogListAdapter(Context context) {
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blogs, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.imageView1.setImageResource(personImages.get(position));
        holder.tv_read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SingleBlogDetailActivity.class));
            }
        });
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
            imageView1=view.findViewById(R.id.imageView1);
            tv_read_more=view.findViewById(R.id.tv_read_more);
        }
    }
}

