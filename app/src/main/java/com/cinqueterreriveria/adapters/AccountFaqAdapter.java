package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.models.UserFAQModel;

import java.util.List;

public class AccountFaqAdapter extends RecyclerView.Adapter<AccountFaqAdapter.MyViewHolder> {

    Context context;
    int selected_position = 0;
    boolean isVisible= false;
    List<UserFAQModel.Detail> detail;
    View v;

    public AccountFaqAdapter(Context context, List<UserFAQModel.Detail> detail) {
        this.context = context;
        this.detail = detail;
    }

    @Override
    public AccountFaqAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_faqs, parent, false);

        return new AccountFaqAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AccountFaqAdapter.MyViewHolder holder, final int position) {
        holder.tv_faq_item_title.setText(detail.get(position).getTitle());

        if (selected_position == position)
        {
            if (isVisible)
            {
                Log.e("TAG","true");
                holder.rl_bottom_rect.setBackgroundResource(R.drawable.strok_grey_light);
                holder.tv_expand_content.setVisibility(View.GONE);
            }
            else {
                Log.e("TAG","false");

                holder.ll_faq.setBackgroundResource(R.drawable.strok_grey_light);
                holder.rl_bottom_rect.setBackgroundResource(R.drawable.stroke_grey_bottom_rect);
                holder.tv_expand_content.setVisibility(View.VISIBLE);
                holder.iv_expand.setImageResource(R.drawable.ic_chevron_upwards_arrow);

                holder.tv_expand_content.getSettings().setJavaScriptEnabled(true);
                holder.tv_expand_content.loadDataWithBaseURL(null, detail.get(position).getContent(), "text/html", "utf-8", null);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_position = holder.getAdapterPosition();
                if (holder.tv_expand_content.getVisibility() == View.VISIBLE)
                {
                    updateVisibility(true);
                }
                else {
                    updateVisibility(false);
                }
                notifyDataSetChanged();

            }
        });

    }

    public void updateVisibility(boolean newValue){
        isVisible= newValue;
    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_faq_item_title;
        LinearLayout ll_faq;
        WebView tv_expand_content;
        RelativeLayout rl_bottom_rect;
        ImageView iv_expand;

        public MyViewHolder(View view) {
            super(view);
            tv_faq_item_title = view.findViewById(R.id.tv_faq_item_title);
            tv_expand_content = view.findViewById(R.id.tv_expand_content);
            ll_faq = view.findViewById(R.id.ll_faq);
            rl_bottom_rect = view.findViewById(R.id.rl_bottom_rect);
            iv_expand = view.findViewById(R.id.iv_expand);
        }
    }
}



