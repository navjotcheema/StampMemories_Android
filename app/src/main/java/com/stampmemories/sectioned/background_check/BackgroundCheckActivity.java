package com.stampmemories.sectioned.background_check;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityBackgroundCheckBinding;
import com.stampmemories.interfaces.OnToggleExportOption;

import static com.stampmemories.core.app.CommonFunction.showExportDialog;

public class BackgroundCheckActivity extends AppCompatActivity implements OnToggleExportOption {
    ActivityBackgroundCheckBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_background_check);
        binding.setViewPager(binding.viewPagerBc);
        binding.btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExportDialog(BackgroundCheckActivity.this);
            }
        });
    }

    public void onBackBgCheck(View view) {
        onBackPressed();
    }

    @Override
    public void showExportIcon(boolean show) {
        if (show) {
            binding.btnExport.setVisibility(View.VISIBLE);
        } else
            binding.btnExport.setVisibility(View.INVISIBLE);

    }
}
