package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.BlogDetailsActivity;
import com.cinqueterreriveria.models.DashboardModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder> {

    Context context;
    List<DashboardModel.Blog> blogsListl;

    public BlogAdapter(Context context, List<DashboardModel.Blog> blogsList) {
        this.context = context;
        this.blogsListl = blogsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dashboard_blog, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RequestOptions simpleOptions = new RequestOptions()
                    .centerCrop()

                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(context).load(blogsListl.get(position).getImage()).placeholder(R.drawable.placeholder).apply(simpleOptions).into(holder.iv_dashboard_blog);


        holder.tv_dashboard_blog_title.setText(blogsListl.get(position).getTitle());
        holder.tv_dashboard_blog_description.setText(blogsListl.get(position).getSlug());

        holder.bt_blog_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, BlogDetailsActivity.class).
                        putExtra("what_name",blogsListl.get(position).getSlug()));
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
        return blogsListl.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView iv_dashboard_blog;
        TextView tv_dashboard_blog_title,tv_dashboard_blog_description;
        Button bt_blog_detail;

        public MyViewHolder(View view) {
            super(view);
            iv_dashboard_blog = view.findViewById(R.id.iv_dashboard_blog);
            tv_dashboard_blog_title = view.findViewById(R.id.tv_dashboard_blog_title);
            tv_dashboard_blog_description = view.findViewById(R.id.tv_dashboard_blog_description);
            bt_blog_detail = view.findViewById(R.id.bt_blog_detail);
        }
    }
}

