package com.cinqueterreriveria.apis;

import com.cinqueterreriveria.models.AccountBookingModel;
import com.cinqueterreriveria.models.BlogCategoryModel;
import com.cinqueterreriveria.models.ContactUsModel;
import com.cinqueterreriveria.models.DashboardModel;
import com.cinqueterreriveria.models.EditProfileModel;
import com.cinqueterreriveria.models.FAQsModel;
import com.cinqueterreriveria.models.HomeSearchModel;
import com.cinqueterreriveria.models.HowToReachDetailModel;
import com.cinqueterreriveria.models.LuxuryCollectionModel;
import com.cinqueterreriveria.models.PDFModel;
import com.cinqueterreriveria.models.PaymentModel;
import com.cinqueterreriveria.models.PlaceListModel;
import com.cinqueterreriveria.models.ProfileModel;
import com.cinqueterreriveria.models.RegisterModel;
import com.cinqueterreriveria.models.SearchDealModel;
import com.cinqueterreriveria.models.SinglePlaceDetailModel;
import com.cinqueterreriveria.models.UpdatePhotoModel;
import com.cinqueterreriveria.models.UserFAQModel;
import com.cinqueterreriveria.models.WhatToDoDetailModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
                                   @Field("name") String name,
                                   @Field("rating") String rating,
                                   @Field("start_price") String start_price,
                                   @Field("end_price") String end_price,
                                   @Field("property_type") String property_type,
                                   @Field("status") String status);
   /* @FormUrlEncoded
    @POST("locationProperties/")
    Call<PlaceListModel> placeList(@Query("secret_key") String secret_key,
                                   @Field("name") String name,
                                   @Field("rating") String rating,
                                  *//* @Field("start_price") String start_price,
                                   @Field("end_price") String end_price,*//*
                                   @Field("property_type") String property_type,
                                   @Field("status") String status);*/

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
    @POST("blogDetail/")
    Call<WhatToDoDetailModel> blogDetail(@Query("secret_key") String secret_key,
                                         @Field("name") String url);

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

    // blog according to categories
    @FormUrlEncoded
    @POST("blogList/")
    Call<BlogCategoryModel> blogCategory(@Query("secret_key") String secret_key,
                                         @Field("category") String category);

    // profile Api
    @FormUrlEncoded
    @POST("profileDetail/")
    Call<ProfileModel> profile(@Query("secret_key") String secret_key,
                               @Field("userId") String userId);

    // edit profile
    @FormUrlEncoded
    @POST("editProfile/")
    Call<EditProfileModel> editProfile(@Query("secret_key") String secret_key,
                                       @Field("firstname") String firstname,
                                       @Field("lastname") String lastname,
                                       @Field("email") String email,
                                       @Field("phonenumber") String phonenumber,
                                       @Field("address") String address,
                                       @Field("city") String city,
                                       @Field("country") String country,
                                       @Field("userId") String userId);

    // account FAQ api
    @POST("userFaq/")
    Call<UserFAQModel> accountFAQ(@Query("secret_key") String secret_key);

    // acccount Bookings Api
    @FormUrlEncoded
    @POST("userBookings/")
    Call<AccountBookingModel> bookingList(@Query("secret_key") String secret_key,
                                          @Field("userId") String userId,
                                          @Field("type") String type);

    // search  deal api
    @FormUrlEncoded
    @POST("searchDealNow/")
    Call<SearchDealModel> seachDealNow(@Query("secret_key") String secret_key,
                                       @Field("check_in_date") String check_in_date,
                                       @Field("check_out_date") String check_out_date,
                                       @Field("no_of_person") String no_of_person,
                                       @Field("property_id") String property_id,
                                       @Field("product_id") String product_id);

    // booking payment
    @FormUrlEncoded
    @POST("bookingPayment/")
    Call<PaymentModel> doPayment(@Query("secret_key") String secret_key,
                                 @Field("property_price") String property_price,
                                 @Field("property_name") String property_name,
                                 @Field("p_guestFirstName") String p_guestFirstName,
                                 @Field("p_guestLastName") String p_guestLastName,
                                 @Field("p_guestEmail") String p_guestEmail,
                                 @Field("p_guestPhoneNumber") String p_guestPhoneNumber,
                                 @Field("p_messageToOwner") String p_messageToOwner,
                                 @Field("p_guestAddressLine1") String p_guestAddressLine1,
                                 @Field("p_guestAddressLine2") String p_guestAddressLine2,
                                 @Field("p_guestAddressCity") String p_guestAddressCity,
                                 @Field("p_guestAddressState") String p_guestAddressState,
                                 @Field("p_guestAddressZip") String p_guestAddressZip,
                                 @Field("p_guestAddressCountry") String p_guestAddressCountry,
                                 @Field("p_no_of_personn") String p_no_of_personn,
                                 @Field("p_date_start") String p_date_start,
                                 @Field("p_date_end") String p_date_end,
                                 @Field("sepa_email") String sepa_email,
                                 @Field("payment_type") String payment_type,
                                 @Field("booking_price") String booking_price,
                                 @Field("client_price") String client_price,
                                 @Field("remaining_amount") String remaining_amount,
                                 @Field("cleaningPrice") String cleaningPrice,
                                 @Field("nights") String nights,
                                 @Field("card_number") String card_number,
                                 @Field("card_exp_month") String card_exp_month,
                                 @Field("card_exp_year") String card_exp_year,
                                 @Field("cvc") String cvc);

    // update pic APi
    @Multipart
    @POST("updateProfileImage/")
    Call<UpdatePhotoModel> userPicture(@Query("secret_key") String secret_key,
                                       @Part("userId") RequestBody userId,
                                       @Part MultipartBody.Part file);

    // home page search APi is here
    @FormUrlEncoded
    @POST("homePageSearch/")
    Call<HomeSearchModel> homePageSearch(@Query("secret_key") String secret_key,
                                         @Field("Locationid") String Locationid,
                                         @Field("DateFron") String DateFron,
                                         @Field("DateTo") String DateTo,
                                         @Field("children") String children,
                                         @Field("adults") String adults);





}
