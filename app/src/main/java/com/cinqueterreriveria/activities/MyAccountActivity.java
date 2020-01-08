package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.AccountViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MyAccountActivity extends AppCompatActivity {

    TextView tv_app_bar_title;
    TabLayout account_tab_layout;
    ViewPager account_viewpager;
    LinearLayout ll_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        initUis();
    }
    private void initUis()
    {
        account_tab_layout=findViewById(R.id.account_tab_layout);
        account_viewpager=findViewById(R.id.account_viewpager);
        tv_app_bar_title=findViewById(R.id.tv_app_bar_title);
        ll_back=findViewById(R.id.ll_back);
        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("My Account");
        account_viewpager.setAdapter(new AccountViewPagerAdapter(getSupportFragmentManager()));

        account_tab_layout.setupWithViewPager(account_viewpager);

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
