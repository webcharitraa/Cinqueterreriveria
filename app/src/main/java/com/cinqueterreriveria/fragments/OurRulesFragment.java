package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OurRulesFragment extends Fragment {


    public OurRulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_our_rules, container, false);
    }

}
