package com.stampmemories.sectioned.notifications;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityNotificationsBinding;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNotificationsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_notifications);
        binding.setNotificationsViewModel(new NotificationsViewModel());
        binding.setContext(this);
    }
}
