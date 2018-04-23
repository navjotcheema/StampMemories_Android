package com.stampmemories.helper;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;

/**
 * Created by AppsterBiz on 15-Feb-18.
 */

public class InstantAutoComplete extends AppCompatAutoCompleteTextView {

    public InstantAutoComplete(@NonNull Context context) {
        super(context);
    }

    public InstantAutoComplete(@NonNull Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public InstantAutoComplete(@NonNull Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && getAdapter() != null) {
            performFiltering(getText(), 0);
        }
    }

}
