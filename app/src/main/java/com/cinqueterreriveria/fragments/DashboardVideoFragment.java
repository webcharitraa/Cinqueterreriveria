package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.DashboardActivity;
import com.cinqueterreriveria.adapters.BlogAdapter;
import com.cinqueterreriveria.adapters.DashboardVideoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardVideoFragment extends Fragment {

RecyclerView rv_video;
    public DashboardVideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard_video, container, false);

        rv_video=view.findViewById(R.id.rv_video);

        rv_video.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_video.setAdapter(new DashboardVideoAdapter(getActivity(), DashboardActivity.videosList));
        return view;
    }


}
