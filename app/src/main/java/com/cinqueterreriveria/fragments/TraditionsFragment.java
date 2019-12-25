package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.TraditionsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TraditionsFragment extends Fragment {

    RecyclerView rv_blogs_traditions;


    public TraditionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_traditions, container, false);
        rv_blogs_traditions = view.findViewById(R.id.rv_blogs_traditions);
        rv_blogs_traditions.setNestedScrollingEnabled(false);
        rv_blogs_traditions.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_blogs_traditions.setAdapter(new TraditionsAdapter(getActivity()));


        return view;
    }

}
