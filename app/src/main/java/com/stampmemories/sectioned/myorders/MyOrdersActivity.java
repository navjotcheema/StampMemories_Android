package com.stampmemories.sectioned.myorders;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityMyOrdersBinding;

public class MyOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyOrdersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_my_orders);
        binding.setMyOrdersViewModel(new MyOrderViewModel(this, binding.viewPager));
    }
}
