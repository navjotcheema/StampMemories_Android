package com.stampmemories.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.ObservableArrayList;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.stampmemories.R;
import com.stampmemories.core.adapter.PlacesArrayAdapter;
import com.stampmemories.helper.AppPreference;
import com.stampmemories.sectioned.dashboard.DashBoardViewModel;
import com.stampmemories.sectioned.dashboard.DrawerModel;
import com.stampmemories.sectioned.general_information.UserModel;
import com.stampmemories.sectioned.invitefriend.InviteFriendModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import static com.stampmemories.core.app.AppConstants.USER_TYPE.BUSINESS;
import static com.stampmemories.core.app.AppKey.YES;
import static com.stampmemories.core.app.MyApplication.countryList;
import static com.stampmemories.core.app.MyApplication.userModel;

/**
 * Created by AppsterBiz on 16-Dec-17
 */

public class CommonFunction {
    @Nullable
    private static ProgressDialog progressDialog;
    @Nullable
    private static LatLng latLng = null;

    public static void showToast(Activity context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

//    public static void removeAllView(Activity context) {
//        LinearLayout linearLayout = context.findViewById(R.id.landing_page_container);
//        linearLayout.removeAllViews();
//    }

//    public static void setUpToolbar(Activity context, String value, boolean showLocation, boolean showTitle) {
//        Toolbar toolbar = null;
//        AutofitTextView title = null;
//        if (context != null) {
//            toolbar = context.findViewById(R.id.toolbar);
//            title = toolbar.findViewById(R.id.toolbar_title);
//        }
//        LinearLayout layout = toolbar.findViewById(R.id.toolbar_location);
//        LinearLayout titleLayout = toolbar.findViewById(R.id.title_layout);
//        if (showLocation && showTitle) {
//            layout.setVisibility(View.VISIBLE);
//            titleLayout.setVisibility(View.VISIBLE);
//            title.setText(value);
//            AutofitHelper.create(title);
//            setMargins(title, 0, 0, 0, 0);
//        } else {
//            if (showTitle) {
//                layout.setVisibility(View.GONE);
//                titleLayout.setVisibility(View.VISIBLE);
//                title.setText(value);
//                setMargins(title, 0, 0, getActionBarSize(context), 0);
//                AutofitHelper.create(title);
//            } else {
//                titleLayout.setVisibility(View.GONE);
//                layout.setVisibility(View.VISIBLE);
//            }
//        }
////        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(title,R.dimen.min_title_bar_size,R.dimen.max_title_bar_size,2,);
//    }

    public static String loadJSONFromAsset(@NonNull Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void showGpsOffAlert(@NonNull final Activity context) {
        new AlertDialog.Builder(context).setTitle("Enable GPS")
                .setMessage("Please enable gps from settings.")
                .setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("loc-search").putExtra("gps_enabled", 0));

                    }
                }).create().show();
    }

    public static void showProgressDialog(@Nullable Activity context, String message) {
        if (context != null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setMessage(message);
            progressDialog.show();
        }
    }

    public static void dismissProgressDialog(@Nullable Activity context) {
        if (context != null) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    public static void hideKeyboard(IBinder iBinder, @NonNull Activity context) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(iBinder, 0);
        }
    }

    private static void setMargins(@NonNull View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    private static int getActionBarSize(@NonNull Activity context) {
        TypedValue tv = new TypedValue();

        if (context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true))
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        else return 0;

    }

    public static ObservableArrayList<DrawerModel.Drawer.Sub_menu> getMenuList(@NonNull Activity context, boolean isProvider) {
        ObservableArrayList<DrawerModel.Drawer.Sub_menu> menuList = new ObservableArrayList<>();

        if (AppPreference.getInstance(context).getBoolean(AppKey.IS_LOGGED_IN)) {
            if (!isProvider) {
                // for customer
                String jsonString = loadJSONFromAsset(context, "customer_drawer");
                Gson gson = new Gson();
                DrawerModel drawerModel = gson.fromJson(jsonString, DrawerModel.class);
                menuList.clear();
                menuList = drawerModel.getDrawer().getSub_menu();
                if (AppPreference.getInstance(context).getString(userModel.getIs_provider()).equalsIgnoreCase(AppKey.YES)) {
                    // if customer is also a provider
                    removeAnObject(menuList, "Become a provider");
                } else {
                    removeAnObject(menuList, "Switch to provider");
                }
            } else {
                // for provider
                String jsonString = loadJSONFromAsset(context, "provider_drawer");
                Gson gson = new Gson();
                DrawerModel drawerModel = gson.fromJson(jsonString, DrawerModel.class);
                menuList.clear();
                menuList = drawerModel.getDrawer().getSub_menu();
            }
        } else {
            String jsonString = loadJSONFromAsset(context, "guest_drawer");
            Gson gson = new Gson();
            DrawerModel drawerModel = gson.fromJson(jsonString, DrawerModel.class);
            menuList.clear();
            menuList = drawerModel.getDrawer().getSub_menu();
        }
        return menuList;
    }

    private static void removeAnObject(@NonNull ArrayList<DrawerModel.Drawer.Sub_menu> menuList, String s) {
        for (int i = 0; i < menuList.size(); i++) {
            Log.e("title", menuList.get(i).getTitle());
            if (menuList.get(i).getTitle().equalsIgnoreCase(s)) {
                menuList.remove(i);
            }
        }

    }

    public static Drawable getDrawableByName(@NonNull Activity context, String resourceName) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(resourceName, "drawable",
                context.getPackageName());
        return resources.getDrawable(resourceId);
    }

    public static int getIndex(@NonNull Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static void navigateActivityTransitions(@NonNull Activity currentActivity, @NonNull AppConstants.TRANSITIONS transitions, boolean isentry) {
        if (isentry) {
            switch (transitions) {
                case ENTRY:
                    currentActivity.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                    break;
                case SLIDE_IN:
//                    currentActivity.overridePendingTransition(R.anim.anim_slide_in, R.anim.anim_slide_out);
                    break;

            }
        } else {
            switch (transitions) {
                case EXIT:
                    currentActivity.overridePendingTransition(R.anim.exit_to_right, R.anim.enter_from_left);
                    break;

            }

        }
    }

    public static void finishActivity(Activity currentActivity, @NonNull AppConstants.TRANSITIONS transitions) {
        switch (transitions) {
            case EXIT:
                //currentActivity.overridePendingTransition(R.anim.anim_exit, R.anim.anim_entry);
                break;
            case SLIDE_OUT:
                //currentActivity.overridePendingTransition(R.anim.anim_slide_out, R.anim.anim_slide_in);
                break;

        }
    }

    public static void showExportDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Export");
        builder.setMessage("Select an export file type:");
        builder.setPositiveButton("PDF", null);
        builder.setNegativeButton("EXCEL", null);
        builder.setNeutralButton("CSV", null);
        builder.create().show();
    }

    @NonNull
    public static ArrayList<InviteFriendModel> getInviteTypes(@NonNull Activity context) {
        String jsonString = loadJSONFromAsset(context, "invite_type.json");
        InviteFriendModel[] list = new Gson().fromJson(jsonString, InviteFriendModel[].class);
        ArrayList<InviteFriendModel> list1 = new ArrayList<>();
        Collections.addAll(list1, list);
        return list1;

    }

    @Nullable
    public static Enum stringToEnum(@NonNull String value) {
        Enum val = null;
        switch (value.toLowerCase()) {
            case "individual":
                val = AppConstants.USER_TYPE.INDIVIDUAL;
                break;
            case "business":
                val = BUSINESS;
                break;

        }
        return val;
    }


    public static Drawable getCountryFlag(@NonNull Context context, @NonNull String name) {

        int rid = context.getResources().getIdentifier(name.toLowerCase(), "raw", context.getPackageName());
        Resources res = context.getResources();
        InputStream in = res.openRawResource(rid);
        Drawable image = Drawable.createFromStream(in, name);

        return image;
    }

    public static void setUser(@NonNull UserModel userModel) {
        if (userModel.getIs_provider().equalsIgnoreCase(YES))
            DashBoardViewModel.currentUser.set(AppConstants.USER_ROLES.PROVIDER);
        else
            DashBoardViewModel.currentUser.set(AppConstants.USER_ROLES.CUSTOMER);
    }

    public static int dialCodePosition(String locale) {
        int pos = 0;
        for (int j = 0; j < countryList.size(); j++) {
            if (countryList.get(j).getCode().equalsIgnoreCase(locale)) {
                pos = j;
                break;
            }

        }
        return pos;

    }

    @Nullable
    public static LatLng getLatLngFromLocation(@NonNull final Activity context, @NonNull PlacesArrayAdapter placesArrayAdapter, int i) {
        final PlacesArrayAdapter.PlaceAutocomplete item = placesArrayAdapter.getItem(i);
        final String placeId = String.valueOf(item.placeId);
//        binding.location.setText(item.description);
        GeoDataClient mGeoDataClient = Places.getGeoDataClient(context);
        mGeoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();
                    Place myPlace = places.get(0);
                    latLng = myPlace.getLatLng();
//                    Log.i(APP_NAME, "Place found: " + myPlace.getName() + "//" + myPlace.getLatLng());
//                    lat = myPlace.getLatLng().latitude;
//                    lng = myPlace.getLatLng().longitude;
                    places.release();
                } else {
                    Toast.makeText(context, "Some error occurred, please select the location again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return latLng;
    }


}
