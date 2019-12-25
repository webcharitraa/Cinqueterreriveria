package com.cinqueterreriveria.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.BoatTourAdapter;


public class BoatTourFragment extends Fragment {


    RecyclerView rv_blogs_boat_tour;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_boat_tour, container, false);
        rv_blogs_boat_tour=view.findViewById(R.id.rv_blogs_boat_tour);
        rv_blogs_boat_tour.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_blogs_boat_tour.setAdapter(new BoatTourAdapter(getActivity()));
        return view;
    }


}
