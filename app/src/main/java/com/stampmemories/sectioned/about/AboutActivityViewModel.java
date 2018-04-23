package com.stampmemories.sectioned.about;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import com.stampmemories.R;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.helper.InstantAutoComplete;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 26-Mar-18.
 */

public class AboutActivityViewModel extends BaseObservable {
    private Activity context;

    public AboutActivityViewModel(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
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
    public View.OnClickListener onSelectClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SelectEventsActivity.class));
            }
        };
    }

    @NonNull
    public View.OnClickListener onSelectAgeClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SelectAgeGroupActivity.class));
            }
        };
    }

    @NonNull
    public ArrayList<Object> getDataList(AppConstants.DROPDOWN_TYPE type) {
        return new ArrayList<>();
    }
}
