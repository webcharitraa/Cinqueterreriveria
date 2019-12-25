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
import com.cinqueterreriveria.models.HowToReachDetailModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HowToReachDetailActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView iv_how_to_reach_detail;
    TextView tv_how_to_reach_title,tv_app_bar_title;
    WebView tv_how_to_reach_description;
    Intent intent;
    TransparentDialog dialog=new TransparentDialog();
    Context context=this;
    LinearLayout ll_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_reach_detail);

        initUis();
    }

    private void initUis()
    {
        intent=getIntent();
        iv_how_to_reach_detail=findViewById(R.id.iv_how_to_reach_detail);
        tv_how_to_reach_title=findViewById(R.id.tv_how_to_reach_title);
        tv_how_to_reach_description=findViewById(R.id.tv_how_to_reach_description);
        ll_back=findViewById(R.id.ll_back);
        tv_app_bar_title=findViewById(R.id.tv_app_bar_title);

        ll_back.setOnClickListener(this);
        HowToReachDetailApi();
    }

    private void HowToReachDetailApi()
    {
        Call<HowToReachDetailModel> call= Rest.getRetrofit().howToReachDetails(ApiConstents.SECRET_KEY,
                intent.getStringExtra("how_to_reach_name"));

        dialog.progressDialog(context);
        call.enqueue(new Callback<HowToReachDetailModel>() {
            @Override
            public void onResponse(Call<HowToReachDetailModel> call, Response<HowToReachDetailModel> response) {
                dialog.stopProgressDialog();
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess() == true) {

                        tv_app_bar_title.setVisibility(View.VISIBLE);
                        tv_app_bar_title.setText(response.body().getDetail().getBannerTitle());
                        tv_how_to_reach_description.getSettings().setJavaScriptEnabled(true);
                        RequestOptions simpleOptions = new RequestOptions()
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                        Glide.with(context).load(response.body().getDetail().getBannerImage()).apply(simpleOptions).into(iv_how_to_reach_detail);
                        tv_how_to_reach_title.setText(response.body().getDetail().getBannerTitle());
                        tv_how_to_reach_description.loadDataWithBaseURL(null, response.body().getDetail().getContent(), "text/html", "utf-8", null);

                       // tv_how_to_reach_description.loadData(response.body().getDetail().getContent(), "text/html", "utf-8");

                    }
                }
            }

            @Override
            public void onFailure(Call<HowToReachDetailModel> call, Throwable t) {
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
