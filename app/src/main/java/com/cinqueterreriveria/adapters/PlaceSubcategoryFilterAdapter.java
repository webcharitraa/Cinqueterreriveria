package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;

public class PlaceSubcategoryFilterAdapter extends RecyclerView.Adapter<PlaceSubcategoryFilterAdapter.MyViewHolder> {
    Context context;
    String[] filter_categories;
    onRadioClick onRadioClick;
    int mSelectedItem=-1;

    public interface onRadioClick {
        void onClick(String filter_category);
    }

    public PlaceSubcategoryFilterAdapter(Context context, String[] filter_categories,onRadioClick onRadioClick) {
        this.context = context;
        this.filter_categories = filter_categories;
        this.onRadioClick = onRadioClick;
    }

    @Override
    public PlaceSubcategoryFilterAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter_rating, parent, false);

        return new PlaceSubcategoryFilterAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PlaceSubcategoryFilterAdapter.MyViewHolder holder, final int position) {
        holder.tv_category_filter.setChecked(position == mSelectedItem);

        holder.rating_.setVisibility(View.GONE);
        holder.tv_radio_text.setText(filter_categories[position]);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tv_category_filter.isChecked() == false) {
                    holder.tv_category_filter.setChecked(true);
                    onRadioClick.onClick(filter_categories[position]);
                    mSelectedItem = holder.getAdapterPosition();
                    notifyItemRangeChanged(0, filter_categories.length);
                } else {
                    //holder.tv_category_filter.setChecked(false);
                    onRadioClick.onClick("");
                    mSelectedItem = -1;
                    notifyItemRangeChanged(0, filter_categories.length);
                }
               /* onRadioClick.onClick(filter_categories[position]);
                mSelectedItem = holder.getAdapterPosition();
                notifyItemRangeChanged(0, filter_categories.length);*/

            }
        };
        holder.rl_radio.setOnClickListener(clickListener);
        holder.itemView.setOnClickListener(clickListener);
        holder.tv_radio_text.setOnClickListener(clickListener);


    }

    @Override
    public int getItemCount() {
        return filter_categories.length;
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


