package com.stampmemories.sectioned.myorders;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.helper.LockableViewPager;

/**
 * Created by AppsterBiz on 23-Mar-18
 */

public class MyOrderViewModel extends BaseObservable {
    private Activity context;
    private ViewPager viewPager;

    MyOrderViewModel(Activity context, ViewPager viewPager) {
        this.context = context;
        this.viewPager = viewPager;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
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

    @BindingAdapter({"init_tabLayout"})
    public static void bindTabLayout(@NonNull TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter("init_viewpager")
    public static void bindTabLayout(@NonNull LockableViewPager viewPager, @NonNull Activity context) {
        viewPager.setSwipeable(false);
        MyOrdersPagerAdapter adapter = new MyOrdersPagerAdapter(((AppCompatActivity) context).getSupportFragmentManager());
        adapter.addFrag(new OrderHistoryFragment(), "Completed Orders");
        adapter.addFrag(new ActiveOrdersFragment(), "Active Orders");
        viewPager.setAdapter(adapter);
    }
}
