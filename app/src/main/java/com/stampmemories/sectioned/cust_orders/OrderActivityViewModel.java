package com.stampmemories.sectioned.cust_orders;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by AppsterBiz on 05-Apr-18
 */

public class OrderActivityViewModel extends BaseObservable {


    @BindingAdapter("cust_orders")
    public static void bindCustOrders(@NonNull RecyclerView recyclerView, @NonNull Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new OrdersAdapter((AppCompatActivity) context));

    }

}
