package com.stampmemories.sectioned.gift_card;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;

public class ApplyGiftCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_gift_card);
    }

    public void onBackAgc(View view) {
        onBackPressed();
    }
}
