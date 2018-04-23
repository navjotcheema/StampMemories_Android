package com.stampmemories.sectioned.credits;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.ItemCreditsTransactionBinding;
import com.stampmemories.databinding.ItemDateHeaderBinding;
import com.stampmemories.sectioned.myorders.ActiveOrdersAdapter;

import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.HEADER;
import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.LIST;

/**
 * Created by AppsterBiz on 26-Mar-18
 */

public class CreditsHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public CreditsHistoryAdapter(Activity context) {
        Activity context1 = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case HEADER:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_date_header, parent, false);
                viewHolder = new ActiveOrdersAdapter.DateHeaderVH((ItemDateHeaderBinding) binding);
                break;
            case LIST:
                binding = DataBindingUtil.inflate(inflater, R.layout.item_credits_transaction, parent, false);
                viewHolder = new CreditsHistoryVH((ItemCreditsTransactionBinding) binding);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 || position == 5 || position == 7) ? HEADER : LIST;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class CreditsHistoryVH extends RecyclerView.ViewHolder {
        ItemCreditsTransactionBinding binding;

        public CreditsHistoryVH(@NonNull ItemCreditsTransactionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
