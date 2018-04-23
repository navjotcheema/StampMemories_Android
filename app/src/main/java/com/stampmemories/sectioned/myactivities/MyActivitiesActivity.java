package com.stampmemories.sectioned.myactivities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityMyActivitiesBinding;

public class MyActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyActivitiesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_my_activities);
        binding.setMyActivitiesViewModel(new MyActivitiesViewModel(this));
    }
}
