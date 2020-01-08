package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.BlogDetailPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SingleBlogDetailActivity extends AppCompatActivity {

    ViewPager view_pager_blog_detail;
    TabLayout blog_tab;
    LinearLayout ll_back;
    ImageView iv_back;
    TextView tv_back,tv_app_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_blog_detail);

        initUis();
    }

    void initUis() {
        view_pager_blog_detail = findViewById(R.id.view_pager_blog_detail);
        blog_tab = findViewById(R.id.blog_tab);
        ll_back = findViewById(R.id.ll_back);
        tv_back = findViewById(R.id.tv_back);
        iv_back = findViewById(R.id.iv_back);
        tv_app_bar_title = findViewById(R.id.tv_app_bar_title);

        iv_back.setColorFilter(getResources().getColor(R.color.white));
        tv_back.setText("Back");
        tv_back.setTextColor(getResources().getColor(R.color.white));
        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("Cinque Terre News");
        tv_app_bar_title.setTextColor(getResources().getColor(R.color.white));

        view_pager_blog_detail.setAdapter(new BlogDetailPagerAdapter(getSupportFragmentManager()));
        blog_tab.setupWithViewPager(view_pager_blog_detail);

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
