package com.stampmemories.sectioned.event;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityViewAllBinding;

public class ViewAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewAllBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_all);
        binding.setViewAllViewModel(new ViewAllViewModel(this));
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, new EventListFragment())
                .commit();
    }
}
