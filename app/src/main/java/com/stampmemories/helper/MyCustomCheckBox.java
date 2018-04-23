package com.stampmemories.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

/**
 * Created by AppsterBiz on 06-Mar-18.
 */

public class MyCustomCheckBox extends AppCompatCheckBox {
    public MyCustomCheckBox(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyCustomCheckBox(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomCheckBox(@NonNull Context context) {
        super(context);
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "poppins_light.ttf");
        setTypeface(tf);
    }
}
