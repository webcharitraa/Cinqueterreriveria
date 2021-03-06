package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {

    Context context;
    ArrayList<Integer> personImages;

    public CollectionAdapter(Context context) {
        this.context = context;

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_collection, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.imageView1.setImageResource(personImages.get(position));
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        public MyViewHolder(View view) {
            super(view);
            imageView1=view.findViewById(R.id.imageView1);
        }
    }
}

