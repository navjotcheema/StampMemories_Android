package com.stampmemories.sectioned.purchase;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivitySpotDetailsBinding;

public class SpotDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySpotDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_spot_details);
        binding.setSpotDetailViewModel(new SpotDetailsViewModel(this));
    }
}
