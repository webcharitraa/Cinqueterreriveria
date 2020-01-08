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
import com.cinqueterreriveria.adapters.BookingsAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.common.PrefStore;
import com.cinqueterreriveria.models.AccountBookingModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTypeBookingFragment extends Fragment {

    RecyclerView rv_all_bookings;
    TextView tv_no_data_found;

    PrefStore prefStore;

    public AllTypeBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_type_booking, container, false);
        prefStore = new PrefStore(getActivity());

        rv_all_bookings = view.findViewById(R.id.rv_all_bookings);
        tv_no_data_found = view.findViewById(R.id.tv_no_data_found);

        rv_all_bookings.setLayoutManager(new LinearLayoutManager(getActivity()));


        allBookings();

        return view;
    }

    // all bookings APi
    private void allBookings() {
        Call<AccountBookingModel> call = Rest.getRetrofit().bookingList(ApiConstents.SECRET_KEY,
                "1", "all");

        call.enqueue(new Callback<AccountBookingModel>() {
            @Override
            public void onResponse(Call<AccountBookingModel> call, Response<AccountBookingModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("true")) {

                        if (response.body().getDetail().size()> 0)
                        {
                            tv_no_data_found.setVisibility(View.GONE);
                            rv_all_bookings.setVisibility(View.VISIBLE);
                            rv_all_bookings.setAdapter(new BookingsAdapter(getActivity(), response.body().getDetail()));

                        }
                        else {
                            rv_all_bookings.setVisibility(View.GONE);
                            tv_no_data_found.setVisibility(View.VISIBLE);
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), "False", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountBookingModel> call, Throwable t) {

            }
        });
    }

}
