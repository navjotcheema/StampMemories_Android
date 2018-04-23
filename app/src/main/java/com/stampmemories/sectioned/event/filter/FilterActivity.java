package com.stampmemories.sectioned.event.filter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityFilterBinding;

public class FilterActivity extends AppCompatActivity implements SubCategoriesFragment.OnSubcategoriesLoaded {
    ActivityFilterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter);
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.filter_container, new CategoriesFragment()).commit();
    }

    @Override
    public void changeTitle(String title) {
        binding.textView3.setText(title);
    }
}
