package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.BlogDetailsActivity;
import com.cinqueterreriveria.models.BlogCategoryModel;

import java.util.List;

public class BlogAccommodationAdapter extends RecyclerView.Adapter<BlogAccommodationAdapter.MyViewHolder> {

    Context context;
    List<BlogCategoryModel.BlogDescription> detail;

    public BlogAccommodationAdapter(Context context, List<BlogCategoryModel.BlogDescription> detail) {
        this.context = context;
        this.detail = detail;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_blog_accomodation, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(detail.get(position).getImage()).placeholder(R.drawable.placeholder).apply(simpleOptions).into(holder.iv_accomodation);
        holder.tv_title.setText(detail.get(position).getTitle());
        holder.tv_blog_date.setText(detail.get(position).getDate()
        );
        holder.tv_acco_descripti.setText(Html.fromHtml(detail.get(position).getShortDescription()));

        holder.tv_boat_read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, BlogDetailsActivity.class).
                        putExtra("what_name",detail.get(position).getSlug()));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_accomodation;
        TextView tv_title,tv_acco_descripti,tv_blog_date;
        Button tv_boat_read_more;

        public MyViewHolder(View view) {
            super(view);
            iv_accomodation = view.findViewById(R.id.iv_accomodation);
            tv_title = view.findViewById(R.id.tv_title);
            tv_acco_descripti = view.findViewById(R.id.tv_acco_descripti);
            tv_boat_read_more = view.findViewById(R.id.tv_boat_read_more);
            tv_blog_date = view.findViewById(R.id.tv_blog_date);
        }
    }
}


