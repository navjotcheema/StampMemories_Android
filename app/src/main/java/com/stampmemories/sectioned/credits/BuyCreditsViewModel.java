package com.stampmemories.sectioned.credits;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by AppsterBiz on 26-Mar-18.
 */

public class BuyCreditsViewModel extends BaseObservable {
    private Activity context;

    public BuyCreditsViewModel(Activity context) {
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
}
