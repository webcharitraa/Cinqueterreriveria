package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.BookingDetailPagerAdapter;
import com.cinqueterreriveria.adapters.NonSwipeableViewPager;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    NonSwipeableViewPager view_pager_booking;
    TabLayout booking_tab;

    public BookingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_booking, container, false);
        booking_tab=view.findViewById(R.id.booking_tab);
        view_pager_booking=view.findViewById(R.id.view_pager_booking);


        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        view_pager_booking.setPageMargin(pageMargin);

        booking_tab.setupWithViewPager(view_pager_booking);

        view_pager_booking.setAdapter(new BookingDetailPagerAdapter(getChildFragmentManager()));

        return  view;
    }

}
