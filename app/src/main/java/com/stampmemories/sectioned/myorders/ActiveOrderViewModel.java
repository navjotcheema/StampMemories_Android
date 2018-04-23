package com.stampmemories.sectioned.myorders;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by AppsterBiz on 23-Mar-18
 */

public class ActiveOrderViewModel extends BaseObservable {
    private Activity context;

    public ActiveOrderViewModel(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
    }

    @BindingAdapter("active_orders")
    public static void bindActiveOrders(@NonNull RecyclerView recyclerView, @NonNull Activity context) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ActiveOrdersAdapter(context));
    }
}
