package com.stampmemories.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.stampmemories.core.app.AppConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by cis on 21/12/16
 */

public class AppPreference {
    private boolean status = false;

    //my preference class vars
    private static AppPreference appPreference;
    private final SharedPreferences sharedPreferences;

    private AppPreference(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstants.APP_NAME, Context.MODE_PRIVATE);
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static AppPreference getInstance(@NonNull Context context) {
        if (appPreference == null)
            appPreference = new AppPreference(context);

        return appPreference;
    }

    public void removeKey(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void putLong(String key, long val) {
        sharedPreferences.edit().putLong(key, val).apply();
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public void putString(String key, String val) {
        if (TextUtils.isEmpty(val))
            sharedPreferences.edit().putString(key, "").apply();
        else {
            sharedPreferences.edit().putString(key, val).apply();
        }
    }

    @Nullable
    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void putBoolean(String key, boolean val) {
        sharedPreferences.edit().putBoolean(key, val).apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public boolean isKeyExist(String key) {
        return sharedPreferences.contains(key);
    }

    public void showToast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public void putInt(String key, int val) {
        sharedPreferences.edit().putInt(key, val).apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void clearPreferences() {
        sharedPreferences.edit().clear().apply();
    }

    public boolean isOnline() {

        new android.os.Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    int timeoutMs = 1500;
                    Socket sock = new Socket();
                    SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

                    sock.connect(sockaddr, timeoutMs);
                    sock.close();

                    status = true;
                } catch (IOException e) {
                    status = false;
                }

            }
        });
        return status;
    }

    public boolean isInternetAvailable(@NonNull Activity context) {
        NetworkInfo activeNetworkInfo = null;
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}