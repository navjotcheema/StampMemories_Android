package com.stampmemories.sectioned.notifications;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityContactSupportBinding;

public class ContactSupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactSupportBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_contact_support);
        binding.setContactSupportViewModel(new ContactSupportViewModel());
    }
}
