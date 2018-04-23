package com.stampmemories.sectioned.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityLoginBinding;

import br.com.ilhasoft.support.validation.Validator;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        Validator validator = new Validator(binding);
        loginViewModel = new LoginViewModel(this, validator);
        binding.setLoginViewModel(loginViewModel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginViewModel.onActivityResult(requestCode, resultCode, data);
    }
}
