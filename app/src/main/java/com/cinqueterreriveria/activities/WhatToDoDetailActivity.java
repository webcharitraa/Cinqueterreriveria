package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.models.WhatToDoDetailModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhatToDoDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView iv_what_to_do_detail;
    TextView tv_what_to_do_detail_title,tv_app_bar_title;
    Intent intent;
    LinearLayout ll_back;
    WebView tv_what_to_do_description;
    TransparentDialog dialog=new TransparentDialog();
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_to_do_detail);

        initUis();
    }


    private void initUis()
    {
        intent=getIntent();
        iv_what_to_do_detail=findViewById(R.id.iv_what_to_do_detail);
        ll_back=findViewById(R.id.ll_back);
        tv_what_to_do_detail_title=findViewById(R.id.tv_what_to_do_detail_title);
        tv_what_to_do_description=findViewById(R.id.tv_what_to_do_description);
        tv_app_bar_title =findViewById(R.id.tv_app_bar_title);

        ll_back.setOnClickListener(this);

        WhatToDoDetailApi();
    }

    private void WhatToDoDetailApi()
    {
        Call<WhatToDoDetailModel> call= Rest.getRetrofit().whatToDoDetails(ApiConstents.SECRET_KEY,
                intent.getStringExtra("what_name"));

        dialog.progressDialog(context);
        call.enqueue(new Callback<WhatToDoDetailModel>() {
            @Override
            public void onResponse(Call<WhatToDoDetailModel> call, Response<WhatToDoDetailModel> response) {
                dialog.stopProgressDialog();
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess() == true) {
                        tv_app_bar_title.setVisibility(View.VISIBLE);
                        tv_app_bar_title.setText(response.body().getDetail().getBannerTitle());
                        tv_what_to_do_description.getSettings().setJavaScriptEnabled(true);
                        RequestOptions simpleOptions = new RequestOptions()
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                        if (!response.body().getDetail().getBannerImage().equals(""))
                        {
                            iv_what_to_do_detail.setVisibility(View.VISIBLE);
                            Glide.with(context).load(response.body().getDetail().getBannerImage()).apply(simpleOptions).into(iv_what_to_do_detail);

                        }
                        else {
                            iv_what_to_do_detail.setVisibility(View.GONE);
                        }
                        tv_what_to_do_detail_title.setText(response.body().getDetail().getBannerTitle());
                        tv_what_to_do_description.loadDataWithBaseURL(null, response.body().getDetail().getContent(), "text/html", "utf-8", null);

                       // tv_what_to_do_description.loadData(response.body().getDetail().getContent(), "text/html", "utf-8");

                    }
                }
            }

            @Override
            public void onFailure(Call<WhatToDoDetailModel> call, Throwable t) {
                dialog.stopProgressDialog();

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
