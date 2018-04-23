package com.stampmemories.sectioned.change_password;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.core.BaseActivity;
import com.stampmemories.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChangePasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        binding.setChangePasswordViewModel(new ChangePasswordViewModel());
    }
}
