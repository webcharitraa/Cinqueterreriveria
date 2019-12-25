package com.cinqueterreriveria.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cinqueterreriveria.fragments.AmenitiesFragment;
import com.cinqueterreriveria.fragments.DashBoardReachUsFragment;
import com.cinqueterreriveria.fragments.DashboardBlogFragment;
import com.cinqueterreriveria.fragments.DashboardVideoFragment;
import com.cinqueterreriveria.fragments.ExtraChargesFragment;
import com.cinqueterreriveria.fragments.FloorPlanFragment;
import com.cinqueterreriveria.fragments.NearByFragment;
import com.cinqueterreriveria.fragments.OurRulesFragment;

public class PlaceDetailViewPagerAdapter extends FragmentPagerAdapter {
    Context context;

    public PlaceDetailViewPagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new AmenitiesFragment();
        }
        else if (position == 1)
        {
            fragment = new OurRulesFragment();
        }
        else if (position == 2)
        {
            fragment = new ExtraChargesFragment();
        }
        else if (position == 3)
        {
            fragment = new FloorPlanFragment();
        }else if (position == 4)
        {
            fragment = new DashBoardReachUsFragment();
        }
        else if (position == 5)
        {
            fragment = new NearByFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Amenities";
        }
        else if (position == 1)
        {
            title = "Our Rules";
        }
        else if (position == 2)
        {
            title = "Extra Charges";
        }
        else if (position == 3)
        {
            title = "Flore Plans";
        }
        else if (position == 4)
        {

            title = "Reviews";
        }else if (position == 5)
        {
            title = "Near By";
        }

        return title;
    }
}