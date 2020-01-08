package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.HomeSearchAdapter;
import com.cinqueterreriveria.adapters.PlaceListAdapter;
import com.cinqueterreriveria.adapters.PlacePricingAdapter;
import com.cinqueterreriveria.adapters.PlaceSubcategoryFilterAdapter;
import com.cinqueterreriveria.adapters.Place_Filter_categoryAdapter;
import com.cinqueterreriveria.adapters.PropertytypeAdapter;
import com.cinqueterreriveria.adapters.RatingAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.models.HomeSearchModel;
import com.cinqueterreriveria.models.PlaceListModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceListActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = this;
    RecyclerView rv_place, rv_category, rv_sub_category;
    LinearLayout ll_back;
    TextView tv_app_bar_title, tv_clear_filter, tv_apply_filter, tv_no_data;
    ImageView iv_filter;
    Intent intent;
    String price = "", reser = "", rating1 = "", property_tye = "", start_price = "", end_price = "";
    String[] filter_categories = {"Pricing", "Rating", "Reservation", "Property Type"};
    String[] rating = {"5", "4", "3", "2", "1"};
    String[] reservation = {"All",
            "On Request",
            "Instant Bookable"};
    String[] pricing = {"All",
            "€100 - €200",
            "€200 - €300",
            "€300 - €400",
            "€400 - €500",
            "€500 - €600",
            "€600 - €700",
            "€700 - €800",
            "€800 - €900"};
    ShimmerFrameLayout shimmer_list_container;
    DrawerLayout place_list_drawer;
    TransparentDialog dialog = new TransparentDialog();
    List<PlaceListModel.PropertyType> property_type = new ArrayList<>();

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
        tv_clear_filter = findViewById(R.id.tv_clear_filter);
        tv_apply_filter = findViewById(R.id.tv_apply_filter);
        tv_no_data = findViewById(R.id.tv_no_data);

        tv_app_bar_title.setVisibility(View.VISIBLE);
        rv_place.setLayoutManager(new LinearLayoutManager(context));
        if (intent.getStringExtra("flag").equals("place")) {
            String text = intent.getStringExtra("place_name").substring(0, 1).toUpperCase() + intent.getStringExtra("place_name").substring(1);
            tv_app_bar_title.setText(text);
        }
        ll_back.setOnClickListener(this);
        iv_filter.setOnClickListener(this);
        tv_clear_filter.setOnClickListener(this);
        tv_apply_filter.setOnClickListener(this);

        place_list_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        if (intent.getStringExtra("flag").equals("place")) {
            iv_filter.setVisibility(View.VISIBLE);
            placeListApi();
        }
        if (intent.getStringExtra("flag").equals("search")) {
            iv_filter.setVisibility(View.GONE);
            HomeSearchApi();
        }

        rv_category.setLayoutManager(new LinearLayoutManager(context));
        rv_category.setAdapter(new Place_Filter_categoryAdapter(context, filter_categories, new Place_Filter_categoryAdapter.onClickListerner() {
            @Override
            public void onClick(String type) {
                if (type.equals("Pricing")) {
                    rv_sub_category.setLayoutManager(new LinearLayoutManager(context));
                    rv_sub_category.setAdapter(new PlacePricingAdapter(context, pricing, new PlacePricingAdapter.onRadioClick() {
                        @Override
                        public void onClick(String price) {
                            price = price;

                            if (!price.equals("All") && !price.equals("")) {
                                String[] separated = price.split("-");
                                String start_doller = separated[0]; // this will contain "Fruit"
                                String end_doller = separated[1]; // this will contain " they taste good"
                                if (start_doller.contains("€")) {
                                    start_price = start_doller.replace("€", "");

                                    // final_start_price= Integer.parseInt(start_price);
                                }
                                if (end_doller.contains("€")) {
                                    end_price = end_doller.replace("€", "");
                                    //final_end_price= Integer.parseInt(end_price);

                                }

                                Log.e("TAG", start_price + "  " + end_price);
                            } else {
                                start_price = "";
                                end_price = "";
                            }
                        }
                    }));
                }
                if (type.equals("Rating")) {
                    rv_sub_category.setLayoutManager(new LinearLayoutManager(context));
                    rv_sub_category.setAdapter(new RatingAdapter(context, rating, new RatingAdapter.onRadioClick() {
                        @Override
                        public void onClick(String rating) {
                            rating1 = rating;
                            Log.e("TAG", "rating " + rating);

                        }
                    }));
                }
                if (type.equals("Reservation")) {
                    rv_sub_category.setLayoutManager(new LinearLayoutManager(context));
                    rv_sub_category.setAdapter(new PlaceSubcategoryFilterAdapter(context, reservation, new PlaceSubcategoryFilterAdapter.onRadioClick() {
                        @Override
                        public void onClick(String reservation) {
                            reser = reservation;
                            Log.e("TAG", reser);
                        }
                    }));
                }
                if (type.equals("Property Type")) {
                    rv_sub_category.setLayoutManager(new LinearLayoutManager(context));
                    rv_sub_category.setAdapter(new PropertytypeAdapter(context, property_type, new PropertytypeAdapter.onRadioClick() {
                        @Override
                        public void onClick(String property_type) {
                            property_tye = property_type;
                            Log.e("TAG", property_tye);
                        }
                    }));
                }
            }
        }));
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
            case R.id.tv_clear_filter:
                place_list_drawer.closeDrawer(GravityCompat.END);
                break;
            case R.id.tv_apply_filter:
                place_list_drawer.closeDrawer(GravityCompat.END);
                placeListApi();
                break;
        }
    }

    private void placeListApi() {

        shimmer_list_container.startShimmerAnimation();

        Call<PlaceListModel> call = Rest.getRetrofit().placeList(ApiConstents.SECRET_KEY,
                intent.getStringExtra("place_name"), rating1, start_price.trim(), end_price.trim(), property_tye, reser);
        call.enqueue(new Callback<PlaceListModel>() {
            @Override
            public void onResponse(Call<PlaceListModel> call, Response<PlaceListModel> response) {

                shimmer_list_container.stopShimmerAnimation();
                shimmer_list_container.setVisibility(View.GONE);
                rv_place.setVisibility(View.VISIBLE);
                // dialog.stopProgressDialog();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true) {

                        if (response.body().getLocationProperties().size() > 0) {
                            rv_place.setVisibility(View.VISIBLE);

                            tv_no_data.setVisibility(View.GONE);
                            property_type.addAll(response.body().getPropertyType());
                            rv_place.setAdapter(new PlaceListAdapter(context, response.body().getLocationProperties()));
                        } else {
                            tv_no_data.setVisibility(View.VISIBLE);
                            rv_place.setVisibility(View.GONE);
                        }

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
    // home search APi

    private void HomeSearchApi() {
        shimmer_list_container.startShimmerAnimation();

        // dialog.progressDialog(context);
        Call<HomeSearchModel> call = Rest.getRetrofit().homePageSearch(ApiConstents.SECRET_KEY,
                intent.getStringExtra("locationId"),
                intent.getStringExtra("DateFron"), intent.getStringExtra("DateTo"),
                intent.getStringExtra("children"), intent.getStringExtra("adults"));

        call.enqueue(new Callback<HomeSearchModel>() {
            @Override
            public void onResponse(Call<HomeSearchModel> call, Response<HomeSearchModel> response) {

                shimmer_list_container.stopShimmerAnimation();
                shimmer_list_container.setVisibility(View.GONE);
                rv_place.setVisibility(View.VISIBLE);
                // dialog.stopProgressDialog();
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("true")) {

                        if (response.body().getLocationProperties().size() > 0) {
                            rv_place.setVisibility(View.VISIBLE);
                            tv_no_data.setVisibility(View.GONE);

                            rv_place.setAdapter(new HomeSearchAdapter(context, response.body().getLocationProperties()));
                        } else {
                            tv_no_data.setVisibility(View.VISIBLE);
                            rv_place.setVisibility(View.GONE);
                        }

                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<HomeSearchModel> call, Throwable t) {
                dialog.stopProgressDialog();

            }
        });
    }


}
