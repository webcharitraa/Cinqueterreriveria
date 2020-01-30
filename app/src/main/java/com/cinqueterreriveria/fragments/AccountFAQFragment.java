package com.cinqueterreriveria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.AccountFaqAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.models.UserFAQModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFAQFragment extends Fragment {


    RecyclerView rv_account_faq;
    ShimmerFrameLayout account_shimmer_faqs;
    LinearLayout ll_account_faq_data;
    TextView tv_account_faq_title,tv_account_faq_subtitle;

    public AccountFAQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account_faq, container, false);
        rv_account_faq=view.findViewById(R.id.rv_account_faq);
        account_shimmer_faqs=view.findViewById(R.id.account_shimmer_faqs);
        ll_account_faq_data=view.findViewById(R.id.ll_account_faq_data);
        tv_account_faq_title=view.findViewById(R.id.tv_account_faq_title);
        tv_account_faq_subtitle=view.findViewById(R.id.tv_account_faq_subtitle);

        rv_account_faq.setLayoutManager(new LinearLayoutManager(getActivity()));


        AccountFaqsApi();
        return view;
    }

    // faq APi
    private void AccountFaqsApi() {
        Call<UserFAQModel> call = Rest.getRetrofit().accountFAQ(ApiConstents.SECRET_KEY);

        account_shimmer_faqs.startShimmerAnimation();
        call.enqueue(new Callback<UserFAQModel>() {
            @Override
            public void onResponse(Call<UserFAQModel> call, Response<UserFAQModel> response) {
                // Log.e("TAG", String.valueOf(response.body().getDetail().size()));
                if (response.isSuccessful()) {

                    if (response.body().getSuccess() == true) {
                        account_shimmer_faqs.stopShimmerAnimation();
                        account_shimmer_faqs.setVisibility(View.GONE);
                        ll_account_faq_data.setVisibility(View.VISIBLE);
                        RequestOptions simpleOptions = new RequestOptions()
                                .centerCrop()

                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                        tv_account_faq_title.setText(response.body().getFaqDetail().getTitle());
                        tv_account_faq_subtitle.setText(response.body().getFaqDetail().getSubtitle());
                        rv_account_faq.setAdapter(new AccountFaqAdapter(getActivity(),response.body().getFaqDetail().getDetail()));
                    }
                    else {
                        Log.e("TAG","else");

                    }
                }
            }

            @Override
            public void onFailure(Call<UserFAQModel> call, Throwable t) {
                Log.e("TAG","failure"+t.getMessage());

            }
        });
    }

}
