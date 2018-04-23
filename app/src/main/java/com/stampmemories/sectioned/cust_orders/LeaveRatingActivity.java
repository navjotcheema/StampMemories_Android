package com.stampmemories.sectioned.cust_orders;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityLeaveRatingBinding;

public class LeaveRatingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLeaveRatingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_leave_rating);
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
