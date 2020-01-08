package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;

public class Place_Filter_categoryAdapter extends RecyclerView.Adapter<Place_Filter_categoryAdapter.MyViewHolder> {
    Context context;
    String[] filter_categories;
    onClickListerner onClickListerner;
    int selected_position =0;
    public interface onClickListerner{
        void onClick(String pricing);
    }

    public Place_Filter_categoryAdapter(Context context, String[] filter_categories,onClickListerner onClickListerner) {
        this.context = context;
        this.filter_categories = filter_categories;
        this.onClickListerner = onClickListerner;
    }

    @Override
    public Place_Filter_categoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom_place_items, parent, false);

        return new Place_Filter_categoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Place_Filter_categoryAdapter.MyViewHolder holder, int position) {
        holder.tv_category_filter.setText(filter_categories[position]);
        //onClickListerner.onClick("Pricing");
       // holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.orange));

        if (selected_position == 0)
        {
            onClickListerner.onClick("Pricing");
        }
        if (selected_position == position)
        {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.orange));
            holder.tv_category_filter.setTextColor(context.getResources().getColor(R.color.white));
        }
        else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.low_light_grey));
            holder.tv_category_filter.setTextColor(context.getResources().getColor(R.color.dark_grey));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_position = holder.getAdapterPosition();
                if (selected_position == 0)
                {
                    onClickListerner.onClick("Pricing");
                }
                 if (selected_position == 1)
                {
                    onClickListerner.onClick("Rating");

                }
                 if (selected_position == 2)
                {
                    onClickListerner.onClick("Reservation");
                }
                 if (selected_position == 3)
                {
                    onClickListerner.onClick("Property Type");
                }

                 notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return filter_categories.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_filter;
        TextView tv_category_filter;

        public MyViewHolder(View view) {
            super(view);
            tv_category_filter = view.findViewById(R.id.tv_category_filter);
            ll_filter = view.findViewById(R.id.ll_filter);
        }
    }
}

