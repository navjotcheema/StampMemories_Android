package com.stampmemories.sectioned.earned_funds;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stampmemories.helper.LockableViewPager;
import com.stampmemories.sectioned.myorders.MyOrdersPagerAdapter;

public class EarnedFundsViewModel extends BaseObservable {


    @NonNull
    public View.OnClickListener onBackClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).onBackPressed();
            }
        };
    }

    @NonNull
    public View.OnClickListener onInitiateClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, InitiateTransferActivity.class));
            }
        };
    }

    @BindingAdapter({"init_tabLayout_earned_funds"})
    public static void bindTabEarnedFunds(@NonNull TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter({"init_viewpager_earned_funds"})
    public static void bindViewPagerEarnedFunds(@NonNull LockableViewPager viewPager, @NonNull Context context) {
        viewPager.setSwipeable(false);
        MyOrdersPagerAdapter adapter = new MyOrdersPagerAdapter(((AppCompatActivity) context).getSupportFragmentManager());
        adapter.addFrag(new FundsTransactionHistory(), "Funds Transaction History");
        adapter.addFrag(new FundTransferHistoryFragment(), "Transfer History");
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("funds_transaction_recycler")
    public static void bindFundsTransactionHistory(@NonNull RecyclerView recyclerView, @NonNull Context context) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new FundsTransactionHistoryAdapter((AppCompatActivity)context));
    }
}
