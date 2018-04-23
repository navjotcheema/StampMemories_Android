package com.stampmemories.sectioned.general_information;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.core.BaseActivity;
import com.stampmemories.databinding.ActivityVerifyPhoneBinding;

public class VerifyPhoneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityVerifyPhoneBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_verify_phone);
        binding.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
