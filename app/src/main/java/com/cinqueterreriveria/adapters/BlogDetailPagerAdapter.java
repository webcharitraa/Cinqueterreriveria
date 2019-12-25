package com.cinqueterreriveria.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cinqueterreriveria.fragments.BlogDetailAccomodationFragment;
import com.cinqueterreriveria.fragments.BoatTourFragment;
import com.cinqueterreriveria.fragments.FoodWineFragment;
import com.cinqueterreriveria.fragments.TraditionsFragment;
import com.cinqueterreriveria.fragments.TravelGuideFragment;
import com.cinqueterreriveria.fragments.WeddingFragment;
import com.cinqueterreriveria.fragments.VillageTipsFragment;

public class BlogDetailPagerAdapter extends FragmentPagerAdapter {


    public BlogDetailPagerAdapter( FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment= null;
        if (position == 0)
        {
            fragment=new BlogDetailAccomodationFragment();
        }
        else if (position ==1)
        {
            fragment=new BoatTourFragment();
        }
        else if (position == 2)
        {
            fragment=new TravelGuideFragment();
        }else if (position == 3)
        {
            fragment=new VillageTipsFragment();
        }else if (position == 4)
        {
            fragment=new WeddingFragment();
        }else if (position == 5)
        {
            fragment=new FoodWineFragment();
        }else if (position == 6)
        {
            fragment=new TraditionsFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title= "";
        if (position == 0)
        {
            title= "Accomodation";
        }
        else if (position ==1)
        {
            title= "Boat Tour";
        }
        else if (position == 2)
        {
            title= "Travel Guide";
        }else if (position == 3)
        {
            title= "Village-tips";
        }else if (position == 4)
        {
            title= "Wedding";
        }else if (position == 5)
        {
            title= "Food Wine";
        }else if (position == 6)
        {
            title= "Traditions";
        }
        return title;
    }
}
