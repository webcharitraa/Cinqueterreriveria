package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.models.AccountBookingModel;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.MyViewHolder> {

    Context context;
    List<AccountBookingModel.Detail> detail;

    public BookingsAdapter(Context context, List<AccountBookingModel.Detail> detail) {
        this.context = context;
        this.detail = detail;

    }

    @Override
    public BookingsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bookings, parent, false);

        return new BookingsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookingsAdapter.MyViewHolder holder, int position) {
        holder.tv_check_in_month.setText(detail.get(position).getCheckInMonth());
        holder.tv_check_in_date.setText(detail.get(position).getCheckInDate());
        holder.tv_check_out.setText(detail.get(position).getCheckOutMonth());
        holder.tv_check_out_date.setText(detail.get(position).getCheckOutDate());
        holder.tv_destination.setText("Destination: "+detail.get(position).getDestination());
        holder.tv_booking_order.setText("Booking Order: "+detail.get(position).getBookingOrder());
        holder.tv_status.setText("Status: "+detail.get(position).getStatus());
        holder.tv_payment.setText("Payment: "+detail.get(position).getPayment());

    }


    @Override
    public int getItemCount() {
        return detail.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        TextView tv_check_in_month,tv_check_in_date,tv_check_out,
                tv_check_out_date,tv_destination,tv_booking_order,
                tv_status,tv_payment;

        public MyViewHolder(View view) {
            super(view);
            tv_check_in_month = view.findViewById(R.id.tv_check_in_month);
            tv_check_in_date = view.findViewById(R.id.tv_check_in_date);
            tv_check_out = view.findViewById(R.id.tv_check_out);
            tv_check_out_date = view.findViewById(R.id.tv_check_out_date);
            tv_destination = view.findViewById(R.id.tv_destination);
            tv_booking_order = view.findViewById(R.id.tv_booking_order);
            tv_status = view.findViewById(R.id.tv_status);
            tv_payment = view.findViewById(R.id.tv_payment);
        }
    }
}


