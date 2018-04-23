package com.stampmemories.sectioned.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProfileBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setProfileViewModel(new ProfileActivityViewModel());
        binding.setViewPager(binding.viewpagerProfile);
    }
}
