package com.stampmemories.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by AppsterBiz on 08-Feb-18
 */

public class LockableViewPager extends ViewPager {
    private boolean swipeable;


    public LockableViewPager(@NonNull Context context) {

        super(context);

    }


    public LockableViewPager(@NonNull Context context, AttributeSet attrs) {

        super(context, attrs);

        this.swipeable = true;

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.swipeable && super.onTouchEvent(event);


    }


    @Override

    public boolean onInterceptTouchEvent(MotionEvent event) {

        return this.swipeable && super.onInterceptTouchEvent(event);

    }

    public void setSwipeable(boolean swipeable) {

        this.swipeable = swipeable;

    }

}

