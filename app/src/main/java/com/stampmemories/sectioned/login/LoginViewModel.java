package com.stampmemories.sectioned.login;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stampmemories.core.api.ApiManager;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.core.app.AppKey;
import com.stampmemories.helper.Alert;
import com.stampmemories.helper.AppPreference;
import com.stampmemories.interfaces.OnApiResponse;
import com.stampmemories.sectioned.dashboard.DashBoardViewModel;
import com.stampmemories.sectioned.dashboard.Dashboard;
import com.stampmemories.sectioned.forgotpassword.ForgotPasswordActivity;
import com.stampmemories.sectioned.general_information.UserModel;
import com.stampmemories.sectioned.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import br.com.ilhasoft.support.validation.Validator;

import static com.stampmemories.core.app.AppConstants.ERROR;
import static com.stampmemories.core.app.AppKey.DEVICE_TOKEN;
import static com.stampmemories.core.app.AppKey.DEVICE_TYPE;
import static com.stampmemories.core.app.AppKey.EMAIL;
import static com.stampmemories.core.app.AppKey.FIRST_NAME;
import static com.stampmemories.core.app.AppKey.IS_LOGGED_IN;
import static com.stampmemories.core.app.AppKey.LAST_NAME;
import static com.stampmemories.core.app.AppKey.LOGIN_TYPE;
import static com.stampmemories.core.app.AppKey.PASSWORD;
import static com.stampmemories.core.app.AppKey.PUBLIC_PROFILE;
import static com.stampmemories.core.app.AppKey.REMEMBER_ME;
import static com.stampmemories.core.app.AppKey.REMEMBER_USER_DATA;
import static com.stampmemories.core.app.AppKey.SOCIAL_ID;
import static com.stampmemories.core.app.AppKey.SOCIAL_TYPE;
import static com.stampmemories.core.app.AppKey.USER_DATA;
import static com.stampmemories.core.app.CommonFunction.setUser;
import static com.stampmemories.core.app.MyApplication.userModel;

/**
 * Created by AppsterBiz on 21-Mar-18
 */

public class LoginViewModel extends BaseObservable implements OnApiResponse {
    private Activity context;
    @NonNull
    public ObservableField<String> email = new ObservableField<>("");
    @NonNull
    public ObservableField<String> password = new ObservableField<>("");
    @NonNull
    private ObservableBoolean doRemember = new ObservableBoolean(false);
    private Validator validator;
    @Nullable
    private GoogleSignInOptions gso = null;
    @Nullable
    private GoogleSignInClient mGoogleSignInClient = null;
    @Nullable
    private LoginManager loginManager = null;
    @Nullable
    private CallbackManager callbackManager = null;
    @Nullable
    private String f_name = "", l_name = "", socialID = "", s_email = ""; // there are used to store values received from social account
    @NonNull
    private String social_type = "";


    LoginViewModel(Activity context, Validator validator) {
        this.context = context;
        this.validator = validator;
        initialize();
    }

    private void initialize() {
        if (AppPreference.getInstance(context).getBoolean(REMEMBER_ME)) {
            JsonObject jsonObject = new JsonParser().parse(AppPreference.getInstance(context).getString(REMEMBER_USER_DATA)).getAsJsonObject();
            setEmail(jsonObject.get(EMAIL).getAsString());
            setPassword(jsonObject.get(PASSWORD).getAsString());
            setDoRemember(true);
        }
        // setup for google Sign-in
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
        // setup for Facebook login
        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();
        if (loginManager != null)
            loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(@NonNull LoginResult loginResult) {
                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(@NonNull JSONObject object, GraphResponse response) {
                            try {
                                s_email = (object.has("email") ? object.getString("email") : "");
                                f_name = (object.has("first_name") ? object.getString("first_name") : "");
                                l_name = (object.has("last_name") ? object.getString("last_name") : "");
                                socialID = object.getString("id");
                                social_type = AppConstants.FB_LOGIN_TYPE;
                                doLogin(getSocialLoginRequestBody(), AppConstants.API_MODE.SOCIAL_LOGIN);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id, email,first_name,last_name"); // fetching these parameters
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(@NonNull FacebookException error) {
                    Log.e("facebook_error", error.getMessage());

                }
            });
    }

    @Nullable
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
        notifyChange();
    }

