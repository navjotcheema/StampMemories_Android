package com.stampmemories.sectioned.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stampmemories.R;
import com.stampmemories.core.BaseActivity;
import com.stampmemories.core.adapter.CountryCodeAdapter;
import com.stampmemories.core.adapter.PlacesArrayAdapter;
import com.stampmemories.core.api.ApiManager;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.core.app.AppKey;
import com.stampmemories.core.app.CommonFunction;
import com.stampmemories.databinding.ActivityRegisterBinding;
import com.stampmemories.helper.Alert;
import com.stampmemories.helper.AppPreference;
import com.stampmemories.interfaces.OnApiResponse;
import com.stampmemories.sectioned.dashboard.Dashboard;
import com.stampmemories.sectioned.general_information.UserModel;

import java.util.ArrayList;
import java.util.List;

import static com.stampmemories.core.app.AppKey.BUSINESS_NAME;
import static com.stampmemories.core.app.AppKey.COUNTRY_CODE;
import static com.stampmemories.core.app.AppKey.DEVICE_TOKEN;
import static com.stampmemories.core.app.AppKey.DEVICE_TYPE;
import static com.stampmemories.core.app.AppKey.EMAIL;
import static com.stampmemories.core.app.AppKey.FIRST_NAME;
import static com.stampmemories.core.app.AppKey.IS_LOGGED_IN;
import static com.stampmemories.core.app.AppKey.LAST_NAME;
import static com.stampmemories.core.app.AppKey.LATITUTE;
import static com.stampmemories.core.app.AppKey.LOCATION;
import static com.stampmemories.core.app.AppKey.LONGITUDE;
import static com.stampmemories.core.app.AppKey.PASSWORD;
import static com.stampmemories.core.app.AppKey.PHONE;
import static com.stampmemories.core.app.AppKey.REGISTER_TYPE;
import static com.stampmemories.core.app.AppKey.REP_F_NAME;
import static com.stampmemories.core.app.AppKey.REP_L_NAME;
import static com.stampmemories.core.app.AppKey.ROLE_ID;
import static com.stampmemories.core.app.AppKey.SOCIAL_ID;
import static com.stampmemories.core.app.AppKey.SOCIAL_TYPE_REGISTER;
import static com.stampmemories.core.app.AppKey.USER_DATA;
import static com.stampmemories.core.app.AppKey.USER_SERVICE_TYPE;
import static com.stampmemories.core.app.AppKey.USER_TYPE_REGISTER;
import static com.stampmemories.core.app.CommonFunction.setUser;
import static com.stampmemories.core.app.MyApplication.countryList;
import static com.stampmemories.core.app.MyApplication.userModel;

