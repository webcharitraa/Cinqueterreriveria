package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.cinqueterreriveria.R;

import java.util.ArrayList;

public class CustomPagerAdapter extends PagerAdapter {

    Context context;
    int[] pager;
    String[] title;
    String[] description;

    public CustomPagerAdapter(Context context, int[] pager, String[] title, String[] description) {
        this.context = context;
        this.pager = pager;
        this.title = title;
        this.description = description;
    }

    @Override
    public int getCount() {
        return pager.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public  Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_walkthrough, container, false);
        ImageView iv_banner = view.findViewById(R.id.iv_banner);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_des = view.findViewById(R.id.tv_des);
        tv_title.setText(title[position]);
        tv_des.setText(description[position]);
        iv_banner.setImageResource(pager[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
