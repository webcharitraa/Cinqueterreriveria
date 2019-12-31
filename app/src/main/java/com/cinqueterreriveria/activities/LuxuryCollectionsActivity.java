package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.LuxuryCollectionAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.models.LuxuryCollectionModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LuxuryCollectionsActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout ll_back;
    RecyclerView rv_luxury_collection;
    Context context = this;
    TextView tv_app_bar_title;
    TransparentDialog dialog = new TransparentDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luxury_collections);

        initUis();
    }

    private void initUis() {
        ll_back = findViewById(R.id.ll_back);
        rv_luxury_collection = findViewById(R.id.rv_luxury_collection);
        tv_app_bar_title = findViewById(R.id.tv_app_bar_title);

        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("Luxury Properties");
        rv_luxury_collection.setLayoutManager(new LinearLayoutManager(this));

        ll_back.setOnClickListener(this);
        luxuryCollectionAPi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    private void luxuryCollectionAPi() {
        Call<LuxuryCollectionModel> call = Rest.getRetrofit().luxuryCollection(ApiConstents.SECRET_KEY,
                "luxury-collection");
        dialog.progressDialog(context);
        call.enqueue(new Callback<LuxuryCollectionModel>() {
            @Override
            public void onResponse(Call<LuxuryCollectionModel> call, Response<LuxuryCollectionModel> response) {
                dialog.stopProgressDialog();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true) {

                        rv_luxury_collection.setAdapter(new LuxuryCollectionAdapter(context, response.body().getDetail()));
                        // tv_what_to_do_description.loadData(response.body().getDetail().getContent(), "text/html", "utf-8");

                    }
                }
            }

            @Override
            public void onFailure(Call<LuxuryCollectionModel> call, Throwable t) {
                dialog.stopProgressDialog();

            }
        });
    }
}
