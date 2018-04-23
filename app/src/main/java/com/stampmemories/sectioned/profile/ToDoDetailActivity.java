package com.stampmemories.sectioned.profile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.sectioned.communication.CommunicationSingleActivity;
import com.stampmemories.databinding.ActivityToDoDetailBinding;
import com.stampmemories.sectioned.event.EventDetailActivity;

public class ToDoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityToDoDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_to_do_detail);
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EventDetailActivity.class).putExtra("isEditable", true));
            }
        });
        binding.btnCommu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CommunicationSingleActivity.class));
            }
        });
        binding.btnMarkComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ToDoDetailActivity.this);
                View v = LayoutInflater.from(ToDoDetailActivity.this).inflate(R.layout.layout_todo_confirmation, null);
                builder.setView(v);
                builder.create().show();
            }
        });

    }
}