public class RegisterActivity extends BaseActivity implements OnApiResponse, AdapterView.OnItemClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    protected ActivityRegisterBinding binding;
    @Nullable
    private GoogleApiClient mGoogleApiClient = null;
    @Nullable
    private GeoDataClient mGeoDataClient = null;
    @NonNull
    private ArrayList<String> roleList = new ArrayList<>();
    @Nullable
    ArrayAdapter arrayAdapter = null;
    @NonNull
    String dialCode = "+1";
    String flagCode = "US";
    double lat = 0.0;
    double lng = 0.0;
    String social_id = "";
    String social_type = "";
    @Nullable
    RoleModel roleModel = null;
    @NonNull
    String register_type = "custom"; //default is custom
    static int default_country = 230;
    @Nullable
    PlacesArrayAdapter placesArrayAdapter = null;
    @Nullable
    CountryCodeAdapter countryCodeAdapter = null;
    String roleValue = "";
    @NonNull
    RegisterViewModel registerViewModel = new RegisterViewModel();
    String countryLocale = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setRegisterViewModel(registerViewModel);
        initialize();
    }

    private void initialize() {

        String receivedData = getIntent().getStringExtra(USER_DATA);
        if (!TextUtils.isEmpty(receivedData)) {
            updateUI(receivedData);
            registerViewModel.setIsSocial(true);
            register_type = "social";
        }
        initializeGoogleApiClient();
        setAdapters();
        addListenersToWidgets();
    }

    private void updateUI(String receivedData) {
        JsonObject jsonObject = new JsonParser().parse(receivedData).getAsJsonObject();
        binding.email.setText(jsonObject.get(EMAIL).getAsString());
        binding.firstName.setText(jsonObject.get(FIRST_NAME).getAsString());
        binding.lastName.setText(jsonObject.get(LAST_NAME).getAsString());
        social_id = jsonObject.get(SOCIAL_ID).getAsString();
        social_type = jsonObject.get("social_type").getAsString();
    }

    private void initializeGoogleApiClient() {
        placesArrayAdapter = new PlacesArrayAdapter(this, android.R.layout.simple_list_item_1, null, null);
        mGeoDataClient = Places.getGeoDataClient(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .build();


    }

    private void setAdapters() {
        binding.chooserUserType.setAdapter(ArrayAdapter.createFromResource(this, R.array.user_type, R.layout.txt_black));
        binding.chooserRegisterType.setAdapter(ArrayAdapter.createFromResource(this, R.array.user_service_type, R.layout.txt_black));
        arrayAdapter = new ArrayAdapter<>(this, R.layout.txt_black, roleList);
        binding.chooserRoleType.setAdapter(arrayAdapter);
        countryCodeAdapter = new CountryCodeAdapter(this, countryList);
        binding.spinnerCodeRegister.setAdapter(countryCodeAdapter);
        binding.location.setAdapter(placesArrayAdapter);

    }

    private void addListenersToWidgets() {
        binding.chooserRegisterType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, @NonNull MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.chooserRegisterType.showDropDown();
                }
                return false;
            }

        });
        binding.chooserRegisterType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, @Nullable View view, int i, long l) {
                if (view != null) {
                    if (i == 0) {
//                        user_service_type = "customer";
                        ApiManager.requestApi(RegisterActivity.this, RegisterActivity.this, AppConstants.API_MODE.CUSTOMER_ROLE, new JsonObject());
                    }
                    if (i == 1) {
//                        user_service_type = "provider";
                        ApiManager.requestApi(RegisterActivity.this, RegisterActivity.this, AppConstants.API_MODE.PROVIDER_ROLE, new JsonObject());
                    }
                }
            }
        });
        binding.chooserUserType.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, @NonNull MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!TextUtils.isEmpty(binding.chooserRegisterType.getText().toString()))
                        binding.chooserUserType.showDropDown();
                }
                return false;

            }
        });
        binding.chooserUserType.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, @NonNull MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!TextUtils.isEmpty(binding.chooserRegisterType.getText().toString())) {
                        binding.chooserUserType.showDropDown();
                    } else
                        Alert.showAlert(RegisterActivity.this, "", "Please select Register as");

                }
                return false;
            }
        });
        binding.chooserUserType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, @Nullable View view, int i, long l) {
                if (view != null && roleModel != null) {
                    if (i == 0) {
                        roleList.clear();
//                        user_type = "individual";
//                        binding.chooserUserType.setText(((TextView) view).getText().toString());
                        roleList.add(roleModel.getIndividual().getDescription());
                        arrayAdapter.notifyDataSetChanged();
                        registerViewModel.setIsIndividual(true);
                        roleValue = getRoleID(binding.chooserUserType.getText().toString());
                        arrayAdapter.notifyDataSetChanged();

                    } else if (i == 1) {
//                        user_type = "business";
//                        binding.chooserUserType.setText(((TextView) view).getText().toString());
                        registerViewModel.setIsIndividual(false);
                        roleList.clear();
                        if (roleModel != null) {
                            List<RoleModel.Business> list = roleModel.getBusiness();
                            for (int j = 0; j < list.size(); j++) {
                                roleList.add(list.get(j).getDescription());
                            }
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
        binding.chooserRoleType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, @NonNull MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!TextUtils.isEmpty(binding.chooserRegisterType.getText().toString())) {
                        if (!TextUtils.isEmpty(binding.chooserUserType.getText().toString())) {
                            binding.chooserRoleType.showDropDown();
                        } else
                            Alert.showAlert(RegisterActivity.this, "", "Please select are you a?");
                    } else
                        Alert.showAlert(RegisterActivity.this, "", "Please select Register as");
                }
                return false;

            }
        });
        binding.chooserRoleType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, @NonNull View view, int i, long l) {
                roleValue = getRoleID(((TextView) view).getText().toString());
            }
        });
        binding.spinnerCodeRegister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, @Nullable View view, int i, long l) {
                if (view != null) {
                    dialCode = ((TextView) view).getText().toString();
                    flagCode = countryList.get(i).getCode();
                    binding.imageFlag.setImageDrawable(CommonFunction.getCountryFlag(getApplicationContext(), flagCode.toLowerCase()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty(DEVICE_TYPE, AppConstants.DEVICE_TYPE);
                    jsonObject.addProperty(USER_SERVICE_TYPE, binding.chooserRegisterType.getText().toString().toLowerCase());
                    jsonObject.addProperty(DEVICE_TOKEN, AppPreference.getInstance(getApplicationContext()).getString(AppKey.DEVICE_TOKEN));
                    jsonObject.addProperty(ROLE_ID, roleValue);
                    jsonObject.addProperty(COUNTRY_CODE, dialCode);
                    jsonObject.addProperty(PHONE, binding.phone.getText().toString().trim());
                    jsonObject.addProperty(EMAIL, binding.email.getText().toString().trim());
                    jsonObject.addProperty(PASSWORD, binding.password.getText().toString().trim());
                    jsonObject.addProperty(LATITUTE, lat);
                    jsonObject.addProperty(LONGITUDE, lng);
                    jsonObject.addProperty(LOCATION, binding.location.getText().toString());
                    jsonObject.addProperty(USER_TYPE_REGISTER, binding.chooserUserType.getText().toString().toLowerCase());
                    jsonObject.addProperty(REGISTER_TYPE, register_type);
                    jsonObject.addProperty(SOCIAL_ID, social_id);
                    jsonObject.addProperty(SOCIAL_TYPE_REGISTER, social_type);
                    jsonObject.addProperty(AppKey.LOCALE, flagCode.toLowerCase());
                    jsonObject.addProperty(AppKey.COUNTRY_LOCALE, countryLocale.toLowerCase());
                    if (registerViewModel.getIsIndividual()) {
                        jsonObject.addProperty(FIRST_NAME, binding.firstName.getText().toString().trim());
                        jsonObject.addProperty(LAST_NAME, binding.lastName.getText().toString().trim());
                    } else {
                        jsonObject.addProperty(BUSINESS_NAME, binding.businessName.getText().toString().trim());
                        jsonObject.addProperty(REP_F_NAME, binding.businessRepFName.getText().toString().trim());
                        jsonObject.addProperty(REP_L_NAME, binding.businessRepLName.getText().toString().trim());
                    }
                    ApiManager.requestApi(RegisterActivity.this, RegisterActivity.this, AppConstants.API_MODE.REGISTER, jsonObject);
                }
            }
        });
        binding.location.setOnItemClickListener(this);
        binding.spinnerCodeRegister.setSelection(default_country);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                Place place = PlaceAutocomplete.getPlace(this, data);
