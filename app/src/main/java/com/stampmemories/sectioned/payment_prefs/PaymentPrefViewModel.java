package com.stampmemories.sectioned.payment_prefs;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by AppsterBiz on 26-Mar-18.
 */

public class PaymentPrefViewModel extends BaseObservable {
    private Activity context;

    public PaymentPrefViewModel(Activity context) {
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
    public View.OnClickListener onAddCardClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddCardActivity.class));
            }
        };
    }
}
