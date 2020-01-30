package com.cinqueterreriveria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.DashboardActivity;
import com.cinqueterreriveria.adapters.DashBoardReachAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardReachUsFragment extends Fragment {

RecyclerView rv_how_to_reach;
    public DashBoardReachUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dash_board_reach_us, container, false);

        rv_how_to_reach=view.findViewById(R.id.rv_how_to_reach);
        rv_how_to_reach.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_how_to_reach.setAdapter(new DashBoardReachAdapter(getActivity(), DashboardActivity.howReachList));
        return view;
    }

}
