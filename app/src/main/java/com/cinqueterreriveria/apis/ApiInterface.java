package com.cinqueterreriveria.apis;

import com.cinqueterreriveria.models.ContactUsModel;
import com.cinqueterreriveria.models.DashboardModel;
import com.cinqueterreriveria.models.FAQsModel;
import com.cinqueterreriveria.models.HowToReachDetailModel;
import com.cinqueterreriveria.models.LuxuryCollectionModel;
import com.cinqueterreriveria.models.PDFModel;
import com.cinqueterreriveria.models.PlaceListModel;
import com.cinqueterreriveria.models.RegisterModel;
import com.cinqueterreriveria.models.SinglePlaceDetailModel;
import com.cinqueterreriveria.models.WhatToDoDetailModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("registerUser/")
    Call<RegisterModel> register(@Query("secret_key") String secret_key,
                                 @Query("email") String email,
                                 @Query("password") String password);

    @POST("userLogin/")
    Call<RegisterModel> login(@Query("secret_key") String secret_key,
                              @Query("email") String email,
                              @Query("password") String password);

    @FormUrlEncoded
    @POST("homePage/")
    Call<DashboardModel> dashboard(@Query("secret_key") String secret_key,
                                   @Field("limit") String limit);

    @FormUrlEncoded
    @POST("locationProperties/")
    Call<PlaceListModel> placeList(@Query("secret_key") String secret_key,
                                   @Field("name") String name);

    @FormUrlEncoded
    @POST("whatToDoDetail/")
    Call<WhatToDoDetailModel> whatToDoDetails(@Query("secret_key") String secret_key,
                                              @Field("name") String name);

    @FormUrlEncoded
    @POST("howToReachDetail/")
    Call<HowToReachDetailModel> howToReachDetails(@Query("secret_key") String secret_key,
                                                  @Field("name") String name);

    @POST("contactUs/")
    Call<ContactUsModel> contactUs(@Query("secret_key") String secret_key);

    @FormUrlEncoded
    @POST("serviceMenu/")
    Call<LuxuryCollectionModel> luxuryCollection(@Query("secret_key") String secret_key,
                                                 @Field("url") String url);

    @FormUrlEncoded
    @POST("serviceMenu/")
    Call<WhatToDoDetailModel> servicesDetail(@Query("secret_key") String secret_key,
                                             @Field("url") String url);

    @FormUrlEncoded
    @POST("serviceMenu/")
    Call<PDFModel> pdfDetail(@Query("secret_key") String secret_key,
                             @Field("url") String url);

    @FormUrlEncoded
    @POST("propertyDetail/")
    Call<SinglePlaceDetailModel> singlePlaceDetail(@Query("secret_key") String secret_key,
                                                   @Field("name") String name);

    // Faq api

    @POST("faq/")
    Call<FAQsModel> FAQs(@Query("secret_key") String secret_key);

}
