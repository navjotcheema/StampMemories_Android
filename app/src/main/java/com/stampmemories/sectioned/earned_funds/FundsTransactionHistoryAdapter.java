package com.stampmemories.sectioned.earned_funds;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.ItemDateHeaderBinding;
import com.stampmemories.databinding.ItemFundTransactionHistoryBinding;
import com.stampmemories.databinding.LayoutEarnedFundsDialogBinding;
import com.stampmemories.sectioned.myorders.ActiveOrdersAdapter;

import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.HEADER;
import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.LIST;

public class FundsTransactionHistoryAdapter extends RecyclerView.Adapter {
    private Activity context;

    FundsTransactionHistoryAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = null;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case HEADER:
                dataBinding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_date_header, parent, false);
                viewHolder = new ActiveOrdersAdapter.DateHeaderVH((ItemDateHeaderBinding) dataBinding);
                break;
            case LIST:
                dataBinding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_fund_transaction_history, parent, false);
                viewHolder = new FundsTransactionHistoryVH((ItemFundTransactionHistoryBinding) dataBinding);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FundsTransactionHistoryVH) {
            FundsTransactionHistoryVH vh = (FundsTransactionHistoryVH) holder;
            vh.binding.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutEarnedFundsDialogBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.layout_earned_funds_dialog, null, false);
                    builder.setView(binding.getRoot());
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    binding.setViewModel(new EarnedFundsDialogViewModel(dialog));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 || position == 6) ? HEADER : LIST;
    }

    static class FundsTransactionHistoryVH extends RecyclerView.ViewHolder {
        ItemFundTransactionHistoryBinding binding;

        public FundsTransactionHistoryVH(@NonNull ItemFundTransactionHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
