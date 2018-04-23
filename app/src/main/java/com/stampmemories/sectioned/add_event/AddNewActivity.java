package com.stampmemories.sectioned.add_event;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.stampmemories.R;
import com.stampmemories.databinding.ActivityAddNewBinding;

public class AddNewActivity extends AppCompatActivity {
    public static StateProgressBar stateProgressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddNewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new);
        getWindow().setStatusBarColor(getResources().getColor(R.color.green));
        stateProgressBar = findViewById(R.id.state_progress);
        getSupportFragmentManager().beginTransaction().replace(R.id.form_fragment_container, new AddNewActivityOne()).commit();
        binding.backNavAddNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(AddNewActivity.this)
                        .setMessage("Are you sure to cancel adding new activity?")
                        .setCancelable(false)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }
}
