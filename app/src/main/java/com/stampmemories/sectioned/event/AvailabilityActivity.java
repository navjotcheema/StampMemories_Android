package com.stampmemories.sectioned.event;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityAvailabilityBinding;

public class AvailabilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAvailabilityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_availability);
        binding.setAvailabilityViewModel(new AvailabiltyViewModel(this));
    }
}