//                location = place.getAddress().toString();
//                binding.location.setText(location);
//                lat = place.getLatLng().latitude;
//                lng = place.getLatLng().longitude;
//                countryLocale = place.getLocale().getCountry();
//            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
//                Status status = PlaceAutocomplete.getStatus(this, data);
//                Log.e(APP_NAME, status.getStatusMessage());
//
//            } else if (resultCode == RESULT_CANCELED) {
//            }
//        }
//    }

    @Override
    public void onApiSuccess(AppConstants.API_MODE mode, @NonNull JsonObject response) {

        if (mode == AppConstants.API_MODE.CUSTOMER_ROLE || mode == AppConstants.API_MODE.PROVIDER_ROLE) {
            roleModel = new RoleModel();
            roleModel = new Gson().fromJson(response.getAsJsonObject("data"), RoleModel.class);
        } else {
            // register api response
            userModel = new Gson().fromJson(response.getAsJsonObject("data"), UserModel.class);
            AppPreference.getInstance(this).putBoolean(IS_LOGGED_IN, true); // save user logged in status
            AppPreference.getInstance(this).putString(USER_DATA, response.getAsJsonObject("data").toString()); // save user profile data
            Intent intent = new Intent(this, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            setUser(userModel);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onApiFailed(AppConstants.API_MODE mode, int statusCode, String response) {
        Alert.showAlert(this, AppKey.ERROR, response);
    }

    private String getRoleID(String roleString) {
        String roleID = "";
        if (!TextUtils.isEmpty(roleString) && roleModel != null) {
            if (roleModel.getIndividual().getDescription().equalsIgnoreCase(roleString)) {
                roleID = roleModel.getIndividual().getId();
            } else {
                for (int k = 0; k < roleModel.getBusiness().size(); k++) {
                    if (roleModel.getBusiness().get(k).getDescription().equalsIgnoreCase(roleString)) {
                        roleID = roleModel.getBusiness().get(k).getId();
                    }
                }
            }
        }


        return roleID;
    }

    @Override
    public void onItemClick(@Nullable AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView != null && placesArrayAdapter != null && mGeoDataClient != null) {
            final PlacesArrayAdapter.PlaceAutocomplete item = placesArrayAdapter.getItem(i);
            String placeId = "";
            if (item != null) {
                placeId = String.valueOf(item.placeId);
                binding.location.setText(item.description);
            }

            mGeoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                    if (task.isSuccessful()) {
                        PlaceBufferResponse places = task.getResult();
                        Place myPlace = places.get(0);
                        lat = myPlace.getLatLng().latitude;
                        lng = myPlace.getLatLng().longitude;
                        countryLocale = myPlace.getLocale().getCountry();
                        places.release();
                    } else
                        Toast.makeText(getApplicationContext(), "Some error occurred, please select the location again", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (placesArrayAdapter != null)
            placesArrayAdapter.setGoogleApiClient(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (placesArrayAdapter != null)
            placesArrayAdapter.setGoogleApiClient(null);
    }

    private boolean validateForm() {
        boolean isValid = false;
        if (!TextUtils.isEmpty(binding.chooserRegisterType.getText().toString())) {
            if (!TextUtils.isEmpty(binding.chooserUserType.getText().toString())) {
                if (registerViewModel.getIsIndividual()) {
                    if (!TextUtils.isEmpty(binding.firstName.getText().toString())) {
                        if (!TextUtils.isEmpty(binding.lastName.getText().toString())) {
                            isValid = validateOther();
                        } else
                            Alert.showAlert(this, "", "Please enter Last Name");
                    } else
                        Alert.showAlert(this, "", "Please enter First Name");
                } else {

                    if (!TextUtils.isEmpty(binding.businessName.getText().toString())) {
                        if (!TextUtils.isEmpty(binding.businessRepFName.getText().toString())) {
                            if (!TextUtils.isEmpty(binding.businessRepLName.getText().toString())) {
                                if (!TextUtils.isEmpty(binding.chooserRoleType.getText().toString())) {
                                    isValid = validateOther();
                                } else
                                    Alert.showAlert(this, "", "Please select Role at the Company");
                            } else
                                Alert.showAlert(this, "", "PLease enter Representative Last Name");
                        } else
                            Alert.showAlert(this, "", "PLease enter Representative First Name");
                    } else
                        Alert.showAlert(this, "", "PLease enter Business Name");
                }
            } else
                Alert.showAlert(this, "", "Please select are you a?");
        } else
            Alert.showAlert(this, "", "Please select Register as");


        return isValid;
    }

    private boolean validateOther() {
        boolean isValid = false;
        if (!TextUtils.isEmpty(binding.phone.getText().toString())) {
            if (!TextUtils.isEmpty(binding.location.getText().toString())) {
                if (!TextUtils.isEmpty(binding.email.getText().toString())) {
                    if (binding.email.getText().toString().matches(Patterns.EMAIL_ADDRESS.pattern())) {
                        if (registerViewModel.getIsSocial()) {
                            isValid = true;
                        } else {
                            if (!TextUtils.isEmpty(binding.password.getText().toString())) {
                                if (!TextUtils.isEmpty(binding.cPassword.getText().toString())) {
                                    if (binding.password.getText().toString().equals(binding.cPassword.getText().toString())) {
                                        isValid = true;
                                    } else
                                        Alert.showAlert(this, "", "Password mismatch");
                                } else
                                    Alert.showAlert(this, "", "Please enter Confirm Password");
                            } else
                                Alert.showAlert(this, "", "Please enter Password");
                        }
                    } else
                        Alert.showAlert(this, "", "Please enter valid Email");
                } else
                    Alert.showAlert(this, "", "Please enter Email");
            } else
                Alert.showAlert(this, "", "Please enter Location");
        } else
            Alert.showAlert(this, "", "Please enter Phone");


        return isValid;
    }


}
