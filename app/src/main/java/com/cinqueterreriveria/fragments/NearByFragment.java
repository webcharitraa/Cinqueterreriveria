package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.NearByAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearByFragment extends Fragment {

    RecyclerView rv_near_by;

    public NearByFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_near_by, container, false);
        rv_near_by = view.findViewById(R.id.rv_near_by);
        rv_near_by.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rv_near_by.setAdapter(new NearByAdapter(getActivity()));
        return view;
    }

}
