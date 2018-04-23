package com.stampmemories.helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;


/**
 * Created by cis on 21/9/16.
 */

public class Alert {

    /*Simple alert msg for user*/
    public static void showAlert(@NonNull final Context mContext, String title, String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg + "");
        alertDialog.setPositiveButton(mContext.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        if (!((Activity) mContext).isFinishing())
            alertDialog.show();
    }

    /* Alert msg with choice for user*/
    public static void showAlert(@NonNull final Context mContext, @NonNull final OnAlertResponse onAlertResponse, String title, String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg + "");
        alertDialog.setPositiveButton(mContext.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {
                dialog.cancel();
                onAlertResponse.onAlertResponse(true);
            }
        });
        alertDialog.setNegativeButton(mContext.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialog, int which) {
                dialog.cancel();
                onAlertResponse.onAlertResponse(false);
            }
        });

        if (!((Activity) mContext).isFinishing())
            alertDialog.show();
    }

//    public static void showAlert(final Context mContext, final OnAlertResponseWithMode onAlertResponse, final AppKeys.ALERT_MODE mode, String title, String msg) {
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
//        alertDialog.setTitle(title);
//        alertDialog.setMessage(msg + "");
//        alertDialog.setPositiveButton(mContext.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//                onAlertResponse.onAlertResponse(mode, true);
//            }
//        });
//        alertDialog.setNegativeButton(mContext.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//                onAlertResponse.onAlertResponse(mode, false);
//            }
//        });
//
//        if (!((Activity) mContext).isFinishing())
//            alertDialog.show();
//    }

    /* Listener from user choice with or without mode*/
    public interface OnAlertResponse {
        void onAlertResponse(boolean isAction);
    }

//    public interface OnAlertResponseWithMode {
//        void onAlertResponse(AppKeys.ALERT_MODE mode, boolean isAction);
//    }

}
