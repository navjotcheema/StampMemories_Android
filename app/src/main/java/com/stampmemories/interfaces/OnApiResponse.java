package com.stampmemories.interfaces;

import com.google.gson.JsonObject;
import com.stampmemories.core.app.AppConstants;

/**
 * Created by AppsterBiz on 08-Mar-18
 */

public interface OnApiResponse {
    void onApiSuccess(AppConstants.API_MODE mode, JsonObject response);

    void onApiFailed(AppConstants.API_MODE mode, int statusCode, String response);
}
