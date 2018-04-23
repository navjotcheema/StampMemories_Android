package com.stampmemories.sectioned.about;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.core.BaseActivity;
import com.stampmemories.databinding.ActivitySelectAgeGroupBinding;

public class SelectAgeGroupActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySelectAgeGroupBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_select_age_group);
        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
