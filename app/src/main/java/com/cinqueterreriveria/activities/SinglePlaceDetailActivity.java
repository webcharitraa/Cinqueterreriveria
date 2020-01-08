package com.cinqueterreriveria.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.CollectionAdapter;
import com.cinqueterreriveria.adapters.CustomPagerFoeSinglePlace;
import com.cinqueterreriveria.adapters.HotelDetailAdapter;
import com.cinqueterreriveria.adapters.PlaceDetailViewPagerAdapter;
import com.cinqueterreriveria.adapters.SinglePlaceImageViewPagerAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.common.MySpannable;
import com.cinqueterreriveria.common.PrefStore;
import com.cinqueterreriveria.models.SearchDealModel;
import com.cinqueterreriveria.models.SinglePlaceDetailModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class SinglePlaceDetailActivity extends AppCompatActivity implements
        OnMapReadyCallback, View.OnClickListener {

    LinearLayout single_pager_dots, ll_single_detail;
    Context context = this;
    ViewPager single_place_viewPager;
    ShimmerFrameLayout shimmer_single_place_detail;
    SwipeRefreshLayout refresh;
    int[] images = {R.drawable.dummy, R.drawable.dummy, R.drawable.dummy};
    private ImageView[] ivArrayDotsPager;
    TabLayout place_detail_tabs;
    CustomPagerFoeSinglePlace place_detail_viewpager;
    FloatingActionButton fab;
    RecyclerView rv_collections, rv_details;
    GoogleMap mMap;
    Spinner deal_group_spinner;
    String date1, product_id, property_id;
    double lat, lng;
    PrefStore prefStore;
    AlertDialog dialog2;
    RatingBar ratingBar;
    SupportMapFragment mapFragment;
    TextView tv_single_place_title, tv_amount, tv_citra_code,
            tv_bathroom, tv_guest, tv_room,
            tv_description, tv_single_place_location, tv_property_type;
    Intent intent;
    RelativeLayout rl_single_place, rl_top;
    String groups_deal[], mStringArray[];
    String status = "check_in", property_name;
    public static String florePlanImage;
    public static JSONArray requiredDataArray;

    int value;
    CalendarView calender_filter;
    TransparentDialog transparentDialog = new TransparentDialog();
    public static ArrayList<String> amentiesList;
    public static ArrayList<String> groups;
    public static ArrayList<SinglePlaceDetailModel.Extra> ectraChargesList;
    public static ArrayList<SinglePlaceDetailModel.PropertyRate> propertyRates;
    public static ArrayList<SinglePlaceDetailModel.Nearby> nearByList;
    public static ArrayList<SinglePlaceDetailModel.Comment> reviewsList;
    public static JSONObject object;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_place_detail);

        initUis();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initUis() {
        intent = getIntent();
        prefStore = new PrefStore(context);
        amentiesList = new ArrayList<String>();
        ectraChargesList = new ArrayList();
        nearByList = new ArrayList();
        propertyRates = new ArrayList();
        reviewsList = new ArrayList();
        groups = new ArrayList();
        single_place_viewPager = findViewById(R.id.single_place_viewPager);
        single_pager_dots = findViewById(R.id.single_pager_dots);
        rv_collections = findViewById(R.id.rv_collections);
        rv_details = findViewById(R.id.rv_details);
        place_detail_tabs = findViewById(R.id.place_detail_tabs);
        place_detail_viewpager = findViewById(R.id.place_detail_viewpager);
        ratingBar = findViewById(R.id.ratingBar);
        refresh = findViewById(R.id.refresh);
        rl_single_place = findViewById(R.id.rl_single_place);
        rl_top = findViewById(R.id.rl_top);
        tv_single_place_title = findViewById(R.id.tv_single_place_title);
        tv_amount = findViewById(R.id.tv_amount);
        tv_citra_code = findViewById(R.id.tv_citra_code);
        tv_bathroom = findViewById(R.id.tv_bathroom);
        tv_guest = findViewById(R.id.tv_guest);
        tv_room = findViewById(R.id.tv_room);
        tv_description = findViewById(R.id.tv_description);
        tv_single_place_location = findViewById(R.id.tv_single_place_location);
        ll_single_detail = findViewById(R.id.ll_single_detail);
        shimmer_single_place_detail = findViewById(R.id.shimmer_single_place_detail);
        tv_property_type = findViewById(R.id.tv_property_type);

        fab = findViewById(R.id.fab);

        rv_collections.setLayoutManager(new GridLayoutManager(context, 3));
        rv_collections.setAdapter(new CollectionAdapter(context));

        rv_details.setLayoutManager(new LinearLayoutManager(context));

        setupPagerIndidcatorDots();

        ivArrayDotsPager[0].setImageResource(R.drawable.selected_dot);

        try {
            Field f = refresh.getClass().getDeclaredField("mCircleView");
            f.setAccessible(true);
            ImageView img = (ImageView) f.get(refresh);
            img.setAlpha(0.0f);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                finish();
                overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
            }
        });

        single_place_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ivArrayDotsPager.length; i++) {
                    ivArrayDotsPager[i].setImageResource(R.drawable.unselected_dot);
                }
                ivArrayDotsPager[position].setImageResource(R.drawable.selected_dot);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        place_detail_viewpager.setPageMargin(pageMargin);

        place_detail_tabs.setupWithViewPager(place_detail_viewpager);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fab.setOnClickListener(this);

        singledetailAPi();
    }

    private void setupPagerIndidcatorDots() {
        ivArrayDotsPager = new ImageView[images.length];
        for (int i = 0; i < ivArrayDotsPager.length; i++) {
            ivArrayDotsPager[i] = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            ivArrayDotsPager[i].setLayoutParams(params);
            ivArrayDotsPager[i].setImageResource(R.drawable.unselected_dot);
            //ivArrayDotsPager[i].setAlpha(0.4f);
            ivArrayDotsPager[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setAlpha(1);
                }
            });
            single_pager_dots.addView(ivArrayDotsPager[i]);
            single_pager_dots.bringToFront();
        }
    }

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {
        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });
    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned,
                                                                            final TextView tv,
                                                                            final int maxLine,
                                                                            final String spanableText,
                                                                            final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new MySpannable(false) {
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "Read Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 5, ".. Read More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);
        }
        return ssb;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //LatLng UCA = new LatLng(44.134660, 9.683730);
        LatLng UCA = new LatLng(lat, lng);
        BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.logo);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);

        mMap.addMarker(new MarkerOptions().position(UCA).title("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker))).showInfoWindow();

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.setMaxZoomPreference(300);
        float zoomLevel = 12.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UCA, zoomLevel));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UCA, 17));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                View view2 = LayoutInflater.from(context).inflate(R.layout.popup_filter, null);
                ImageView iv_cancel_filter = view2.findViewById(R.id.iv_cancel_filter);
                deal_group_spinner = view2.findViewById(R.id.deal_group_spinner);
                Button bt_search_deal = view2.findViewById(R.id.bt_search_deal);
                calender_filter = view2.findViewById(R.id.calender_filter);
                final LinearLayout ll_calender = view2.findViewById(R.id.ll_calender);
                final RelativeLayout rl_dia = view2.findViewById(R.id.rl_dia);
                final RelativeLayout rl_popup = view2.findViewById(R.id.rl_popup);
                final TextView et_check_in = view2.findViewById(R.id.et_check_in);
                final TextView tv_check_out = view2.findViewById(R.id.tv_check_out);
                builder2.setView(view2);
                ArrayAdapter<String> a = new ArrayAdapter<String>(this, R.layout.item_spinner, mStringArray);
                //deal_group_spinner.setPrompt("Select");
                deal_group_spinner.setAdapter(a);

                calender_filter.setMinDate(System.currentTimeMillis() - 1000);

                calender_filter.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                        month = month + 1;
                        date1 = day + "/" + month + "/" + year;
                        DateFormat outputFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.US);
                        DateFormat inputFormat = new SimpleDateFormat("dd/M/yyyy", Locale.US);

                        Date date = null;
                        try {
                            date = inputFormat.parse(date1);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String outputText = outputFormat.format(date);
                        Log.d("DATE", month + "");
                        if (status.equals("check_in")) {
                            et_check_in.setText(outputText);
                        }
                        if (status.equals("check_out")) {
                            tv_check_out.setText(outputText);
                        }
                        if (!et_check_in.getText().toString().equals("")) {
                            ll_calender.setVisibility(View.GONE);
                        }
                    }
                });
                rl_popup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ll_calender.getVisibility() == View.VISIBLE) {
                            ll_calender.setVisibility(View.GONE);
                        }
                    }
                });

                et_check_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setMargins(rl_dia, 0, 55, 0, 0);
                        // calender_filter.setMinDate(System.currentTimeMillis() - 1000);

                       /* LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(0, 45, 0, 0);
                        rl_dia.setLayoutParams(params);*/
                        if (ll_calender.getVisibility() == View.GONE) {
                            ll_calender.setVisibility(View.VISIBLE);
                            status = "check_in";
                        }
                    }
                });
                tv_check_out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setMargins(rl_dia, 0, 55, 0, 0);
                        // calender_filter.setMaxDate(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 6));

                       /* LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(0, 45, 0, 0);
                        rl_dia.setLayoutParams(params);*/
                        if (ll_calender.getVisibility() == View.GONE) {
                            ll_calender.setVisibility(View.VISIBLE);
                            status = "check_out";

                        }
                    }
                });

                final AlertDialog finalDialog = dialog2;
                iv_cancel_filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });
                bt_search_deal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (et_check_in.getText().toString().isEmpty()) {
                            Toast.makeText(SinglePlaceDetailActivity.this, "Please fill Check in time", Toast.LENGTH_SHORT).show();
                        } else if (tv_check_out.getText().toString().isEmpty()) {
                            Toast.makeText(SinglePlaceDetailActivity.this, "Please fill Check out time", Toast.LENGTH_SHORT).show();

                        } else {
                            searchDealNowAPi(et_check_in.getText().toString(),
                                    tv_check_out.getText().toString(), deal_group_spinner.getSelectedItem().toString());
                        }
                    }
                });

                dialog2 = builder2.create();

                dialog2.setCancelable(false);
                dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                // dialog2.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                dialog2.show();
                break;
        }
    }

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    private void singledetailAPi() {
        shimmer_single_place_detail.startShimmerAnimation();
        Call<SinglePlaceDetailModel> call = Rest.getRetrofit().singlePlaceDetail(ApiConstents.SECRET_KEY,
                intent.getStringExtra("slug"));

        call.enqueue(new Callback<SinglePlaceDetailModel>() {
            @Override
            public void onResponse(Call<SinglePlaceDetailModel> call, Response<SinglePlaceDetailModel> response) {
                if (response.isSuccessful()) {
                    lat = Double.parseDouble(response.body().getDetail().getLocation().getLat());
                    lng = Double.parseDouble(response.body().getDetail().getLocation().getLong());
                    shimmer_single_place_detail.stopShimmerAnimation();
                    shimmer_single_place_detail.setVisibility(View.GONE);
                    ll_single_detail.setVisibility(View.VISIBLE);
                    if (response.body().getSuccess() == true) {
                        ratingBar.setRating(Float.parseFloat(response.body().getDetail().getRating()));
                        mapFragment.getMapAsync(SinglePlaceDetailActivity.this);
                        property_name = response.body().getDetail().getTitle();
                        for (int i = 0; i < response.body().getDetail().getDetails().size(); i++) {
                            if (response.body().getDetail().getDetails().get(i).getName().equals("Max Guest")) {
                                value = Integer.parseInt(response.body().getDetail().getDetails().get(i).getValue());

                            } else {
                                value = 0;
                            }
                        }
                        for (int i = 1; i <= value; i++) {
                            groups.add(String.valueOf(i));
                        }

                        mStringArray = new String[groups.size()];
                        mStringArray = groups.toArray(mStringArray);


                        product_id = response.body().getDetail().getProductId();
                        property_id = response.body().getDetail().getPropertyId();

                        single_place_viewPager.setAdapter(new SinglePlaceImageViewPagerAdapter(context, response.body().getDetail().getGallery()));
                        tv_single_place_title.setText(response.body().getDetail().getTitle());
                        tv_property_type.setText(response.body().getDetail().getPropertyType());
                        tv_description.setText(Html.fromHtml(response.body().getDetail().getDescription()));
                        tv_single_place_location.setText(response.body().getDetail().getPropertyLocation());
                        tv_citra_code.setText("Citra Code:" + response.body().getDetail().getCitraCode());
                        tv_bathroom.setText("Bathrooms(" + response.body().getDetail().getIcons().getBathrooms() + ")");
                        tv_guest.setText("Guests(" + response.body().getDetail().getIcons().getGuests() + ")");
                        tv_room.setText("Rooms(" + response.body().getDetail().getIcons().getRooms() + ")");

                        rv_details.setAdapter(new HotelDetailAdapter(context, response.body().getDetail().getDetails()));
                        amentiesList.addAll(response.body().getDetail().getAmenties());
                        ectraChargesList.addAll(response.body().getDetail().getExtras());
                        nearByList.addAll(response.body().getDetail().getNearby());
                        reviewsList.addAll(response.body().getDetail().getComments());
                        florePlanImage = response.body().getDetail().getFloorPlanImage();
                        propertyRates.addAll(response.body().getDetail().getPropertyRates());
                        String our_rule = response.body().getDetail().getOurRules().toString();
                        try {

                            object = new JSONObject();

                            requiredDataArray = new JSONArray();
                            JSONObject data = new JSONObject();
                            data.put("Minimum Stay", response.body().getDetail().getOurRules().getMinimumStay());
                            data.put("Garbage Collection", response.body().getDetail().getOurRules().getGarbageCollection());
                            data.put("Smoking", response.body().getDetail().getOurRules().getSmoking());
                            data.put("Pets", response.body().getDetail().getOurRules().getPets());
                            data.put("Check-In Time", response.body().getDetail().getOurRules().getCheckInTime());
                            data.put("Check-Out Time", response.body().getDetail().getOurRules().getCheckOutTime());
                            requiredDataArray.put(data);
                            object.put("REQ_DATA", requiredDataArray);
                            Log.d("JSON", object.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        place_detail_viewpager.setAdapter(new PlaceDetailViewPagerAdapter(context, getSupportFragmentManager()));

                        //ratingBar.setRating(Float.parseFloat(intent.getStringExtra("rating")));

                        makeTextViewResizable(tv_description, 5, "Read More", true);

                    } else {
                        Toast.makeText(SinglePlaceDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SinglePlaceDetailModel> call, Throwable t) {
                Toast.makeText(SinglePlaceDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // search Deal APi is here
    private void searchDealNowAPi(final String et_check_in, final String check_out, final String no_of_persons) {
        Call<SearchDealModel> call = Rest.getRetrofit().seachDealNow(ApiConstents.SECRET_KEY,
                et_check_in, check_out, no_of_persons, property_id, product_id);
        transparentDialog.progressDialog(context);
        Log.e("TAG", "no_of_persons " + no_of_persons + " property_id " + property_id + " product_id " + product_id);
        call.enqueue(new Callback<SearchDealModel>() {
            @Override
            public void onResponse(Call<SearchDealModel> call, final Response<SearchDealModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess().equals("true")) {
                        transparentDialog.stopProgressDialog();
                        dialog2.dismiss();

                        AlertDialog dialog1 = null;
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                        View view2 = LayoutInflater.from(context).inflate(R.layout.popup_booking_detail, null);
                        Button bt_full_payment = view2.findViewById(R.id.bt_full_payment);
                        TextView tv_check_in = view2.findViewById(R.id.tv_check_in);
                        TextView tv_popup_check_out = view2.findViewById(R.id.tv_popup_check_out);
                        TextView tv_total_nights = view2.findViewById(R.id.tv_total_nights);
                        TextView tv_popup_price = view2.findViewById(R.id.tv_popup_price);
                        TextView tv_cleaning_fee = view2.findViewById(R.id.tv_cleaning_fee);
                        TextView tv_city_tax = view2.findViewById(R.id.tv_city_tax);
                        TextView tv_deposit = view2.findViewById(R.id.tv_deposit);
                        builder2.setView(view2);

                        tv_check_in.setText("Check In:- " + response.body().getDetails().getCheckInDate());
                        tv_popup_check_out.setText("Check Out:- " + response.body().getDetails().getCheckOutDate());
                        tv_total_nights.setText("Total Nights:- " + response.body().getDetails().getTotalNights());
                        tv_popup_price.setText("Price:- ₤" + response.body().getDetails().getPrice());
                        tv_cleaning_fee.setText("Cleaning fee:- " + response.body().getDetails().getCleaningFee());
                        tv_city_tax.setText("City tax:- " + response.body().getDetails().getCityTax());
                        bt_full_payment.setText("Full payment ₤" + response.body().getDetails().getFullPayment());
                        tv_deposit.setText(response.body().getDetails().getDeposit());

                        tv_deposit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                prefStore.saveString("property_price", String.valueOf(response.body().getDetails().getPropertyPrice()));
                                prefStore.saveString("property_name", property_name);
                                prefStore.saveString("no_of_persons", no_of_persons);
                                prefStore.saveString("start_date", et_check_in);
                                prefStore.saveString("end_date", check_out);
                                prefStore.saveString("payment_type", "partial");
                                prefStore.saveString("booking_price", String.valueOf(response.body().getDetails().getFullPayment()));
                                prefStore.saveString("client_price", String.valueOf(response.body().getDetails().getRemainingAmount()));
                                prefStore.saveString("cleaningPrice", String.valueOf(response.body().getDetails().getCleaningPrice()));
                                prefStore.saveString("nights", String.valueOf(response.body().getDetails().getTotalNights()));

                                startActivity(new Intent(SinglePlaceDetailActivity.this, PaymentTypeActivity.class));

                            }
                        });
                        bt_full_payment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                prefStore.saveString("property_price", String.valueOf(response.body().getDetails().getPropertyPrice()));
                                prefStore.saveString("property_name", property_name);
                                prefStore.saveString("no_of_persons", no_of_persons);
                                prefStore.saveString("start_date", et_check_in);
                                prefStore.saveString("end_date", check_out);
                                prefStore.saveString("payment_type", "full");
                                prefStore.saveString("booking_price", String.valueOf(response.body().getDetails().getFullPayment()));
                                prefStore.saveString("client_price", String.valueOf(response.body().getDetails().getMinDeposit()));
                                prefStore.saveString("remaining_amount", String.valueOf(response.body().getDetails().getRemainingAmount()));
                                prefStore.saveString("cleaningPrice", String.valueOf(response.body().getDetails().getCleaningPrice()));
                                prefStore.saveString("nights", String.valueOf(response.body().getDetails().getTotalNights()));

                                startActivity(new Intent(SinglePlaceDetailActivity.this, PaymentTypeActivity.class));
                            }
                        });
                        dialog1 = builder2.create();
                        dialog1.setCancelable(true);

                        dialog1.show();
                    } else {
                        transparentDialog.stopProgressDialog();
                        Toast.makeText(SinglePlaceDetailActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchDealModel> call, Throwable t) {
                transparentDialog.stopProgressDialog();
                Toast.makeText(SinglePlaceDetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
