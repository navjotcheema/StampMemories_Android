package com.stampmemories.sectioned.cust_orders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.view.View;

import com.stampmemories.sectioned.communication.CommunicationSingleActivity;
import com.stampmemories.helper.Alert;
import com.stampmemories.sectioned.myorders.OrderDetailActivity;

/**
 * Created by AppsterBiz on 06-Apr-18
 */

public class CustomerOrderDialogViewModel extends BaseObservable {

    AlertDialog alertDialog;

    public CustomerOrderDialogViewModel(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
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

    @NonNull
    public View.OnClickListener onViewDetail(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, OrderDetailActivity.class));

            }
        };
    }

    @NonNull
    public View.OnClickListener onContactProvider(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CommunicationSingleActivity.class));
            }
        };
    }

    @NonNull
    public View.OnClickListener onRequestRefund(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert.showAlert(context, "Request Refund", "Refund request has been send to the provider.");

            }
        };
    }

    @NonNull
    public View.OnClickListener onRequestRescheduling(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert.showAlert(context, "Request Rescheduling", "Rescheduling request has been send to the provider.");
            }
        };
    }

    @NonNull
    public View.OnClickListener onLeaveRating(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, LeaveRatingActivity.class));
            }
        };
    }
}
