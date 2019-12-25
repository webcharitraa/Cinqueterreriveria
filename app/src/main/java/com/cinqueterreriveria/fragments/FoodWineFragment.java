package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.FoodWineAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodWineFragment extends Fragment {

    RecyclerView rv_blogs_food_wine;

    public FoodWineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_food_wine, container, false);
        rv_blogs_food_wine=view.findViewById(R.id.rv_blogs_food_wine);
        rv_blogs_food_wine.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_blogs_food_wine.setAdapter(new FoodWineAdapter(getActivity()));
        return view;
    }

}