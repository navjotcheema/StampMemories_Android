package com.stampmemories.sectioned.forgotpassword;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityForgotPasswordBinding;

import br.com.ilhasoft.support.validation.Validator;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityForgotPasswordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        Validator validator=new Validator(binding);
        binding.setForgotPasswordViewModel(new ForgotPasswordViewModel(this,validator));

    }
}