    @Nullable
    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
        notifyChange();
    }

    public boolean getDoRemember() {
        return doRemember.get();
    }

    public void setDoRemember(boolean doRemember) {
        this.doRemember.set(doRemember);
        notifyChange();
    }

    @NonNull
    public View.OnClickListener onLoginClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validator.validate()) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty(AppKey.EMAIL, getEmail());
                    jsonObject.addProperty(AppKey.PASSWORD, getPassword());
                    jsonObject.addProperty(AppKey.DEVICE_TYPE, AppConstants.DEVICE_TYPE);
                    jsonObject.addProperty(AppKey.LOGIN_TYPE, AppConstants.DIRECT_LOGIN_TYPE);
                    jsonObject.addProperty(AppKey.DEVICE_TOKEN, AppPreference.getInstance(context).getString(AppKey.DEVICE_TOKEN));
                    social_type = "";
                    doLogin(jsonObject, AppConstants.API_MODE.LOGIN);
                }

            }
        };
    }

    private void saveRememberData() {
        if (getDoRemember()) {
            AppPreference.getInstance(context).putBoolean(REMEMBER_ME, getDoRemember());
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(EMAIL, getEmail());
            jsonObject.addProperty(PASSWORD, getPassword());
            AppPreference.getInstance(context).putString(REMEMBER_USER_DATA, jsonObject.toString());
        }
        // what to do when new user login with its credentials and set donot remember
    }

    @NonNull
    public View.OnClickListener onForgotpasswordClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ForgotPasswordActivity.class));
            }
        };
    }

    @NonNull
    public View.OnClickListener onSignupClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navToRegister(null);

            }
        };
    }

    @NonNull
    public View.OnClickListener onFacebookClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
                if (loginManager != null)
                    loginManager.logInWithReadPermissions(context, Arrays.asList(EMAIL, PUBLIC_PROFILE));

            }
        };
    }

    @Nullable
    public View.OnClickListener onGoogleClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGoogleSignInClient != null) {
                    signOut();
                    signIn();
                }

            }
        };
    }

    @NonNull
    public View.OnClickListener onGuestClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DashBoardViewModel.currentUser.set(AppConstants.USER_ROLES.GUEST);
                context.startActivity(new Intent(context, Dashboard.class));
                context.finish();
            }
        };
    }

    private void signIn() {
        if (mGoogleSignInClient != null) {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            context.startActivityForResult(signInIntent, 111);
        }
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (callbackManager != null)
            callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(@NonNull Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            f_name = (!TextUtils.isEmpty(account.getGivenName()) ? account.getGivenName() : "");
            l_name = (!TextUtils.isEmpty(account.getFamilyName()) ? account.getFamilyName() : "");
            s_email = (!TextUtils.isEmpty(account.getEmail()) ? account.getEmail() : "");
            socialID = account.getId();
            social_type = AppConstants.GOOGLE_LOGIN_TYPE;
            doLogin(getSocialLoginRequestBody(), AppConstants.API_MODE.SOCIAL_LOGIN);
        } catch (ApiException e) {
            Log.w("google", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private void doLogin(JsonObject jsonObject, @NonNull AppConstants.API_MODE type) {
        ApiManager.requestApi(context, LoginViewModel.this, type, jsonObject);
    }

    @Override
    public void onApiSuccess(AppConstants.API_MODE mode, @NonNull JsonObject response) {
        saveRememberData();
        userModel = new Gson().fromJson(response.getAsJsonObject("data"), UserModel.class);

        AppPreference.getInstance(context).putBoolean(IS_LOGGED_IN, true); // save user logged in status
        AppPreference.getInstance(context).putString(USER_DATA, response.getAsJsonObject("data").toString()); // save user profile data
        Intent intent = new Intent(context, Dashboard.class);
        setUser(userModel);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    public void onApiFailed(@NonNull AppConstants.API_MODE mode, int statusCode, String response) {
        switch (mode) {
            case LOGIN:
                Alert.showAlert(context, ERROR, response);
                break;
            case SOCIAL_LOGIN:
                if (statusCode == 400) {
                    Alert.showAlert(context, ERROR, response);
                } else {
                    // handling status: 204
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty(EMAIL, s_email);
                    jsonObject.addProperty(SOCIAL_ID, socialID);
                    jsonObject.addProperty(FIRST_NAME, f_name);
                    jsonObject.addProperty(LAST_NAME, l_name);
                    jsonObject.addProperty("social_type", social_type);
                    navToRegister(jsonObject);
                }


        }

    }

    private void navToRegister(@Nullable JsonObject jsonObject) {
        Intent intent = new Intent(context, RegisterActivity.class);
        if (jsonObject != null)
            intent.putExtra(USER_DATA, jsonObject.toString());
        context.startActivity(intent);
        signOut();

    }

    private void signOut() {
        if (mGoogleSignInClient != null)
            mGoogleSignInClient.signOut();
        if (loginManager != null)
            loginManager.logOut();
    }

    @NonNull
    private JsonObject getSocialLoginRequestBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(EMAIL, s_email);
        jsonObject.addProperty(SOCIAL_ID, socialID);
        jsonObject.addProperty(LOGIN_TYPE, SOCIAL_TYPE);
        jsonObject.addProperty(DEVICE_TYPE, AppConstants.DEVICE_TYPE);
        jsonObject.addProperty(DEVICE_TOKEN, AppPreference.getInstance(context).getString(AppKey.DEVICE_TOKEN));
        return jsonObject;
    }
}
