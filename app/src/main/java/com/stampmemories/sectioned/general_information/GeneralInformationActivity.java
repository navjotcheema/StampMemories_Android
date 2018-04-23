package com.stampmemories.sectioned.general_information;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.stampmemories.R;
import com.stampmemories.core.BaseActivity;
import com.stampmemories.core.adapter.CountryCodeAdapter;
import com.stampmemories.core.adapter.PlacesArrayAdapter;
import com.stampmemories.databinding.ActivityGeneralInformationBinding;
import com.stampmemories.sectioned.dashboard.DashBoardViewModel;

import java.util.Locale;

import static com.stampmemories.core.app.CommonFunction.dialCodePosition;
import static com.stampmemories.core.app.MyApplication.countryList;
import static com.stampmemories.core.app.MyApplication.userModel;

public class GeneralInformationActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, AdapterView.OnItemClickListener {

    private GeneralInfoViewModel generalInfoViewModel;
    private ActivityGeneralInformationBinding binding;
    @Nullable
    private GoogleApiClient mGoogleApiClient = null;
    @Nullable
    private GeoDataClient mGeoDataClient = null;
    @Nullable
    private PlacesArrayAdapter placesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_general_information);
        generalInfoViewModel = new GeneralInfoViewModel(this);
        binding.setCurrentUser(DashBoardViewModel.currentUser);
        binding.spinnerCodeGI.setOnItemSelectedListener(this);
        binding.spinnerCodeGI.setAdapter(new CountryCodeAdapter(this, countryList));
        if (userModel.getLocale().isEmpty())
            binding.spinnerCodeGI.setSelection(dialCodePosition("US"));
        else
            binding.spinnerCodeGI.setSelection(dialCodePosition(userModel.getLocale()));
        binding.setGenInfoViewModel(generalInfoViewModel);
        binding.setUserModel(userModel);
        initializeGoogleApiClient();
        binding.locationGI.setAdapter(placesArrayAdapter);
        binding.locationGI.setOnItemClickListener(this);
    }

    @Override
    public void onItemSelected(@Nullable AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView != null) {
            generalInfoViewModel.getCountryLocale(i);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void initializeGoogleApiClient() {
        placesArrayAdapter = new PlacesArrayAdapter(this, android.R.layout.simple_list_item_1, null, null);
        mGeoDataClient = Places.getGeoDataClient(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .build();
        mGoogleApiClient.connect();


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        placesArrayAdapter.setGoogleApiClient(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        placesArrayAdapter.setGoogleApiClient(null);
    }

    @Override
    public void onItemClick(@Nullable AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView != null) {
            final PlacesArrayAdapter.PlaceAutocomplete item = placesArrayAdapter.getItem(i);
            final String placeId = String.valueOf(item.placeId);
            binding.locationGI.setText(item.description);
            mGeoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                    if (task.isSuccessful()) {
                        PlaceBufferResponse places = task.getResult();
                        Place myPlace = places.get(0);
                        generalInfoViewModel.setLatLang(myPlace.getLatLng().latitude + "",
                                myPlace.getLatLng().longitude + "", myPlace.getLocale().getCountry());
                        places.release();
                    } else
                        Toast.makeText(getApplicationContext(), "Some error occurred, please select the location again", Toast.LENGTH_SHORT).show();
                }

            });
        }
    }
}
