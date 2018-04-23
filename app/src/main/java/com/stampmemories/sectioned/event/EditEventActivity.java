package com.stampmemories.sectioned.event;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityEditEventBinding;

public class EditEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditEventBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_event);
        binding.setEditEventViewModel(new EditEventViewModel());
    }
}
