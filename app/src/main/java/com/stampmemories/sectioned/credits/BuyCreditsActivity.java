package com.stampmemories.sectioned.credits;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityBuyCreditsBinding;

public class BuyCreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBuyCreditsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_buy_credits);
        binding.setBuyCreditsViewModel(new BuyCreditsViewModel(this));
    }
}
