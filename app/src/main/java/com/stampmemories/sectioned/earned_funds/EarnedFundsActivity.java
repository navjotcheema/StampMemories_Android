package com.stampmemories.sectioned.earned_funds;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityEarnedFundsBinding;

public class EarnedFundsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEarnedFundsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_earned_funds);
        binding.setViewPager(binding.viewPager);
        binding.setViewModel(new EarnedFundsViewModel());
    }
}
