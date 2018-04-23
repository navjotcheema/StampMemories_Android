package com.stampmemories.sectioned.about;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivitySelectEventsBinding;

public class SelectEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySelectEventsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_select_events);
        binding.setSelectEventViewModel(new SelectEventsViewModel());
    }
}
