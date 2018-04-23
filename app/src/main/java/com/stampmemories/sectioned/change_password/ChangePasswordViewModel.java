package com.stampmemories.sectioned.change_password;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

public class ChangePasswordViewModel extends ViewModel {

    @NonNull
    public View.OnClickListener onBackClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) context).finish();
            }
        };
    }

    @NonNull
    public View.OnClickListener onUpdateClick(final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Update Password", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
