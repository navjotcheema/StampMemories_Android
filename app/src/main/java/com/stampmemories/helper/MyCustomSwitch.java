package com.stampmemories.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.Switch;

/**
 * Created by AppsterBiz on 06-Mar-18.
 */

public class MyCustomSwitch extends SwitchCompat {
    public MyCustomSwitch(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyCustomSwitch(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomSwitch(@NonNull Context context) {
        super(context);
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "poppins_light.ttf");
        setTypeface(tf);
    }
}
