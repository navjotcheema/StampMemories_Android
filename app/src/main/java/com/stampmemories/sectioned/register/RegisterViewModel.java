package com.stampmemories.sectioned.register;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by AppsterBiz on 21-Mar-18
 */

public class RegisterViewModel extends BaseObservable {
    @NonNull
    private ObservableBoolean isIndividual = new ObservableBoolean(true);
    @NonNull
    private ObservableBoolean isCustomer = new ObservableBoolean();
    @NonNull
    private ObservableBoolean isSocial = new ObservableBoolean(false);
    @NonNull
    private ObservableField<String> registerAs = new ObservableField<>();
    @NonNull
    private ObservableField<String> areYouA = new ObservableField<>();

    public boolean getIsIndividual() {
        return isIndividual.get();
    }

    void setIsIndividual(boolean isIndividual) {
        this.isIndividual.set(isIndividual);
        notifyChange();
    }

    public boolean getIsCustomer() {
        return isCustomer.get();
    }

    public void setIsCustomer(boolean isCustomer) {
        this.isCustomer.set(isCustomer);
        notifyChange();
    }

    @Nullable
    public String getRegisterAs() {
        return registerAs.get();
    }

    public void setRegisterAs(String registerAs) {
        this.registerAs.set(registerAs);
        notifyChange();
    }

    @Nullable
    public String getAreYouA() {
        return areYouA.get();
    }

    public void setAreYouA(String areYouA) {
        this.areYouA.set(areYouA);
        notifyChange();
    }

    public boolean getIsSocial() {
        return isSocial.get();
    }

    public void setIsSocial(boolean isSocial) {
        this.isSocial.set(isSocial);
        notifyChange();
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
