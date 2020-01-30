package com.cinqueterreriveria.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.cinqueterreriveria.fragments.DashBoardReachUsFragment;
import com.cinqueterreriveria.fragments.DashboardBlogFragment;
import com.cinqueterreriveria.fragments.DashboardVideoFragment;

public class DashboardViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
    int mCurrentPosition=-1;

    public DashboardViewPagerAdapter(FragmentManager manager) {
        super(manager);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Fragment fragment= null;
        if (position == 0)
        {
            fragment=new DashboardBlogFragment();
        }
        else if (position ==1)
        {
            fragment=new DashboardVideoFragment();
        }
        else if (position == 2)
        {
            fragment=new DashBoardReachUsFragment();
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
            title = "Blog";
        }
        else if (position == 1)
        {
            title = "Video";
        }
        else if (position == 2)
        {
            title = "How to Reach Us";
        }
        return title;
    }

   /* @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (position != mCurrentPosition) {
            Fragment fragment = (Fragment) object;
            NonSwipeableViewPager pager = (NonSwipeableViewPager) container;
            if (fragment != null && fragment.getView() != null) {
                mCurrentPosition = position;
                pager.measureCurrentView(fragment.getView());
            }
        }
    }*/

}















/*extends FragmentPagerAdapter {
    Context context;

    public DashboardViewPagerAdapter(Context context, FragmentManager manager) {
        super(manager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new DashboardBlogFragment();
        }
        else if (position == 1)
        {
            fragment = new DashboardVideoFragment();
        }
        else if (position == 2)
        {
            fragment = new DashBoardReachUsFragment();
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
            title = "Blog";
        }
        else if (position == 1)
        {
            title = "Video";
        }
        else if (position == 2)
        {
            title = "How to Reach Us";
        }
        return title;
    }
}
*/
