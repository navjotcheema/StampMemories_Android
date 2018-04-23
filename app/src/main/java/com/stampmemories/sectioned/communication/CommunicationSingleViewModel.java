package com.stampmemories.sectioned.communication;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CommunicationSingleViewModel extends BaseObservable {

    @NonNull
    public View.OnClickListener onCloseClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).finish();
            }
        };
    }
}
