package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.SinglePlaceDetailActivity;
import com.cinqueterreriveria.adapters.AmenitiesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmenitiesFragment extends Fragment {


    RecyclerView rv_amenities;

    public AmenitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_amenities, container, false);
        rv_amenities = view.findViewById(R.id.rv_amenities);

        rv_amenities.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rv_amenities.setAdapter(new AmenitiesAdapter(getActivity(), SinglePlaceDetailActivity.amentiesList));
        return view;
    }

}
