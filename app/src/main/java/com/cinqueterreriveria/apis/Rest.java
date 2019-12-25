package com.cinqueterreriveria.apis;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.cinqueterreriveria.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {

    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    public static ApiInterface getRetrofit() {
        ApiInterface client = new Retrofit.Builder()
                .baseUrl(ApiConstents.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(ApiInterface.class);
        return client;
       /* return new Retrofit.Builder()
                .baseUrl(ApplicationConstants.BASE_URL)
                .client(getUnsafeOkHttpClient())
                .build();*/
    }


}
