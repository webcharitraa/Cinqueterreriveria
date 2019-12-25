package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.cinqueterreriveria.models.PDFModel;
import com.cinqueterreriveria.models.WhatToDoDetailModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView iv_services_detail;
    WebView tv_services_description;
    TextView tv_services_title,tv_app_bar_title;
    Intent intent;
    TransparentDialog dialog=new TransparentDialog();
    Context context=this;
    LinearLayout ll_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_detail);

        initUis();
    }

    private  void initUis()
    {
        intent=getIntent();

        iv_services_detail=findViewById(R.id.iv_services_detail);
        tv_services_title=findViewById(R.id.tv_services_title);
        tv_services_description=findViewById(R.id.tv_services_description);
        tv_app_bar_title=findViewById(R.id.tv_app_bar_title);
        ll_back=findViewById(R.id.ll_back);



         if (intent.getStringExtra("flag").equals("rental-agreement"))
        {
            pdfAPi();
        } if (intent.getStringExtra("flag").equals("travelling-with-us"))
        {
            servicesDetailApi();
        }if (intent.getStringExtra("flag").equals("wedding-proposal-in-the-cinque-terre"))
        {
            servicesDetailApi();
        }

        ll_back.setOnClickListener(this);
    }

    private void pdfAPi()
    {
        Call<PDFModel> call=Rest.getRetrofit().pdfDetail(ApiConstents.SECRET_KEY,
                intent.getStringExtra("flag"));

        call.enqueue(new Callback<PDFModel>() {
            @Override
            public void onResponse(Call<PDFModel> call, Response<PDFModel> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess()== true)
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse(response.body().getDetail().getPdf()), "application/pdf");
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<PDFModel> call, Throwable t) {

            }
        });
    }

    private void servicesDetailApi()
    {
        Call<WhatToDoDetailModel> call= Rest.getRetrofit().servicesDetail(ApiConstents.SECRET_KEY,
                intent.getStringExtra("flag"));

        dialog.progressDialog(context);

        call.enqueue(new Callback<WhatToDoDetailModel>() {
            @Override
            public void onResponse(Call<WhatToDoDetailModel> call, Response<WhatToDoDetailModel> response) {
                dialog.stopProgressDialog();
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess() == true)
                    {
                        tv_app_bar_title.setVisibility(View.VISIBLE);
                        tv_app_bar_title.setText(response.body().getDetail().getBannerTitle());
                        tv_services_description.getSettings().setJavaScriptEnabled(true);
                        RequestOptions simpleOptions = new RequestOptions()
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                        if (!response.body().getDetail().getBannerImage().equals(""))
                        {
                            iv_services_detail.setVisibility(View.VISIBLE);
                            Glide.with(context).load(response.body().getDetail().getBannerImage()).apply(simpleOptions).into(iv_services_detail);

                        }
                        else {
                            iv_services_detail.setVisibility(View.GONE);
                        }
                        tv_services_title.setText(response.body().getDetail().getBannerTitle());
                        tv_services_description.loadDataWithBaseURL(null, response.body().getDetail().getContent(), "text/html", "utf-8", null);

                    }
                    else {

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
