package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.VillageTipsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class VillageTipsFragment extends Fragment {

    RecyclerView rv_blogs_village_tips;

    public VillageTipsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_village_tips, container, false);
        rv_blogs_village_tips=view.findViewById(R.id.rv_blogs_village_tips);
        rv_blogs_village_tips.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_blogs_village_tips.setAdapter(new VillageTipsAdapter(getActivity()));
        return view;
    }

}
