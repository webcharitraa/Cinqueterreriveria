package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.CustomPagerAdapter;
import com.cinqueterreriveria.common.PrefStore;

public class WalkThroughActivity extends AppCompatActivity {

    Context context = this;
    ViewPager view_pager;
    LinearLayout pager_dots;
    CustomPagerAdapter customPagerAdapter;
    private ImageView[] ivArrayDotsPager;
    ImageView iv_next;
    PrefStore prefStore;
    int[] images = {R.drawable.location, R.drawable.bag, R.drawable.walk};
    String[] title={"Pick Your Location","Pack Your Bag","Start the Adventure"};
    String[] description={"Pick your village and find the rental for you","Travel makes one modest. You see what a tiny place you occupy in the world."," Adventure begins where plans end. start your adventure with cinqueterrerivera"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_walk_through);

        initUis();
    }

    private void initUis() {
        prefStore=new PrefStore(context);
        view_pager = findViewById(R.id.view_pager);
        pager_dots = findViewById(R.id.pager_dots);
        iv_next = findViewById(R.id.iv_next);

        customPagerAdapter = new CustomPagerAdapter(context, images,title,description);

        view_pager.setAdapter(customPagerAdapter);

        setupPagerIndidcatorDots();

        ivArrayDotsPager[0].setImageResource(R.drawable.selected_hash);

        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.unselected_hash);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.selected_hash);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        iv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // view_pager.setCurrentItem(getItem(+1), true);
                int tab=view_pager.getCurrentItem();
                if (tab >= 0)
                {
                    tab++;
                    view_pager.setCurrentItem(tab);
                    if (tab == 3)
                    {

                        startActivity(new Intent(context,LoginActivity.class));

                    }
                }

            }
        });
    }

    private int getItem(int i) {
        return view_pager.getCurrentItem() + i;
    }

    private void setupPagerIndidcatorDots() {
        ivArrayDotsPager = new ImageView[images.length];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.unselected_hash);
            //ivArrayDotsPager[i].setAlpha(0.4f);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setAlpha(1);
                }
            });
            pager_dots.addView(ivArrayDotsPager[i]);
            pager_dots.bringToFront();
        }
    }
}