package com.stampmemories.sectioned.notifications;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import static com.stampmemories.sectioned.dashboard.DashBoardViewModel.currentUser;

/**
 * Created by AppsterBiz on 04-Apr-18
 */

public class ContactSupportViewModel extends BaseObservable {

    @Nullable
    public Enum getCurrentUser() {
        return currentUser.get();
    }

    @NonNull
    public View.OnClickListener onBackClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).onBackPressed();
            }
        };
    }
}
