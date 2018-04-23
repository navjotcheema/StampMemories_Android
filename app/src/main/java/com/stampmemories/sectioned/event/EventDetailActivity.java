package com.stampmemories.sectioned.event;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bumptech.glide.Glide;
import com.stampmemories.R;
import com.stampmemories.databinding.ActivityEventDetailBinding;

public class EventDetailActivity extends AppCompatActivity {

    boolean isEditable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEventDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail);
        if (getIntent().getExtras() != null) {
            isEditable = getIntent().getBooleanExtra("isEditable", false);
        }
        if (isEditable)
            binding.fabEdit.setVisibility(View.VISIBLE);
        else
            binding.fabEdit.setVisibility(View.GONE);
        Glide.with(this).load(getResources().getDrawable(R.drawable.sm_3)).into(binding.image);
        binding.btnBookslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AvailabilityActivity.class));
            }
        });
        binding.btnMoreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MoreInfoActivity.class));
            }
        });
        binding.videosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.videosRecyclerView.setHasFixedSize(true);
        binding.videosRecyclerView.setAdapter(new ActivityDetailMediaAdapter(this));
        binding.textSeeAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ReviewsActivity.class));
            }
        });
        binding.imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditEventActivity.class));
            }
        });
    }
}
