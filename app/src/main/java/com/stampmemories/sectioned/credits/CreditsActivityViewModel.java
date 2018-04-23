package com.stampmemories.sectioned.credits;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by AppsterBiz on 26-Mar-18
 */

public class CreditsActivityViewModel extends BaseObservable {
    private Activity context;

    public CreditsActivityViewModel(Activity context) {
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

    @NonNull
    public View.OnClickListener onBuyCreditsClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, BuyCreditsActivity.class));
            }
        };
    }

    @NonNull
    public View.OnClickListener onLoadMoreClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context.startActivity(new Intent(context, BuyCreditsActivity.class));
            }
        };
    }

    @BindingAdapter("credits")
    public static void bindCreditsHistory(@NonNull RecyclerView recyclerView, @NonNull Activity context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new CreditsHistoryAdapter(context));
        recyclerView.setNestedScrollingEnabled(false);
    }
}
