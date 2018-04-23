package com.stampmemories.sectioned.event;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by AppsterBiz on 26-Mar-18.
 */

public class ViewAllViewModel extends BaseObservable {
    private Activity context;
    private boolean showMap = false;

    public ViewAllViewModel(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
    }

    @NonNull
    public View.OnClickListener onBackClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.onBackPressed();
            }
        };
    }

    public boolean isShowMap() {
        return showMap;
    }

    public void setShowMap(boolean showMap) {
        this.showMap = showMap;
    }


}
