package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.BlogAccommodationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogDetailAccomodationFragment extends Fragment {
    RecyclerView rv_blogs_accommodation;

    public BlogDetailAccomodationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog_detail_accomodation, container, false);
        rv_blogs_accommodation = view.findViewById(R.id.rv_blogs_accommodation);
        rv_blogs_accommodation.setLayoutManager(new LinearLayoutManager(getActivity()));

        rv_blogs_accommodation.setAdapter(new BlogAccommodationAdapter(getActivity()));
        return view;
    }

}
