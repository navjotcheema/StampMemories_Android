package com.stampmemories.core.api;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.stampmemories.core.app.AppKey;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EndPointAPI {

    @NonNull
    @POST(AppKey.REGISTER)
    Call<JsonObject> register(@Body JsonObject jsonObject);

    @NonNull
    @POST(AppKey.LOGIN)
    Call<JsonObject> login(@Body JsonObject jsonObject);

    @NonNull
    @POST(AppKey.FORGOT_PASSWORD)
    Call<JsonObject> forgotPassword(@Body JsonObject jsonObject);

    @NonNull
    @POST(AppKey.CUSTOMER_ROLE)
    Call<JsonObject> customerRole(@Body JsonObject jsonObject);

    @NonNull
    @POST(AppKey.PROVIDER_ROLE)
    Call<JsonObject> providerRole(@Body JsonObject jsonObject);

    @NonNull
    @POST(AppKey.REQUEST_OTP)
    Call<JsonObject> requestOTP(@Body JsonObject jsonObject);

    @NonNull
    @POST(AppKey.VERIFY_OTP)
    Call<JsonObject> verifyOTP(@Body JsonObject jsonObject);

    @NonNull
    @POST(AppKey.UPDATE_PROFILE)
    Call<JsonObject> updateGenarlInfo(@Body JsonObject jsonObject);
}
