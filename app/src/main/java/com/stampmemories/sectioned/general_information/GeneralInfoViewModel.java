package com.stampmemories.sectioned.general_information;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stampmemories.core.api.ApiManager;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.core.app.AppKey;
import com.stampmemories.helper.Alert;
import com.stampmemories.helper.AppPreference;
import com.stampmemories.interfaces.OnApiResponse;

import static com.stampmemories.core.app.CommonFunction.stringToEnum;
import static com.stampmemories.core.app.MyApplication.countryList;
import static com.stampmemories.core.app.MyApplication.userModel;

/**
 * Created by AppsterBiz on 26-Mar-18
 */

public class GeneralInfoViewModel extends BaseObservable implements OnApiResponse {

    private Activity context;
    private Enum userType;
    @NonNull
    private ObservableField<String> verifiedText = new ObservableField<>();
    @NonNull
    private ObservableField<String> countryCode = new ObservableField<>();
    @NonNull
    private ObservableField<String> countryLocale = new ObservableField<>();
    private String lat = "", lng = "", country_locale = "";


    GeneralInfoViewModel(Activity context) {
        this.context = context;
        initialize();
    }

    private void initialize() {
        setUserType(stringToEnum(userModel.getUser_type()));
        countryCode.addOnPropertyChangedCallback(new OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                setCountryLocale(countryLocale.get());
                notifyChange();
            }
        });
//        setCountryCode(countryList.get(dialCodePosition(userModel.getLocale())).getDial_code());
    }

    public Enum getUserType() {
        return userType;
    }

    private void setUserType(Enum userType) {
        this.userType = userType;
        notifyChange();
    }

    @Nullable
    public String getVerifiedText() {
        return verifiedText.get();
    }

    public void setVerifiedText(String verifiedText) {
        this.verifiedText.set(verifiedText);
        notifyChange();
    }

    @Nullable
    private String getCountryCode() {
        return countryCode.get();
    }

    private void setCountryCode(String countryCode) {
        this.countryCode.set(countryCode);
        notifyChange();
    }

    @Nullable
    public String getCountryLocale() {
        return countryLocale.get();
    }

    private void setCountryLocale(String countryLocale) {
        this.countryLocale.set(countryLocale);
    }

    @NonNull
    public View.OnClickListener onBackClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.onBackPressed();
            }
        };
    }

    @NonNull
    public View.OnClickListener onVerifyPhoneClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, VerifyPhoneActivity.class));
            }
        };
    }

    public void afterPhoneChanged(@NonNull Editable phone) {
        if (phone.toString().equals(userModel.getPhone())) {
            setVerifiedText("Verified");
        } else
            setVerifiedText("Verify");

    }

    void getCountryLocale(int position) {
        countryLocale.set(countryList.get(position).getCode());
        setCountryCode(countryList.get(position).getDial_code());
    }

    @NonNull
    public View.OnClickListener onUpdateClick(@NonNull final TextInputEditText email, @NonNull final TextInputEditText fName, @NonNull final TextInputEditText lName, @NonNull final TextInputEditText businessName,
                                              @NonNull final TextInputEditText repFName, @NonNull final TextInputEditText repLName, @NonNull final TextInputEditText phone, @NonNull final AutoCompleteTextView location,
                                              @NonNull final TextInputEditText providerUrl) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty(AppKey.USERID, userModel.getId());
                jsonObject.addProperty(AppKey.EMAIL, email.getText().toString().trim());
                jsonObject.addProperty(AppKey.PHONE, phone.getText().toString().trim());
                jsonObject.addProperty(AppKey.LOCATION, location.getText().toString().trim());
                jsonObject.addProperty(AppKey.COUNTRY_CODE, getCountryCode());
                jsonObject.addProperty(AppKey.LOCALE, getCountryLocale().toLowerCase());
                jsonObject.addProperty("country_locale", country_locale);

                /*
                 * Both customer and provider have a profile url
                 * When in customer role, the profile url cannot be modified,
                 * when in provider role, the profile url can be modified.
                 * so, when updating the profile in customer role, the default profile url of the user will be passed
                 * and in provider role, the entered/updated url from the provider url field will be sent to server
                 */
                if (userModel.getIs_provider().equalsIgnoreCase(AppKey.YES))
                    jsonObject.addProperty(AppKey.PROFILE_URL, providerUrl.getText().toString());
                else
                    jsonObject.addProperty(AppKey.PROFILE_URL, userModel.getProfile_url());
                // send new lat lng only when a new place is selected, else no need to update
                if (!TextUtils.isEmpty(lat) && !TextUtils.isEmpty(lng)) {
                    jsonObject.addProperty(AppKey.LATITUTE, lat);
                    jsonObject.addProperty(AppKey.LONGITUDE, lng);
                }
                /*
                * for individual user profile: @param firstname, lastname will be sent
                * for business user profile: @param business name, representative first name, representative last name will be sent
                * */
                if (userModel.getUser_type().equalsIgnoreCase("individual")) {
                    jsonObject.addProperty(AppKey.USER_TYPE_REGISTER, userModel.getUser_type());
                    jsonObject.addProperty(AppKey.FIRST_NAME, fName.getText().toString().trim());
                    jsonObject.addProperty(AppKey.LAST_NAME, lName.getText().toString().trim());
                } else {
                    jsonObject.addProperty(AppKey.USER_TYPE_REGISTER, userModel.getUser_type());
                    jsonObject.addProperty(AppKey.BUSINESS_NAME, businessName.getText().toString().trim());
                    jsonObject.addProperty(AppKey.REP_F_NAME, repFName.getText().toString().trim());
                    jsonObject.addProperty(AppKey.REP_L_NAME, repLName.getText().toString().trim());
                }

                ApiManager.requestApi(context, GeneralInfoViewModel.this, AppConstants.API_MODE.UPDATE_GENERAL_INFORMATION, jsonObject);

            }
        };
    }

    @Override
    public void onApiSuccess(AppConstants.API_MODE mode, @NonNull JsonObject response) {

        Alert.showAlert(context, AppKey.MESSAGE, response.get(AppKey.MESSAGE).getAsString());
        userModel = new Gson().fromJson(response.getAsJsonObject("data"), UserModel.class);
        AppPreference.getInstance(context).putString(AppKey.USER_DATA, new Gson().toJson(userModel));

    }

    @Override
    public void onApiFailed(AppConstants.API_MODE mode, int statusCode, String response) {
        Alert.showAlert(context, AppKey.ERROR, response);

    }

    void setLatLang(String lat, String lng, String country_locale) {
        this.lat = lat;
        this.lng = lng;
        this.country_locale = country_locale;
    }
}
