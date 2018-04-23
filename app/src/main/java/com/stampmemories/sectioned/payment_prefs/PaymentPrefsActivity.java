package com.stampmemories.sectioned.payment_prefs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityPaymentPrefsBinding;

import static com.stampmemories.sectioned.dashboard.DashBoardViewModel.currentUser;

public class PaymentPrefsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPaymentPrefsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_prefs);
        binding.setPrefViewModel(new PaymentPrefViewModel(this));
        binding.setCurrentUser(currentUser.get());
    }
}
