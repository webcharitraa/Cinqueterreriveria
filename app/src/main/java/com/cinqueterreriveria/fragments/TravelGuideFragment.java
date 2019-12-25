package com.cinqueterreriveria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.TravelGuideAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelGuideFragment extends Fragment {

    RecyclerView rv_blogs_travel_guide;

    public TravelGuideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_travel_guide, container, false);
        rv_blogs_travel_guide = view.findViewById(R.id.rv_blogs_travel_guide);
        rv_blogs_travel_guide.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_blogs_travel_guide.setAdapter(new TravelGuideAdapter(getActivity()));
        return view;
    }

}
