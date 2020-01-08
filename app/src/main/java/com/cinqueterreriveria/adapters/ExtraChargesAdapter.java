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

public class ExtraChargesAdapter extends RecyclerView.Adapter<ExtraChargesAdapter.MyViewHolder> {

    Context context;

    ArrayList<SinglePlaceDetailModel.Extra> ectraChargesList;
    public ExtraChargesAdapter(Context context, ArrayList<SinglePlaceDetailModel.Extra> ectraChargesList) {
        this.context = context;
        this.ectraChargesList = ectraChargesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_extra_charges, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
holder.tv_extra_name.setText(ectraChargesList.get(position).getName());
holder.tv_amoun.setText(ectraChargesList.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return ectraChargesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_extra_name,tv_amoun;
        public MyViewHolder(View view) {
            super(view);
            tv_extra_name=view.findViewById(R.id.tv_extra_name);
            tv_amoun=view.findViewById(R.id.tv_amoun);
        }
    }
}

