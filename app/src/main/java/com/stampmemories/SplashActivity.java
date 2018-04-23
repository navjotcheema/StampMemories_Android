package com.stampmemories;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.stampmemories.core.app.AppKey;
import com.stampmemories.helper.Alert;
import com.stampmemories.helper.AppPreference;
import com.stampmemories.sectioned.dashboard.Dashboard;
import com.stampmemories.sectioned.general_information.UserModel;
import com.stampmemories.sectioned.login.LoginActivity;

import static com.stampmemories.core.app.AppKey.USER_DATA;
import static com.stampmemories.core.app.MyApplication.userModel;

/**
 * Created by AppsterBiz on 21-Mar-18
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                initialize();
            }
        }, 3000);
    }

    private void initialize() {
        if (AppPreference.getInstance(this).isInternetAvailable(this)) {
            if (AppPreference.getInstance(this).getBoolean(AppKey.IS_LOGGED_IN)) {
                userModel = new Gson().fromJson(AppPreference.getInstance(this).getString(USER_DATA), UserModel.class); // load user data, if logged in
                startActivity(new Intent(SplashActivity.this, Dashboard.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        } else {
            Alert.showAlert(this, "No internet", getString(R.string.no_internet));
        }

    }
}
