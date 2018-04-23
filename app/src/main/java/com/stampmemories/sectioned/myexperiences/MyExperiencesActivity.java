package com.stampmemories.sectioned.myexperiences;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityMyExperiencesBinding;

public class MyExperiencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyExperiencesBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_my_experiences);
        binding.setMyExperiencesViewModel(new MyExperiencesViewModel(this));
    }
}
