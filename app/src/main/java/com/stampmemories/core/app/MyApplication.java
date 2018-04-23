package com.stampmemories.core.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.provider.Settings;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.stampmemories.core.model.CountryData;
import com.stampmemories.helper.AppPreference;
import com.stampmemories.sectioned.general_information.UserModel;

import java.util.ArrayList;

import static com.stampmemories.core.app.AppKey.USER_DATA;
import static com.stampmemories.core.app.CommonFunction.loadJSONFromAsset;
import static com.stampmemories.core.app.CommonFunction.setUser;


/**
 * Created by AppsterBiz on 02-Feb-18
 */

public class MyApplication extends Application {
    public static boolean isUserProviderNow = false;
    public static UserModel userModel = new UserModel();
    public static ArrayList<CountryData> countryList = new ArrayList<>();
    @NonNull
    private Gson gson = new Gson();

    @Override
    public void onCreate() {
        super.onCreate();
        @SuppressLint("HardwareIds") String androidDeviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        if (AppPreference.getInstance(getApplicationContext()).getString(AppKey.DEVICE_TOKEN).isEmpty()) {
            AppPreference.getInstance(getApplicationContext()).putString(AppKey.DEVICE_TOKEN, androidDeviceId);
        }
        userModel = gson.fromJson(AppPreference.getInstance(this).getString(USER_DATA), UserModel.class);
        if (userModel != null)
            setUser(userModel);
        JsonObject jsonObject = new JsonParser().parse(loadJSONFromAsset(this, "countryCodes.json")).getAsJsonObject();

        countryList = gson.fromJson(jsonObject.getAsJsonArray("country").toString(),
                new TypeToken<ArrayList<CountryData>>() {
                }.getType());
    }
}
