package com.stampmemories.sectioned.background_check;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;

import com.stampmemories.R;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.interfaces.OnToggleExportOption;
import com.stampmemories.sectioned.myorders.MyOrdersPagerAdapter;

public class BackgroundCheckViewModel extends BaseObservable {
    @NonNull
    private ObservableField<Enum> payment_option = new ObservableField<>();

    public BackgroundCheckViewModel() {
        payment_option.set(AppConstants.PAYMENT_OPTION.EXISTING_CARD);
    }

    public void onCheckChanged(RadioGroup radioGroup, int id) {
        if (id == R.id.rb_pay_with_card_sgc) {
            setPayment_option(AppConstants.PAYMENT_OPTION.EXISTING_CARD);

        } else if (id == R.id.rb_pay_with_another_card_sgc) {
            setPayment_option(AppConstants.PAYMENT_OPTION.ADD_NEW_CARD);
        }

    }

    @Nullable
    public Enum getPayment_option() {
        return payment_option.get();
    }

    public void setPayment_option(Enum option) {
        payment_option.set(option);
        notifyChange();
    }

    @BindingAdapter({"tab_bc", "context"})
    public static void bindBCTab(@NonNull TabLayout tabLayout, ViewPager viewPager, @NonNull final Context context) {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    ((OnToggleExportOption) context).showExportIcon(true);
                }
                else
                    ((OnToggleExportOption) context).showExportIcon(false);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @BindingAdapter("viewpager_BC")
    public static void bindBCViewPager(@NonNull ViewPager viewPager, @NonNull Context context) {

        MyOrdersPagerAdapter adapter = new MyOrdersPagerAdapter(((AppCompatActivity) context).getSupportFragmentManager());
        adapter.addFrag(new BackgroundCheckFragment(), "Check");
        adapter.addFrag(new BcStatusFragment(), "Status");
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter("list_bc_status")
    public static void bindBcStatusList(@NonNull RecyclerView recyclerView, Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new BcStatusAdapter(((AppCompatActivity) context)));
    }
}
