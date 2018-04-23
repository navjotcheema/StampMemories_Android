package com.stampmemories.core.api;


import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RestClient {

    private static final String BASE_URL = "https://www.stampmemoriesdev.com/api/";

    @NonNull
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS);


    static EndPointAPI callApi() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        return retrofit.create(EndPointAPI.class);
    }

}