package com.stampmemories.sectioned.background_check;

import android.app.Activity;
import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.ItemBcStatusBinding;

public class BcStatusAdapter extends RecyclerView.Adapter {
    private Activity context;

    public BcStatusAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBcStatusBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_bc_status, parent, false);
        return new BcStatusVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View v = context.getLayoutInflater().inflate(R.layout.layout_bc_check_details, null);
                builder.setView(v);
                builder.setNeutralButton("Close", null);
                builder.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class BcStatusVH extends RecyclerView.ViewHolder {
        ItemBcStatusBinding binding;

        public BcStatusVH(@NonNull ItemBcStatusBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
