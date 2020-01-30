package com.cinqueterreriveria.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.DashboardActivity;
import com.cinqueterreriveria.adapters.BlogAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardBlogFragment extends Fragment {

    RecyclerView rv_blog;

    public DashboardBlogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard_home, container, false);

        rv_blog = view.findViewById(R.id.rv_blog);

        rv_blog.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        rv_blog.setAdapter(new BlogAdapter(getActivity(), DashboardActivity.blogsList));
        Log.d("TAG", " BlogList "+String.valueOf(DashboardActivity.blogsList.size()));
        return view;
    }

}

