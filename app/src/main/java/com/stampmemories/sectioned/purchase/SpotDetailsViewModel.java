package com.stampmemories.sectioned.purchase;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.view.View;

import com.stampmemories.helper.Alert;

/**
 * Created by AppsterBiz on 27-Mar-18
 */

public class SpotDetailsViewModel extends BaseObservable {
    private Activity context;

    public SpotDetailsViewModel(Activity context) {
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
    public View.OnClickListener onNextClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert.showAlert(context, "Status", "Stripe to be implemented");
            }
        };
    }
}
