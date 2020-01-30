package com.cinqueterreriveria.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.cinqueterreriveria.R;
import com.cinqueterreriveria.models.SinglePlaceDetailModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    Context context;
    ArrayList<SinglePlaceDetailModel.Comment> reviews;

    public ReviewsAdapter(Context context, ArrayList<SinglePlaceDetailModel.Comment> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public ReviewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reviews, parent, false);

        return new ReviewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewsAdapter.MyViewHolder holder, final int position) {

        holder.review_user_name.setText(reviews.get(position).getName());
        holder.tv_comment.setText(reviews.get(position).getComment());


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civ_review;
        TextView review_user_name,tv_comment;

        public MyViewHolder(View view) {
            super(view);
            civ_review = view.findViewById(R.id.civ_review);
            review_user_name = view.findViewById(R.id.review_user_name);
            tv_comment = view.findViewById(R.id.tv_comment);
        }
    }
}
