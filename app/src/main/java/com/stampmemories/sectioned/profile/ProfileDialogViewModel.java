package com.stampmemories.sectioned.profile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.stampmemories.sectioned.add_event.AddNewActivity;

/**
 * Created by AppsterBiz on 06-Apr-18
 */

public class ProfileDialogViewModel {

    AlertDialog alertDialog;

    public ProfileDialogViewModel(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    @NonNull
    public View.OnClickListener onActivityClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddNewActivity.class));

            }
        };
    }

    @NonNull
    public View.OnClickListener onExperienceClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddNewActivity.class));
            }
        };
    }
    @NonNull
    public View.OnClickListener onCloseClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              alertDialog.dismiss();
            }
        };
    }
}
