package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.LuxuryCollectionsActivity;
import com.cinqueterreriveria.activities.ServiceDetailActivity;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.models.PDFModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder> {

    Context context;
    int lastPosition = -1;
    String[] services = {"Luxury Properties", "Destination Weddings", "Rental Agreement", "Wedding Proposals",
            "Wedding Anniversaries", "Travelling With Us"};

    public ServicesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_side_menu, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context,
                (position > lastPosition) ? R.anim.down_from_top
                        : R.anim.up_from_bottom);
        holder.itemView.startAnimation(animation);

        holder.tv_service_name.setText(services[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.tv_service_name.getText().equals("Luxury Properties"))
                {
                    context.startActivity(new Intent(context, LuxuryCollectionsActivity.class).
                            putExtra("flag","luxury-collection"));
                }
                if (holder.tv_service_name.getText().equals("Wedding Proposals"))
                {
                    context.startActivity(new Intent(context, ServiceDetailActivity.class).
                            putExtra("flag","wedding-proposal-in-the-cinque-terre"));
                }if (holder.tv_service_name.getText().equals("Wedding Anniversaries"))
                {
                    context.startActivity(new Intent(context, ServiceDetailActivity.class).
                            putExtra("flag","wedding/vows-renewal-in-the-cinque-terre"));
                }if (holder.tv_service_name.getText().equals("Rental Agreement"))
                {

                    pdfAPi();
                   /* context.startActivity(new Intent(context, ServiceDetailActivity.class).
                            putExtra("flag","rental-agreement"));*/
                }if (holder.tv_service_name.getText().equals("Travelling With Us"))
                {
                    context.startActivity(new Intent(context, ServiceDetailActivity.class).
                            putExtra("flag","travelling-with-us"));
                }
            }
        });
    }
    private void pdfAPi()
    {
        Call<PDFModel> call= Rest.getRetrofit().pdfDetail(ApiConstents.SECRET_KEY,
                "rental-agreement");

        call.enqueue(new Callback<PDFModel>() {
            @Override
            public void onResponse(Call<PDFModel> call, Response<PDFModel> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess()== true)
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(response.body().getDetail().getPdf()), "application/pdf");
                        context.startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<PDFModel> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return services.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_service_name;

        public MyViewHolder(View view) {
            super(view);
            tv_service_name = view.findViewById(R.id.tv_service_name);
        }
    }
}

