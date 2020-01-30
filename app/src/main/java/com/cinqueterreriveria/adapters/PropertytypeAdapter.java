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
import com.cinqueterreriveria.models.PlaceListModel;

import java.util.List;

public class PropertytypeAdapter  extends RecyclerView.Adapter<PropertytypeAdapter.MyViewHolder> {
    Context context;
    List<PlaceListModel.PropertyType> propertytype;
    onRadioClick onRadioClick;
    int mSelectedItem =-1;

    public interface onRadioClick {
        void onClick(String slug);
    }

    public PropertytypeAdapter(Context context, List<PlaceListModel.PropertyType> propertytype,onRadioClick onRadioClick) {
        this.context = context;
        this.propertytype = propertytype;
        this.onRadioClick = onRadioClick;

    }

    @Override
    public PropertytypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filter_rating, parent, false);

        return new PropertytypeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PropertytypeAdapter.MyViewHolder holder, final int position) {
        holder.tv_category_filter.setChecked(position == mSelectedItem);

        holder.rating_.setVisibility(View.GONE);
        holder.tv_radio_text.setText(propertytype.get(position).getName());

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.tv_category_filter.isChecked() == false) {
                    holder.tv_category_filter.setChecked(true);
                    onRadioClick.onClick(propertytype.get(position).getSlug());
                    mSelectedItem = holder.getAdapterPosition();
                    notifyItemRangeChanged(0, propertytype.size());
                } else {
                    //holder.tv_category_filter.setChecked(false);
                    onRadioClick.onClick("");
                    mSelectedItem = -1;
                    notifyItemRangeChanged(0, propertytype.size());
                }
                /*onRadioClick.onClick(propertytype.get(position).getSlug());
                mSelectedItem = holder.getAdapterPosition();
                notifyItemRangeChanged(0, propertytype.size());*/

            }
        };
        holder.itemView.setOnClickListener(clickListener);
        holder.rl_radio.setOnClickListener(clickListener);
        holder.tv_radio_text.setOnClickListener(clickListener);
}

    @Override
    public int getItemCount() {

        return propertytype.size();


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RadioButton tv_category_filter;
        RelativeLayout rl_radio;
        RatingBar rating_;
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


