package com.cinqueterreriveria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.SinglePlaceDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FloorPlanFragment extends Fragment {
    ImageView iv_flore;

    public FloorPlanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_floor_plan, container, false);
        iv_flore = view.findViewById(R.id.iv_flore);
        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(getActivity()).load(SinglePlaceDetailActivity.florePlanImage).placeholder(R.drawable.placeholder).apply(simpleOptions).into(iv_flore);
        return view;
    }

}
