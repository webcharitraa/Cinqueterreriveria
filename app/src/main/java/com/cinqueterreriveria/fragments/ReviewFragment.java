package com.cinqueterreriveria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.SinglePlaceDetailActivity;
import com.cinqueterreriveria.adapters.ReviewsAdapter;
import com.cinqueterreriveria.common.MySpannable;


public class ReviewFragment extends Fragment {

    RecyclerView rv_review;
    TextView tv_no_reviews;

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_review, container, false);
        rv_review = view.findViewById(R.id.rv_review);
        tv_no_reviews = view.findViewById(R.id.tv_no_reviews);
        rv_review.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        if (SinglePlaceDetailActivity.reviewsList.size()> 0)
        {
            tv_no_reviews.setVisibility(View.GONE);
            rv_review.setAdapter(new ReviewsAdapter(getActivity(), SinglePlaceDetailActivity.reviewsList));

        }
        else
        {
            rv_review.setVisibility(View.GONE);
            tv_no_reviews.setVisibility(View.VISIBLE);
        }
        return view;
    }


}
