package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;


public class PlacePricingAdapter extends RecyclerView.Adapter<PlacePricingAdapter.MyViewHolder> {
    Context context;
    String[] pricing;
    onRadioClick onRadioClick;
    int mSelectedItem = -1;
    boolean isChecked = false;

    public interface onRadioClick {
        void onClick(String s);
    }

    public PlacePricingAdapter(Context context, String[] pricing, onRadioClick onRadioClick) {
        this.context = context;
        this.pricing = pricing;
        this.onRadioClick = onRadioClick;

    }

    @Override
    public PlacePricingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter_rating, parent, false);

        return new PlacePricingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PlacePricingAdapter.MyViewHolder holder, final int position) {

        holder.tv_category_filter.setChecked(position == mSelectedItem);
        holder.rating_.setVisibility(View.GONE);
        holder.tv_radio_text.setText(pricing[position]);
       // holder.rating_.setRating(Float.parseFloat(pricing[position]));

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","status"+holder.tv_category_filter.isChecked());
                if (holder.tv_category_filter.isChecked() == false) {
                    holder.tv_category_filter.setChecked(true);
                    onRadioClick.onClick(pricing[position]);
                    mSelectedItem = holder.getAdapterPosition();
                    notifyItemRangeChanged(0, pricing.length);
                } else {
                    //holder.tv_category_filter.setChecked(false);
                    onRadioClick.onClick("");
                    mSelectedItem = -1;
                    notifyItemRangeChanged(0, pricing.length);
                }


            }
        };
        holder.itemView.setOnClickListener(clickListener);
        holder.tv_radio_text.setOnClickListener(clickListener);
        holder.rl_radio.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return pricing.length;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RadioButton tv_category_filter;
        RatingBar rating_;
        RelativeLayout rl_radio;
        TextView tv_radio_text;


        public MyViewHolder(View view) {
            super(view);
            tv_category_filter = view.findViewById(R.id.tv_category_filter);
            rating_ = view.findViewById(R.id.rating_);
            rl_radio = view.findViewById(R.id.rl_radio);
            tv_radio_text = view.findViewById(R.id.tv_radio_text);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}






/*extends RecyclerView.Adapter<PlacePricingAdapter.MyViewHolder> {
    Context context;
    String[] pricing;
    onRadioClick onRadioClick;
    int mSelectedItem = -1;

    public interface onRadioClick {
        void onClick(String s);
    }

    public PlacePricingAdapter(Context context, String[] pricing, onRadioClick onRadioClick) {
        this.context = context;
        this.pricing = pricing;
        this.onRadioClick = onRadioClick;


    }

    @Override
    public PlacePricingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place_subcatfilter, parent, false);

        return new PlacePricingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PlacePricingAdapter.MyViewHolder holder, final int position) {
        holder.tv_category_filter.setChecked(position == mSelectedItem);

        holder.rating_.setVisibility(View.GONE);
        holder.tv_category_filter.setText(pricing[position]);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tv_category_filter.isChecked() == false) {
                    holder.tv_category_filter.setChecked(true);
                    onRadioClick.onClick(pricing[position]);
                    mSelectedItem = holder.getAdapterPosition();
                    notifyItemRangeChanged(0, pricing.length);
                } else {
                    //holder.tv_category_filter.setChecked(false);
                    onRadioClick.onClick("");
                    mSelectedItem = -1;
                    notifyItemRangeChanged(0, pricing.length);
                }
              *//*  onRadioClick.onClick(pricing[position]);
                mSelectedItem = holder.getAdapterPosition();
                notifyItemRangeChanged(0, pricing.length);*//*

            }
        };
        holder.itemView.setOnClickListener(clickListener);
        holder.tv_category_filter.setOnClickListener(clickListener);

    }

    @Override
    public int getItemCount() {

        return pricing.length;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RadioButton tv_category_filter;
        RatingBar rating_;
        RelativeLayout rl_radio;


        public MyViewHolder(View view) {
            super(view);
            tv_category_filter = view.findViewById(R.id.tv_category_filter);
            rating_ = view.findViewById(R.id.rating_);
            rl_radio = view.findViewById(R.id.rl_radio);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

*/