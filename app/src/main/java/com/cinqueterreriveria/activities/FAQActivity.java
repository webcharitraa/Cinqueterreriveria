package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.FAQsAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.models.FAQsModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    RecyclerView rv_faqs;
    ImageView iv_faq_banner;
    LinearLayout ll_back,ll_faqs_data;
    ShimmerFrameLayout shimmer_faqs;
    TextView tv_faq_title,tv_faq_subtitle,tv_app_bar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_faq);

        initUis();
    }

    private void initUis() {
        rv_faqs = findViewById(R.id.rv_faqs);
        ll_back = findViewById(R.id.ll_back);
        iv_faq_banner = findViewById(R.id.iv_faq_banner);
        tv_faq_title = findViewById(R.id.tv_faq_title);
        tv_faq_subtitle = findViewById(R.id.tv_faq_subtitle);
        ll_faqs_data = findViewById(R.id.ll_faqs_data);
        shimmer_faqs = findViewById(R.id.shimmer_faqs);
        tv_app_bar_title = findViewById(R.id.tv_app_bar_title);
        tv_app_bar_title.setVisibility(View.VISIBLE);

        tv_app_bar_title.setText("F.A.Q");
        rv_faqs.setLayoutManager(new LinearLayoutManager(context));


        ll_back.setOnClickListener(this);

        FaqsApi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    // faq APi
    private void FaqsApi() {
        Call<FAQsModel> call = Rest.getRetrofit().FAQs(ApiConstents.SECRET_KEY);

        shimmer_faqs.startShimmerAnimation();
        call.enqueue(new Callback<FAQsModel>() {
            @Override
            public void onResponse(Call<FAQsModel> call, Response<FAQsModel> response) {
               // Log.e("TAG", String.valueOf(response.body().getDetail().size()));
                if (response.isSuccessful()) {

                    if (response.body().getSuccess() == true) {
                        shimmer_faqs.stopShimmerAnimation();
                        shimmer_faqs.setVisibility(View.GONE);
                        ll_faqs_data.setVisibility(View.VISIBLE);
                        RequestOptions simpleOptions = new RequestOptions()
                                .centerCrop()

                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                        Glide.with(context).load(response.body().getBannerImage()).apply(simpleOptions).into(iv_faq_banner);
                        tv_faq_title.setText(response.body().getBannerTitle());
                        tv_faq_subtitle.setText(response.body().getBannerSubTitle());
                        rv_faqs.setAdapter(new FAQsAdapter(context,response.body().getDetail()));
                    }
                    else {
                        Log.e("TAG","else");

                    }
                }
            }

            @Override
            public void onFailure(Call<FAQsModel> call, Throwable t) {
                Log.e("TAG","failure"+t.getMessage());

            }
        });
    }
}
