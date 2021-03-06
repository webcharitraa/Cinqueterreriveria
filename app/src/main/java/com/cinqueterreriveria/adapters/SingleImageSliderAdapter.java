package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;

import java.util.List;

public class SingleImageSliderAdapter extends PagerAdapter {
    private Context mContext;
    List<String> gallery;
    String status;
    int[] images = {R.drawable.dummy, R.drawable.dummy, R.drawable.dummy};

    public SingleImageSliderAdapter(Context context, List<String> gallery) {
        this.mContext = context;
        this.gallery = gallery;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_image_slider, collection, false);
        ImageView iv_image = layout.findViewById(R.id.iv_image);
        TextView tv_status = layout.findViewById(R.id.tv_status);
        //  iv_image.setImageResource(gallery.get(position));


       /* iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, SinglePlaceDetailActivity.class));

            }
        });*/
        collection.addView(layout);

        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(gallery.get(position)).apply(simpleOptions).into(iv_image);

        tv_status.setText(status);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}