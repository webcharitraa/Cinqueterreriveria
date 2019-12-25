package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
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
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.models.PlaceListModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceListActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = this;
    RecyclerView rv_place;
    LinearLayout ll_back;
    TextView tv_app_bar_title;
    Intent intent;
    TransparentDialog dialog=new TransparentDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);


        initUis();

    }

    private void initUis()
    {
        intent=getIntent();
        ll_back=findViewById(R.id.ll_back);
        rv_place = findViewById(R.id.rv_place);
        tv_app_bar_title = findViewById(R.id.tv_app_bar_title);
        tv_app_bar_title.setVisibility(View.VISIBLE);
        rv_place.setLayoutManager(new LinearLayoutManager(context));
        String text=intent.getStringExtra("place_name").substring(0,1).toUpperCase() + intent.getStringExtra("place_name").substring(1);
        tv_app_bar_title.setText(text);
        ll_back.setOnClickListener(this);


        placeListApi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    private void placeListApi()
    {
        dialog.progressDialog(context);
        Call<PlaceListModel> call= Rest.getRetrofit().placeList(ApiConstents.SECRET_KEY,
                intent.getStringExtra("place_name") );
        call.enqueue(new Callback<PlaceListModel>() {
            @Override
            public void onResponse(Call<PlaceListModel> call, Response<PlaceListModel> response) {
                dialog.stopProgressDialog();
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess() == true)
                    {
                        rv_place.setAdapter(new PlaceListAdapter(context,response.body().getLocationProperties(),
                                intent.getStringExtra("place_name")));
                    }
                    else {

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
