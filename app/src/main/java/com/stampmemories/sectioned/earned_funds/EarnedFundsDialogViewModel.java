package com.stampmemories.sectioned.earned_funds;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.stampmemories.sectioned.communication.CommunicationSingleActivity;
import com.stampmemories.sectioned.myorders.OrderDetailActivity;

/**
 * Created by AppsterBiz on 06-Apr-18
 */

public class EarnedFundsDialogViewModel extends BaseObservable {

    private AlertDialog alertDialog;

    public EarnedFundsDialogViewModel(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    @NonNull
    public View.OnClickListener onClose() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        };
    }

    @NonNull
    public View.OnClickListener onViewOrder(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, OrderDetailActivity.class));

            }
        };
    }

    @NonNull
    public View.OnClickListener onContactCustomer(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CommunicationSingleActivity.class));
            }
        };
    }

}
