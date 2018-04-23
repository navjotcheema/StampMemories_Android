package com.stampmemories.sectioned.credits;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityCreditsBinding;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCreditsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_credits);
        binding.setCreditsViewModel(new CreditsActivityViewModel(this));
    }
}
