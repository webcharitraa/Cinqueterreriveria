package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.SinglePlaceDetailActivity;
import com.cinqueterreriveria.adapters.PropertyRatesAdapter;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 */
public class OurRulesFragment extends Fragment {
    RecyclerView rv_property_rates;
    TextView tv_our_min_stay,tv_garbage,tv_smoking,tv_pets,tv_time_check_in,tv_time_check_out;

    public OurRulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_our_rules, container, false);
        rv_property_rates = view.findViewById(R.id.rv_property_rates);
        tv_our_min_stay = view.findViewById(R.id.tv_our_min_stay);
        tv_garbage = view.findViewById(R.id.tv_garbage);
        tv_smoking = view.findViewById(R.id.tv_smoking);
        tv_pets = view.findViewById(R.id.tv_pets);
        tv_time_check_in = view.findViewById(R.id.tv_time_check_in);
        tv_time_check_out = view.findViewById(R.id.tv_time_check_out);

        try {
            tv_our_min_stay.setText(SinglePlaceDetailActivity.requiredDataArray.getJSONObject(0).getString("Minimum Stay"));
            tv_garbage.setText(SinglePlaceDetailActivity.requiredDataArray.getJSONObject(0).getString("Garbage Collection"));
            tv_smoking.setText(SinglePlaceDetailActivity.requiredDataArray.getJSONObject(0).getString("Smoking"));
            tv_pets.setText(SinglePlaceDetailActivity.requiredDataArray.getJSONObject(0).getString("Pets"));
            tv_time_check_in.setText(SinglePlaceDetailActivity.requiredDataArray.getJSONObject(0).getString("Check-In Time"));
            tv_time_check_out.setText(SinglePlaceDetailActivity.requiredDataArray.getJSONObject(0).getString("Check-Out Time"));
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        rv_property_rates.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_property_rates.setAdapter(new PropertyRatesAdapter(getActivity(), SinglePlaceDetailActivity.propertyRates));
        return view;
    }

}
