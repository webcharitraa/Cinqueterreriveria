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

public class PropertyRatesAdapter extends RecyclerView.Adapter<PropertyRatesAdapter.MyViewHolder> {

    Context context;
    ArrayList<SinglePlaceDetailModel.PropertyRate> reviews;

    public PropertyRatesAdapter(Context context, ArrayList<SinglePlaceDetailModel.PropertyRate> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @Override
    public PropertyRatesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_property_rates, parent, false);

        return new PropertyRatesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PropertyRatesAdapter.MyViewHolder holder, final int position) {

        holder.tv_property_rates.setText(reviews.get(position).getPropertyRates());
        holder.tv_daily.setText(reviews.get(position).getDaily());
        holder.tv_min_stay.setText(reviews.get(position).getMinStay());


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civ_review;
        TextView tv_property_rates,tv_daily,tv_min_stay;

        public MyViewHolder(View view) {
            super(view);
            civ_review = view.findViewById(R.id.civ_review);
            tv_property_rates = view.findViewById(R.id.tv_property_rates);
            tv_daily = view.findViewById(R.id.tv_daily);
            tv_min_stay = view.findViewById(R.id.tv_min_stay);
        }
    }
}

