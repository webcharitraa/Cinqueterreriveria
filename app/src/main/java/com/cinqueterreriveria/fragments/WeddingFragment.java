package com.cinqueterreriveria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.weddingAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeddingFragment extends Fragment {


    RecyclerView rv_blogs_weddings;
    public WeddingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_view_all, container, false);
        rv_blogs_weddings=view.findViewById(R.id.rv_blogs_weddings);
        rv_blogs_weddings.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_blogs_weddings.setAdapter(new weddingAdapter(getActivity()));
        return view;
    }

}
