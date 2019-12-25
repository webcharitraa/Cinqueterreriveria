package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.models.ContactUsModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    LinearLayout ll_back;
    TextView tv_app_bar_title, tv_contact_us_title;
    ImageView iv_contact_us;
    WebView webview;
    TransparentDialog dialog = new TransparentDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        initUis();
    }

    private void initUis() {
        ll_back = findViewById(R.id.ll_back);
        tv_app_bar_title = findViewById(R.id.tv_app_bar_title);
        iv_contact_us = findViewById(R.id.iv_contact_us);
        tv_contact_us_title = findViewById(R.id.tv_contact_us_title);
        webview = findViewById(R.id.webview);
        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("Contact Us");
        ll_back.setOnClickListener(this);

        contactUs();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    private void contactUs() {
        Call<ContactUsModel> call = Rest.getRetrofit().contactUs(ApiConstents.SECRET_KEY);
        dialog.progressDialog(context);

        call.enqueue(new Callback<ContactUsModel>() {
            @Override
            public void onResponse(Call<ContactUsModel> call, Response<ContactUsModel> response) {
                dialog.stopProgressDialog();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true) {
                        Glide.with(context).load(response.body().getDetail().getBannerImage()).into(iv_contact_us);
                        webview.getSettings().setJavaScriptEnabled(true);
                        tv_contact_us_title.setText(response.body().getDetail().getBannerTitle());
                        webview.loadDataWithBaseURL(null, response.body().getDetail().getContent(), "text/html", "utf-8", null);

                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<ContactUsModel> call, Throwable t) {

            }
        });

    }
}
