package com.stampmemories.sectioned.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.databinding.LayoutProfileDialogBinding;
import com.stampmemories.sectioned.earned_funds.InitiateTransferActivity;
import com.stampmemories.sectioned.myorders.MyOrdersPagerAdapter;
import com.stampmemories.sectioned.myorders.OrderHistoryFragment;

/**
 * Created by AppsterBiz on 04-Apr-18
 */

public class ProfileActivityViewModel extends BaseObservable {

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
    public View.OnClickListener onShareClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutProfileDialogBinding binding = DataBindingUtil.inflate(((AppCompatActivity) context).getLayoutInflater(), R.layout.layout_profile_dialog, null, false);
                builder.setView(binding.getRoot());
                android.support.v7.app.AlertDialog alertDialog = builder.create();
                alertDialog.show();
                binding.setViewModel(new ProfileDialogViewModel(alertDialog));
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

    @BindingAdapter("profile_tabs")
    public static void bindProfileTabs(@NonNull TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter("profile_viewPager")
    public static void bindProfileViewPager(@NonNull ViewPager viewPager, @NonNull Context context) {
        MyOrdersPagerAdapter adapter = new MyOrdersPagerAdapter(((AppCompatActivity) context).getSupportFragmentManager());
        adapter.addFrag(new ToDoFragment(), "TO-DO's");
        adapter.addFrag(new OrderHistoryFragment(), "Order History");
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("todo_list")
    public static void bindToDoList(@NonNull RecyclerView recyclerView, @NonNull Activity context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ToDoListAdapter(context));
    }
}
