package com.cinqueterreriveria.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;

import java.util.List;

public class SinglePlaceImageViewPagerAdapter extends PagerAdapter {

    Context context;
    List<String> images;
    AlertDialog dialog1 = null;

    public SinglePlaceImageViewPagerAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_single_place_detail_slider_images, container, false);
        ImageView iv_single_page_images = view.findViewById(R.id.iv_single_page_images);
        ImageView iv_single_back = view.findViewById(R.id.iv_single_back);
        LinearLayout ll_slide_down = view.findViewById(R.id.ll_slide_down);

        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()

                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Log.e("TAG",images.get(position));
        Glide.with(context).load(images.get(position)).apply(simpleOptions).into(iv_single_page_images);
        //iv_single_page_images.setImageResource(images[position]);

        iv_single_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
            }
        });
        iv_single_page_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                View view2 = LayoutInflater.from(context).inflate(R.layout.popup_image, null);
                ImageView iv_cancel_image = view2.findViewById(R.id.iv_cancel_image);
                final ViewPager iv_single_place_image = view2.findViewById(R.id.iv_single_place_image);
                RelativeLayout rl_left_nav = view2.findViewById(R.id.rl_left_nav);
                RelativeLayout rl_right_nav = view2.findViewById(R.id.rl_right_nav);
                builder2.setView(view2);

                // Images left navigation
                rl_left_nav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tab = iv_single_place_image.getCurrentItem();
                        if (tab > 0) {
                            tab--;
                            iv_single_place_image.setCurrentItem(tab);
                        } else if (tab == 0) {
                            iv_single_place_image.setCurrentItem(tab);
                        }
                    }
                });

                // Images right navigatin
                rl_right_nav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tab = iv_single_place_image.getCurrentItem();
                        tab++;
                        iv_single_place_image.setCurrentItem(tab);
                    }
                });
                iv_single_place_image.setAdapter(new SingleImageSliderAdapter(context, images));

                iv_cancel_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });

                dialog1 = builder2.create();
                dialog1.setCancelable(true);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog1.show();
            }
        });
        container.addView(view);



        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        ll_slide_down.startAnimation(anim);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
