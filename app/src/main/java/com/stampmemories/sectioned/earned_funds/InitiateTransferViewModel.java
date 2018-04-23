package com.stampmemories.sectioned.earned_funds;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InitiateTransferViewModel extends BaseObservable {

    @NonNull
    public View.OnClickListener onBackClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).onBackPressed();
            }
        };
    }

    @NonNull
    public View.OnClickListener onTransferClick(final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }
}
