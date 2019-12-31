package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.PlaceListAdapter;
import com.cinqueterreriveria.adapters.Place_Filter_categoryAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.models.PlaceListModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceListActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = this;
    RecyclerView rv_place,rv_category,rv_sub_category;
    LinearLayout ll_back;
    TextView tv_app_bar_title;
    ImageView iv_filter;
    Intent intent;
    ShimmerFrameLayout shimmer_list_container;
    DrawerLayout place_list_drawer;
    TransparentDialog dialog = new TransparentDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        initUis();
    }

    private void initUis() {
        intent = getIntent();
        ll_back = findViewById(R.id.ll_back);
        rv_place = findViewById(R.id.rv_place);
        shimmer_list_container = findViewById(R.id.shimmer_list_container);
        tv_app_bar_title = findViewById(R.id.tv_app_bar_title);
        iv_filter = findViewById(R.id.iv_filter);
        place_list_drawer = findViewById(R.id.place_list_drawer);
        rv_category = findViewById(R.id.rv_category);
        rv_sub_category = findViewById(R.id.rv_sub_category);
        iv_filter.setVisibility(View.VISIBLE);
        tv_app_bar_title.setVisibility(View.VISIBLE);
        rv_place.setLayoutManager(new LinearLayoutManager(context));
        String text = intent.getStringExtra("place_name").substring(0, 1).toUpperCase() + intent.getStringExtra("place_name").substring(1);
        tv_app_bar_title.setText(text);
        ll_back.setOnClickListener(this);
        iv_filter.setOnClickListener(this);

        place_list_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        placeListApi();

        rv_category.setLayoutManager(new LinearLayoutManager(context));
        rv_category.setAdapter(new Place_Filter_categoryAdapter(context));
        rv_sub_category.setLayoutManager(new LinearLayoutManager(context));
        rv_sub_category.setAdapter(new Place_Filter_categoryAdapter(context));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;

            case R.id.iv_filter:
                place_list_drawer.openDrawer(GravityCompat.END);
                break;
        }
    }

    private void placeListApi() {
        shimmer_list_container.startShimmerAnimation();

        // dialog.progressDialog(context);
        Call<PlaceListModel> call = Rest.getRetrofit().placeList(ApiConstents.SECRET_KEY,
                intent.getStringExtra("place_name"));
        call.enqueue(new Callback<PlaceListModel>() {
            @Override
            public void onResponse(Call<PlaceListModel> call, Response<PlaceListModel> response) {
                shimmer_list_container.stopShimmerAnimation();
                shimmer_list_container.setVisibility(View.GONE);
                rv_place.setVisibility(View.VISIBLE);
                // dialog.stopProgressDialog();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true) {
                        rv_place.setAdapter(new PlaceListAdapter(context, response.body().getLocationProperties(),
                                intent.getStringExtra("place_name")));
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceListModel> call, Throwable t) {
                dialog.stopProgressDialog();

            }
        });
    }
}
