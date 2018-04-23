package com.stampmemories.sectioned.settings;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;

/**
 * Created by AppsterBiz on 05-Apr-18
 */

public class SettingsViewModel extends BaseObservable {
    @NonNull
    private ObservableBoolean isDateSpecific = new ObservableBoolean();

    public SettingsViewModel() {
        isDateSpecific.set(false);
    }

    public boolean getIsDateSpecific() {
        return isDateSpecific.get();
    }

    public void setIsDateSpecific(Boolean value) {
        isDateSpecific.set(value);
        notifyChange();
    }

    @NonNull
    public View.OnClickListener onCloseClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).finish();

            }
        };
    }

    public void oncheckChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)
            setIsDateSpecific(true);
        else
            setIsDateSpecific(false);

    }
}
