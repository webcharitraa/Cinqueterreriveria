package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.ExtraChargesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExtraChargesFragment extends Fragment {

RecyclerView rv_ectra_charges;
    public ExtraChargesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_extra_charges, container, false);
        rv_ectra_charges=view.findViewById(R.id.rv_ectra_charges);
        rv_ectra_charges.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_ectra_charges.setAdapter(new ExtraChargesAdapter(getActivity()));
        return view;
    }

}
