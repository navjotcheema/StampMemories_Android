package com.stampmemories.sectioned.documents;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityDocumentsBinding;

public class DocumentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDocumentsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_documents);
        binding.setDocumentViewModel(new DocumentViewModel(this));
    }
}
