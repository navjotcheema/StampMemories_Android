package com.stampmemories.sectioned.communication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityCommunicationSingleBinding;

public class CommunicationSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCommunicationSingleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_communication_single);
        binding.setCommSingleViewModel(new CommunicationSingleViewModel());
    }
}
