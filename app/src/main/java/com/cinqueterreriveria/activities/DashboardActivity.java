package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.AccomodationAdapter;
import com.cinqueterreriveria.adapters.BookingUniqueExperienceAdapter;
import com.cinqueterreriveria.adapters.DashboardViewPagerAdapter;
import com.cinqueterreriveria.adapters.ExperienceAdapter;
import com.cinqueterreriveria.adapters.NonSwipeableViewPager;
import com.cinqueterreriveria.adapters.PlacesAdapter;
import com.cinqueterreriveria.adapters.ServicesAdapter;
import com.cinqueterreriveria.adapters.SpacesItemDecoration;
import com.cinqueterreriveria.adapters.WhatToDoAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.common.PrefStore;
import com.cinqueterreriveria.models.DashboardModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class DashboardActivity extends AppCompatActivity
        implements View.OnClickListener, OnMapReadyCallback {

    NavigationView navigation_view;
    ImageView iv_menu, iv_dashboard_back, iv_search, iv_dashboard_banner, profile_image;
    ShimmerFrameLayout shimmer_view_container;
    View v_appbar;
    double lat, lng;
    DrawerLayout drawer_layout;
    TextView tv_accomodation, tv_experience, tv_services, tv_banner_title, tv_banner_description,
            tv_blog, tv_contact_us, tv_faq, tv_logout, tv_MyAccount, tv_profile, tv_cinque;
    RecyclerView rv_name_of_places, rv_book_uniques_experience, rv_services,
            rv_what_to_do, rv_accomodation, rv_experience;
    Context context = this;
    TabLayout tab_layout;
    NonSwipeableViewPager viewpager;
    private GoogleMap mMap;
    Animation animSlideDown, animSlideUp;
    AccomodationAdapter accomodationAdapter;
    ExperienceAdapter experienceAdapter;
    ServicesAdapter servicesAdapter;
    PrefStore prefStore;
    TransparentDialog dialog = new TransparentDialog();
    public static List<DashboardModel.Blog> blogsList;
    public static List<DashboardModel.Video> videosList;
    public static List<DashboardModel.HowReach> howReachList;
    String[] places = {"MONTEROSSO", "MANAROLA", "PORTOVENERE", "VERNAZZA", "LA SPEZIA"};
    Spinner search_place_spinner;
    LinearLayout ll_search_box, rl_dashboard_appbar, ll_lay;
    ArrayList<Integer> personImages = new ArrayList<>(Arrays.asList(R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy, R.drawable.dummy));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        initUis();
    }

    void initUis() {
        blogsList = new ArrayList();
        videosList = new ArrayList();
        howReachList = new ArrayList();

        prefStore = new PrefStore(this);
        navigation_view = findViewById(R.id.navigation_view);
        rv_name_of_places = findViewById(R.id.rv_name_of_places);
        rv_book_uniques_experience = findViewById(R.id.rv_book_uniques_experience);
        rv_what_to_do = findViewById(R.id.rv_what_to_do);
        iv_menu = findViewById(R.id.iv_menu);
        drawer_layout = findViewById(R.id.drawer_layout);
        iv_dashboard_back = findViewById(R.id.iv_dashboard_back);
        rv_accomodation = findViewById(R.id.rv_accomodation);
        tv_accomodation = findViewById(R.id.tv_accomodation);
        rv_experience = findViewById(R.id.rv_experience);
        tv_experience = findViewById(R.id.tv_experience);
        tv_services = findViewById(R.id.tv_services);
        rv_services = findViewById(R.id.rv_services);
        tv_blog = findViewById(R.id.tv_blog);
        tv_contact_us = findViewById(R.id.tv_contact_us);
        tv_faq = findViewById(R.id.tv_faq);
        tab_layout = findViewById(R.id.tab_layout);
        viewpager = findViewById(R.id.viewpager);
        tv_logout = findViewById(R.id.tv_logout);
        rl_dashboard_appbar = findViewById(R.id.rl_dashboard_appbar);
        iv_search = findViewById(R.id.iv_search);
        ll_search_box = findViewById(R.id.ll_search_box);
        v_appbar = findViewById(R.id.v_appbar);
        profile_image = findViewById(R.id.profile_image);
        search_place_spinner = findViewById(R.id.search_place_spinner);
        tv_MyAccount = findViewById(R.id.tv_MyAccount);
        tv_profile = findViewById(R.id.tv_profile);
        iv_dashboard_banner = findViewById(R.id.iv_dashboard_banner);
        tv_banner_title = findViewById(R.id.tv_banner_title);
        tv_banner_description = findViewById(R.id.tv_banner_description);
        shimmer_view_container = findViewById(R.id.shimmer_view_container);
        tv_cinque = findViewById(R.id.tv_cinque);
        ll_lay = findViewById(R.id.ll_lay);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rv_book_uniques_experience.setHasFixedSize(true);

        rv_name_of_places.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        rv_accomodation.setLayoutManager(new LinearLayoutManager(context));
        rv_experience.setLayoutManager(new LinearLayoutManager(context));
        rv_services.setLayoutManager(new LinearLayoutManager(context));
        rv_what_to_do.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


        //This will for default android divider
        rv_book_uniques_experience.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)); // set LayoutManager to RecyclerView


        //rv_book_uniques_experience.addItemDecoration(decoration);


        servicesAdapter = new ServicesAdapter(context);

        rv_services.setAdapter(servicesAdapter);

        iv_menu.setOnClickListener(this);
        iv_dashboard_back.setOnClickListener(this);
        tv_accomodation.setOnClickListener(this);
        tv_experience.setOnClickListener(this);
        tv_services.setOnClickListener(this);
        tv_blog.setOnClickListener(this);
        tv_contact_us.setOnClickListener(this);
        tv_faq.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
        iv_search.setOnClickListener(this);
        profile_image.setOnClickListener(this);
        tv_MyAccount.setOnClickListener(this);
        tv_profile.setOnClickListener(this);

        //rv_name_of_places.setAdapter(new PlacesAdapter(context));

        /*setupViewPager(viewpager);
         tab_layout.setupWithViewPager(viewpager);*/

        //DashboardViewPagerAdapter viewPagerAdapter = new DashboardViewPagerAdapter(context, getSupportFragmentManager());

        //viewpager.setOffscreenPageLimit(3);
        //viewpager.setCurrentItem(0);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        viewpager.setPageMargin(pageMargin);

        //tab_layout.getSelectedTabPosition();
        //viewpager.setOffscreenPageLimit(0);
        tab_layout.setupWithViewPager(viewpager);

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, R.layout.item_search_spinner, places);
        search_place_spinner.setPrompt("Select");
        search_place_spinner.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.solid_orange_rectangle));
        search_place_spinner.setAdapter(a);

        dashboardApi();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide_down);

                v_appbar.setVisibility(View.GONE);
                ll_search_box.setVisibility(View.VISIBLE);
                ll_search_box.startAnimation(animSlideDown);
                iv_search.setVisibility(View.GONE);
                profile_image.setVisibility(View.VISIBLE);
                tv_cinque.setTextColor(getResources().getColor(R.color.white));
                rl_dashboard_appbar.setBackgroundColor(getResources().getColor(R.color.orange));
                iv_menu.setColorFilter(getResources().getColor(R.color.white));
                break;

            case R.id.profile_image:
                animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide_up);
                ll_search_box.startAnimation(animSlideUp);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v_appbar.setVisibility(View.VISIBLE);
                        ll_search_box.setVisibility(View.GONE);
                        iv_search.setVisibility(View.VISIBLE);
                        profile_image.setVisibility(View.GONE);
                        tv_cinque.setTextColor(getResources().getColor(R.color.dark_grey));

                        rl_dashboard_appbar.setBackgroundColor(getResources().getColor(R.color.white));
                        iv_menu.setColorFilter(getResources().getColor(R.color.orange));
                    }
                }, 300);
                break;

            case R.id.iv_menu:
                drawer_layout.openDrawer(GravityCompat.START);
                break;

            case R.id.iv_dashboard_back:
                drawer_layout.closeDrawer(GravityCompat.START);
                break;

            case R.id.tv_accomodation:
                tv_accomodation.setTextColor(getResources().getColor(R.color.white));
                tv_accomodation.setBackgroundColor(getResources().getColor(R.color.orange_dark));
                tv_experience.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_experience.setBackgroundColor(getResources().getColor(R.color.white));
                tv_services.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_services.setBackgroundColor(getResources().getColor(R.color.white));
                tv_blog.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_blog.setBackgroundColor(getResources().getColor(R.color.white));
                tv_contact_us.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_contact_us.setBackgroundColor(getResources().getColor(R.color.white));
                tv_MyAccount.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_MyAccount.setBackgroundColor(getResources().getColor(R.color.white));
                tv_faq.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_faq.setBackgroundColor(getResources().getColor(R.color.white));
                if (rv_accomodation.getVisibility() == View.VISIBLE) {
                    // rv_accomodation.setBackgroundColor(getResources().getColor(R.color.orange));
                    rv_accomodation.setVisibility(View.GONE);
                    //slideUp(rv_accomodation);
                } else {
                    accomodationAdapter.notifyDataSetChanged();
                    rv_accomodation.setVisibility(View.VISIBLE);
                    //slideUp(rv_accomodation);
                    rv_experience.setVisibility(View.GONE);
                    rv_services.setVisibility(View.GONE);
                    rv_accomodation.setBackgroundColor(getResources().getColor(R.color.orange));
                }
                break;

            case R.id.tv_experience:
                tv_accomodation.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_accomodation.setBackgroundColor(getResources().getColor(R.color.white));
                tv_services.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_services.setBackgroundColor(getResources().getColor(R.color.white));
                tv_blog.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_blog.setBackgroundColor(getResources().getColor(R.color.white));
                tv_contact_us.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_contact_us.setBackgroundColor(getResources().getColor(R.color.white));
                tv_MyAccount.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_MyAccount.setBackgroundColor(getResources().getColor(R.color.white));
                tv_faq.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_faq.setBackgroundColor(getResources().getColor(R.color.white));
                tv_experience.setTextColor(getResources().getColor(R.color.white));
                tv_experience.setBackgroundColor(getResources().getColor(R.color.orange_dark));
                if (rv_experience.getVisibility() == View.VISIBLE) {
                    // rv_accomodation.setBackgroundColor(getResources().getColor(R.color.orange));
                    rv_experience.setVisibility(View.GONE);
                } else {
                    experienceAdapter.notifyDataSetChanged();
                    rv_experience.setVisibility(View.VISIBLE);
                    rv_accomodation.setVisibility(View.GONE);
                    rv_services.setVisibility(View.GONE);
                    rv_experience.setBackgroundColor(getResources().getColor(R.color.orange));
                }
                break;

            case R.id.tv_services:
                tv_accomodation.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_accomodation.setBackgroundColor(getResources().getColor(R.color.white));
                tv_blog.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_blog.setBackgroundColor(getResources().getColor(R.color.white));
                tv_contact_us.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_contact_us.setBackgroundColor(getResources().getColor(R.color.white));
                tv_MyAccount.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_MyAccount.setBackgroundColor(getResources().getColor(R.color.white));
                tv_faq.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_faq.setBackgroundColor(getResources().getColor(R.color.white));
                tv_experience.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_experience.setBackgroundColor(getResources().getColor(R.color.white));
                tv_services.setTextColor(getResources().getColor(R.color.white));
                tv_services.setBackgroundColor(getResources().getColor(R.color.orange_dark));
                if (rv_services.getVisibility() == View.VISIBLE) {
                    // rv_accomodation.setBackgroundColor(getResources().getColor(R.color.orange));
                    rv_services.setVisibility(View.GONE);
                } else {
                    servicesAdapter.notifyDataSetChanged();
                    rv_services.setVisibility(View.VISIBLE);
                    rv_accomodation.setVisibility(View.GONE);
                    rv_experience.setVisibility(View.GONE);

                    rv_services.setBackgroundColor(getResources().getColor(R.color.orange));
                }
                break;

            case R.id.tv_blog:
                rv_services.setVisibility(View.GONE);
                rv_accomodation.setVisibility(View.GONE);
                rv_experience.setVisibility(View.GONE);
                tv_accomodation.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_accomodation.setBackgroundColor(getResources().getColor(R.color.white));
                tv_services.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_services.setBackgroundColor(getResources().getColor(R.color.white));
                tv_blog.setTextColor(getResources().getColor(R.color.white));
                tv_blog.setBackgroundColor(getResources().getColor(R.color.orange_dark));
                tv_contact_us.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_contact_us.setBackgroundColor(getResources().getColor(R.color.white));
                tv_MyAccount.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_MyAccount.setBackgroundColor(getResources().getColor(R.color.white));
                tv_faq.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_faq.setBackgroundColor(getResources().getColor(R.color.white));
                tv_experience.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_experience.setBackgroundColor(getResources().getColor(R.color.white));
                startActivity(new Intent(context, BlogsActivity.class));
                break;

            case R.id.tv_contact_us:
                rv_services.setVisibility(View.GONE);
                rv_accomodation.setVisibility(View.GONE);
                rv_experience.setVisibility(View.GONE);
                tv_accomodation.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_accomodation.setBackgroundColor(getResources().getColor(R.color.white));
                tv_services.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_services.setBackgroundColor(getResources().getColor(R.color.white));
                tv_blog.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_blog.setBackgroundColor(getResources().getColor(R.color.white));
                tv_contact_us.setTextColor(getResources().getColor(R.color.white));
                tv_contact_us.setBackgroundColor(getResources().getColor(R.color.orange_dark));
                tv_faq.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_faq.setBackgroundColor(getResources().getColor(R.color.white));
                tv_MyAccount.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_MyAccount.setBackgroundColor(getResources().getColor(R.color.white));
                tv_experience.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_experience.setBackgroundColor(getResources().getColor(R.color.white));
                startActivity(new Intent(context, ContactUsActivity.class));
                break;

            case R.id.tv_faq:
                rv_services.setVisibility(View.GONE);
                rv_accomodation.setVisibility(View.GONE);
                rv_experience.setVisibility(View.GONE);
                tv_accomodation.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_accomodation.setBackgroundColor(getResources().getColor(R.color.white));
                tv_services.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_services.setBackgroundColor(getResources().getColor(R.color.white));
                tv_blog.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_blog.setBackgroundColor(getResources().getColor(R.color.white));
                tv_contact_us.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_contact_us.setBackgroundColor(getResources().getColor(R.color.white));
                tv_MyAccount.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_MyAccount.setBackgroundColor(getResources().getColor(R.color.white));
                tv_faq.setTextColor(getResources().getColor(R.color.white));
                tv_faq.setBackgroundColor(getResources().getColor(R.color.orange_dark));
                tv_experience.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_experience.setBackgroundColor(getResources().getColor(R.color.white));
                break;

            case R.id.tv_MyAccount:
                rv_services.setVisibility(View.GONE);
                rv_accomodation.setVisibility(View.GONE);
                rv_experience.setVisibility(View.GONE);
                tv_accomodation.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_accomodation.setBackgroundColor(getResources().getColor(R.color.white));
                tv_services.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_services.setBackgroundColor(getResources().getColor(R.color.white));
                tv_blog.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_blog.setBackgroundColor(getResources().getColor(R.color.white));
                tv_contact_us.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_contact_us.setBackgroundColor(getResources().getColor(R.color.white));
                tv_MyAccount.setTextColor(getResources().getColor(R.color.white));
                tv_MyAccount.setBackgroundColor(getResources().getColor(R.color.orange_dark));
                tv_faq.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_faq.setBackgroundColor(getResources().getColor(R.color.white));
                tv_experience.setTextColor(getResources().getColor(R.color.dark_grey));
                tv_experience.setBackgroundColor(getResources().getColor(R.color.white));
                break;

            case R.id.tv_profile:
                startActivity(new Intent(context, MyAccountActivity.class));
                break;

            case R.id.tv_logout:
                prefStore.setBoolean("login_status", false);
                startActivity(new Intent(context, LoginActivity.class));
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawer_layout.closeDrawer(GravityCompat.START);
        rv_services.setVisibility(View.GONE);
        rv_accomodation.setVisibility(View.GONE);
        rv_experience.setVisibility(View.GONE);
        tv_accomodation.setTextColor(getResources().getColor(R.color.dark_grey));
        tv_accomodation.setBackgroundColor(getResources().getColor(R.color.white));
        tv_services.setTextColor(getResources().getColor(R.color.dark_grey));
        tv_services.setBackgroundColor(getResources().getColor(R.color.white));
        tv_blog.setTextColor(getResources().getColor(R.color.dark_grey));
        tv_blog.setBackgroundColor(getResources().getColor(R.color.white));
        tv_contact_us.setTextColor(getResources().getColor(R.color.dark_grey));
        tv_contact_us.setBackgroundColor(getResources().getColor(R.color.white));
        tv_faq.setTextColor(getResources().getColor(R.color.dark_grey));
        tv_faq.setBackgroundColor(getResources().getColor(R.color.white));
        tv_experience.setTextColor(getResources().getColor(R.color.dark_grey));
        tv_experience.setBackgroundColor(getResources().getColor(R.color.white));
        tv_MyAccount.setTextColor(getResources().getColor(R.color.dark_grey));
        tv_MyAccount.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng UCA = new LatLng(lat, lng);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.logo);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);

        mMap.addMarker(new MarkerOptions().position(UCA).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker))).showInfoWindow();
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMaxZoomPreference(300);
        float zoomLevel = 5.0f;
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UCA, zoomLevel));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA, zoomLevel));
    }

    void dashboardApi() {
        Call<DashboardModel> call = Rest.getRetrofit().dashboard(ApiConstents.SECRET_KEY, "");
        shimmer_view_container.startShimmerAnimation();
        // dialog.progressDialog(context);
        call.enqueue(new Callback<DashboardModel>() {
            @Override
            public void onResponse(Call<DashboardModel> call, Response<DashboardModel> response) {
                // dialog.stopProgressDialog();
                shimmer_view_container.stopShimmerAnimation();
                shimmer_view_container.setVisibility(View.GONE);
                ll_lay.setVisibility(View.VISIBLE);
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true) {

                        lat = Double.parseDouble(response.body().getLat());
                        lng = Double.parseDouble(response.body().getLong());
                        accomodationAdapter = new AccomodationAdapter(context, response.body().getLocation());
                        rv_accomodation.setAdapter(accomodationAdapter);

                        experienceAdapter = new ExperienceAdapter(context, response.body().getActivity());
                        rv_experience.setAdapter(experienceAdapter);
                        RequestOptions simpleOptions = new RequestOptions()
                                .centerCrop()

                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                        Glide.with(context).load(response.body().getBannerImage()).apply(simpleOptions).placeholder(R.drawable.placeholder).into(iv_dashboard_banner);
                        tv_banner_title.setText(response.body().getBannerTitle());
                        tv_banner_description.setText(response.body().getBannerSubTitle());
                        rv_name_of_places.setAdapter(new PlacesAdapter(context, response.body().getLocation()));
                        rv_what_to_do.setAdapter(new WhatToDoAdapter(context, response.body().getWhatToDo()));
                        rv_book_uniques_experience.setAdapter(new BookingUniqueExperienceAdapter(context, response.body().getActivity()));
                        blogsList.addAll(response.body().getBlogs());
                        videosList.addAll(response.body().getVideos());
                        howReachList.addAll(response.body().getHowReach());

                        viewpager.setAdapter(new DashboardViewPagerAdapter(getSupportFragmentManager()));
                        Log.d("TAG", "blogs " + blogsList.size());
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<DashboardModel> call, Throwable t) {
                //dialog.stopProgressDialog();
                shimmer_view_container.stopShimmerAnimation();
                shimmer_view_container.setVisibility(View.GONE);
                Toast.makeText(DashboardActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
