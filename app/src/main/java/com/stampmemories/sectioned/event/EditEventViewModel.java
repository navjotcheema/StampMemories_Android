package com.stampmemories.sectioned.event;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class EditEventViewModel extends BaseObservable {


    @NonNull
    public View.OnClickListener onBackClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).finish();
            }
        };
    }

    @NonNull
    public View.OnClickListener onUpdateClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                ((AppCompatActivity) context).finish();
            }
        };
    }

}
