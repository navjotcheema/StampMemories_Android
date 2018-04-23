package com.stampmemories.sectioned.about;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.core.BaseActivity;
import com.stampmemories.databinding.ActivityAboutBinding;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        binding.setAboutViewModel(new AboutActivityViewModel(this));
    }
}
