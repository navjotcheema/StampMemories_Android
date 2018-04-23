package com.stampmemories.sectioned.cust_orders;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityOrdersBinding;

public class OrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOrdersBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_orders);
    }

    public void onOrdersBack(View view) {
        onBackPressed();
    }
}
