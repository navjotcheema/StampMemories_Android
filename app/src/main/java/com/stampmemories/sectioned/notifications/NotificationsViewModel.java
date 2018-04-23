package com.stampmemories.sectioned.notifications;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static com.stampmemories.sectioned.dashboard.DashBoardViewModel.currentUser;

/**
 * Created by AppsterBiz on 04-Apr-18
 */

public class NotificationsViewModel extends BaseObservable {

    @NonNull
    public View.OnClickListener onBackNav(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).onBackPressed();
            }
        };
    }

    @NonNull
    public View.OnClickListener onContactSupportClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ContactSupportActivity.class));
            }
        };
    }

    @Nullable
    public Enum getCurrentUser() {
        return currentUser.get();
    }


    @BindingAdapter("list_notifications")
    public static void bindNotificationsList(@NonNull RecyclerView recyclerView, @NonNull Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new NotificationListAdapter(context));
    }
}
