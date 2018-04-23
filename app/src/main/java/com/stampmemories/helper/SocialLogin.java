package com.stampmemories.helper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AppsterBiz on 16-Jan-18.
 */

public class SocialLogin {
    @Nullable
    public Bundle getFacebookData(@NonNull JSONObject object) {
        Log.e("facebook data", object.toString());
        try {
            Bundle FBbundle = new Bundle();
            String id = object.getString("id");

            FBbundle.putString("idFacebook", id);

            if (object.has("email"))
                FBbundle.putString("email",
                        object.getString("email"));
            if (object.has("first_name"))
                FBbundle.putString("first_name",
                        object.getString("first_name"));
            if (object.has("last_name"))
                FBbundle.putString("last_name",
                        object.getString("last_name"));
//            Log.e("FB_DATA_login", FBbundle.getString("idFacebook") + "//" + FBbundle.getString("email"));
            return FBbundle;
        } catch (JSONException e) {
            Log.e("ERROR_PARSING", "Error parsing JSON");
        }
        return null;
    }
}
