package com.cinqueterreriveria.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cinqueterreriveria.fragments.AccountFAQFragment;
import com.cinqueterreriveria.fragments.BookingFragment;
import com.cinqueterreriveria.fragments.DashBoardReachUsFragment;
import com.cinqueterreriveria.fragments.DashboardBlogFragment;
import com.cinqueterreriveria.fragments.DashboardVideoFragment;
import com.cinqueterreriveria.fragments.YourInfoFragment;

public class AccountViewPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public AccountViewPagerAdapter(FragmentManager manager) {
        super(manager);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment= null;
        if (position == 0)
        {
            fragment=new BookingFragment();
        }
        else if (position ==1)
        {
            fragment=new YourInfoFragment();
        }
        else if (position == 2)
        {
            fragment=new AccountFAQFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (position == 0)
        {
            title = "Bookings";
        }
        else if (position == 1)
        {
            title = "Your Info";
        }
        else if (position == 2)
        {
            title = "F.A.Q s";
        }
        return title;
    }
}








