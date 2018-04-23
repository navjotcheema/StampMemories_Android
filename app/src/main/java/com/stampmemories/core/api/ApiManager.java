package com.stampmemories.core.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.JsonObject;
import com.stampmemories.R;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.helper.Alert;
import com.stampmemories.helper.AppPreference;
import com.stampmemories.interfaces.OnApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by AppsterBiz on 03/03/18
 */

public class ApiManager implements Callback<JsonObject> {
    private static final String CONTENT_TYPE = "application/json";
    private static ProgressDialog progressDialog;
    private static Activity mContext;
    @Nullable
    private static Call<JsonObject> call = null;
    private static OnApiResponse mCallBack;
    private static AppConstants.API_MODE REQUESTAPIMode;

    @Override
    public void onResponse(Call<JsonObject> call, @NonNull Response<JsonObject> response) {
//        Activity activity = (Activity) mContext;
        if (mContext != null) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
            if (response != null && response.isSuccessful()) {
                if (response.code() == 200) {
                    if (response.body() != null) {

                        if (response.body().get("status").getAsInt() == 200)
                            ApiManager.mCallBack.onApiSuccess(REQUESTAPIMode, response.body());
                        else
                            ApiManager.mCallBack.onApiFailed(REQUESTAPIMode, response.body().get("status").getAsInt(),
                                    response.body().get("message").getAsString());
                    }
                } else
                    Alert.showAlert(mContext, mContext.getString(R.string.failed), response.message());
            } else
                Alert.showAlert(mContext, mContext.getString(R.string.error), response.message());
        }
    }

    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
//        Activity activity = (Activity) mContext;
        if (mContext != null) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                Alert.showAlert(mContext, mContext.getString(R.string.error), mContext.getString(R.string.server_not_responding));
            }
        }
    }

    public static void dismissDialogIfRunning() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public static void cancelCall() {
        if (call != null && !call.isCanceled())
            call.cancel();
    }

    //sendAppRequest to call api manager from here,,,,
    // Multipart Api manager will give us the response
    // and handler
    public static synchronized void requestApi(@NonNull Activity activity, OnApiResponse mCallBack, @NonNull AppConstants.API_MODE REQUESTAPIMode,
                                               JsonObject jsonObject) {
        ApiManager.mCallBack = mCallBack;
        ApiManager.mContext = activity;
        ApiManager.REQUESTAPIMode = REQUESTAPIMode;
        try {
            if (request(activity))
                new ApiManager().onCall(REQUESTAPIMode, jsonObject);
        } catch (Exception e) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
            Alert.showAlert(mContext, mContext.getString(R.string.error), e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean request(@NonNull Activity mContext) {
        if (AppPreference.getInstance(mContext).isInternetAvailable(mContext)) {
            cancelCall();
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage(mContext.getString(R.string.please_wait));
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            if (progressDialog != null)
                progressDialog.show();
            return true;
        } else {
            Alert.showAlert(mContext, mContext.getString(R.string.alert), mContext.getString(R.string.no_internet));
        }
        return false;
    }

    private void onCall(@NonNull AppConstants.API_MODE mode, JsonObject jsonObject) {
        try {
            switch (mode) {
                case LOGIN:
                    call = RestClient.callApi().login(jsonObject);
                    break;
                case SOCIAL_LOGIN:
                    call = RestClient.callApi().login(jsonObject);
                    break;
                case FORGOT_PASSWORD:
                    call = RestClient.callApi().forgotPassword(jsonObject);
                    break;
                case REGISTER:
                    call = RestClient.callApi().register(jsonObject);
                    break;
                case CUSTOMER_ROLE:
                    call = RestClient.callApi().customerRole(jsonObject);
                    break;
                case PROVIDER_ROLE:
                    call = RestClient.callApi().providerRole(jsonObject);
                    break;
                case UPDATE_GENERAL_INFORMATION:
                    call = RestClient.callApi().updateGenarlInfo(jsonObject);
                    break;
                case REQUEST_OTP:
                    call = RestClient.callApi().requestOTP(jsonObject);
                    break;
                case VERIFY_OTP:
                    call = RestClient.callApi().verifyOTP(jsonObject);
                    break;
            }

            if (call != null)
                call.enqueue(this);

        } catch (Exception e) {
            if (progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
            Alert.showAlert(mContext, mContext.getString(R.string.error), e.getMessage());
            e.printStackTrace();
        }
    }


}
