package com.stampmemories.sectioned.myorders;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by AppsterBiz on 23-Mar-18.
 */

public class OrderHistoryViewModel extends BaseObservable {
    private Activity context;

    OrderHistoryViewModel(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
    }

    @BindingAdapter("orders_history")
    public static void bindOrdersHistory(@NonNull RecyclerView recyclerView, @NonNull Activity context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new OrderHistoryAdapter(context));

    }
}
