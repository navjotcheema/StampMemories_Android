package com.stampmemories.sectioned.gift_card;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivitySendGiftCardBinding;

public class SendGiftCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySendGiftCardBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_send_gift_card);
        binding.setSendGiftCardViewModel(new SendGiftCardViewModel());
    }

    public void onBackSgc(View view) {
        onBackPressed();
    }
}
