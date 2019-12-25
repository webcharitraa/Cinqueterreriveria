package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.BlogDetailPagerAdapter;
import com.cinqueterreriveria.adapters.BlogListAdapter;
import com.google.android.material.tabs.TabLayout;

public class BlogsActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = this;
    RecyclerView rv_all_blogs;
    TextView tv_app_bar_title, tv_back;
    LinearLayout ll_back;
    ViewPager view_pager_blog_detail;
    TabLayout blog_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);

        initUis();
    }

    private void initUis() {
        view_pager_blog_detail = findViewById(R.id.view_pager_blog_detail);
        blog_tab = findViewById(R.id.blog_tab);
        rv_all_blogs = findViewById(R.id.rv_all_blogs);
        tv_app_bar_title = findViewById(R.id.tv_app_bar_title);
        tv_back = findViewById(R.id.tv_back);
        ll_back = findViewById(R.id.ll_back);

        rv_all_blogs.setLayoutManager(new LinearLayoutManager(context));
        rv_all_blogs.setAdapter(new BlogListAdapter(context));
        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("Blog");
        tv_back.setText("Back");

        ll_back.setOnClickListener(this);
        view_pager_blog_detail.setAdapter(new BlogDetailPagerAdapter(getSupportFragmentManager()));
        blog_tab.setupWithViewPager(view_pager_blog_detail);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
