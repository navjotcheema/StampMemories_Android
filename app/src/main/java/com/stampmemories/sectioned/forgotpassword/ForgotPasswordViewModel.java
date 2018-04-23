package com.stampmemories.sectioned.forgotpassword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.gson.JsonObject;
import com.stampmemories.core.api.ApiManager;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.core.app.AppKey;
import com.stampmemories.helper.Alert;
import com.stampmemories.interfaces.OnApiResponse;

import java.util.ArrayList;
import java.util.List;

import br.com.ilhasoft.support.validation.Validator;

/**
 * Created by AppsterBiz on 21-Mar-18
 */

public class ForgotPasswordViewModel extends BaseObservable implements OnApiResponse {
    private Activity context;
    @NonNull
    private ObservableField<String> email = new ObservableField<>();
    Validator validator;

    ForgotPasswordViewModel(Activity context, Validator validator) {
        this.context = context;
        this.validator = validator;
    }

    @Nullable
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
        notifyChange();
    }

    @NonNull
    public View.OnClickListener onSendLinkClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validator.validate()) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty(AppKey.EMAIL, getEmail());
                    ApiManager.requestApi(context, ForgotPasswordViewModel.this, AppConstants.API_MODE.FORGOT_PASSWORD, jsonObject);
                }
            }
        };
    }

    @NonNull
    public View.OnClickListener onBackClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();

            }
        };
    }

    @Override
    public void onApiSuccess(AppConstants.API_MODE mode, JsonObject response) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Message");
        builder.setMessage("If your email address is on file your password re-set link has been sent.");
        builder.setNeutralButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
//                openEmailApp();
                Intent intent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN,
                        Intent.CATEGORY_APP_EMAIL);
                if (intent != null)
                    context.startActivity(intent);
                else
                    Alert.showAlert(context, AppKey.MESSAGE, "Please configure your Email");

            }
        });
        builder.create().show();
    }

    @Override
    public void onApiFailed(AppConstants.API_MODE mode, int statusCode, String response) {
        Alert.showAlert(context, "Error", response);
    }

    private void openEmailApp() {
        List<Intent> emailAppLauncherIntents = new ArrayList<>();

        //Intent that only email apps can handle:
        Intent emailAppIntent = new Intent(Intent.ACTION_SENDTO);
        emailAppIntent.setData(Uri.parse("mailto:"));

        PackageManager packageManager = context.getPackageManager();

        //All installed apps that can handle email intent:
        List<ResolveInfo> emailApps = packageManager.queryIntentActivities(emailAppIntent, PackageManager.MATCH_ALL);

        for (ResolveInfo resolveInfo : emailApps) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent launchIntent = packageManager.getLaunchIntentForPackage(packageName);
            emailAppLauncherIntents.add(launchIntent);
        }

        //Create chooser
        Intent chooserIntent = Intent.createChooser(new Intent(), "Select email app:");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, emailAppLauncherIntents.toArray(new Parcelable[emailAppLauncherIntents.size()]));
        if (emailAppIntent.resolveActivity(packageManager) != null)
            context.startActivity(chooserIntent);
        else
            Alert.showAlert(context, AppKey.MESSAGE, "Please configure your Email");
    }
}
