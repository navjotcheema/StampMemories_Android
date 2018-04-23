package com.stampmemories.core;

import android.support.v7.app.AppCompatActivity;

import com.stampmemories.core.app.CommonFunction;

/**
 * Created by AppsterBiz on 18-Apr-18
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        if (getWindow().getCurrentFocus() != null)
            CommonFunction.hideKeyboard(getWindow().getCurrentFocus().getWindowToken(), this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getWindow().getCurrentFocus() != null)
            CommonFunction.hideKeyboard(getWindow().getCurrentFocus().getWindowToken(), this);
    }
}
