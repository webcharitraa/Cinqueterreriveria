package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class UpcomingBookingsFragment extends Fragment {

    RecyclerView rv_upcoming;
    PrefStore prefStore;
    TextView tv_no_data_found;

    public UpcomingBookingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upcoming_bookings, container, false);
        prefStore=new PrefStore(getActivity());
        rv_upcoming=view.findViewById(R.id.rv_upcoming);
        tv_no_data_found=view.findViewById(R.id.tv_no_data_found);
        rv_upcoming.setLayoutManager(new LinearLayoutManager(getActivity()));

        upcomingBookings();
        return view;
    }

    // all bookings APi
    private void upcomingBookings()
    {
        Call<AccountBookingModel> call= Rest.getRetrofit().bookingList(ApiConstents.SECRET_KEY,
                prefStore.getString("user_id"),"upcoming");

        call.enqueue(new Callback<AccountBookingModel>() {
            @Override
            public void onResponse(Call<AccountBookingModel> call, Response<AccountBookingModel> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess().equals("true"))
                    {
                        if (response.body().getDetail().size()>0)
                        {
                            tv_no_data_found.setVisibility(View.GONE);
                            rv_upcoming.setVisibility(View.VISIBLE);
                            rv_upcoming.setAdapter(new BookingsAdapter(getActivity(),response.body().getDetail()));

                        }
                        else {
                            rv_upcoming.setVisibility(View.GONE);
                            tv_no_data_found.setVisibility(View.VISIBLE);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountBookingModel> call, Throwable t) {

            }
        });
    }


}
