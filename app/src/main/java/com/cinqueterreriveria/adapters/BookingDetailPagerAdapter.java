package com.cinqueterreriveria.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cinqueterreriveria.fragments.AllTypeBookingFragment;

import com.cinqueterreriveria.fragments.CanceledBookingFragment;
import com.cinqueterreriveria.fragments.CompletedFragment;

import com.cinqueterreriveria.fragments.UpcomingBookingsFragment;


public class BookingDetailPagerAdapter extends FragmentPagerAdapter {


    public BookingDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment= null;
        if (position == 0)
        {
            fragment=new AllTypeBookingFragment();
        }
        else if (position ==1)
        {
            fragment=new UpcomingBookingsFragment();
        }
        else if (position == 2)
        {
            fragment=new CanceledBookingFragment();
        }else if (position == 3)
        {
            fragment=new CompletedFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title= "";

        if (position == 0)
        {
            title= "All Types";
        }
        else if (position ==1)
        {
            title= "UpComing";
        }
        else if (position == 2)
        {
            title= "Cancelled";
        } else if (position == 3)
        {
            title= "Completed";
        }
        return title;
    }
}

