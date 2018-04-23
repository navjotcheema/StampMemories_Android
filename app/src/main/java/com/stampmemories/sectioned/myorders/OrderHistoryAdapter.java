package com.stampmemories.sectioned.myorders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.sectioned.communication.CommunicationSingleActivity;
import com.stampmemories.databinding.ItemDateHeaderBinding;
import com.stampmemories.databinding.ItemOrdersHistoryBinding;
import com.stampmemories.databinding.LayoutOrderDetailBinding;

import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.HEADER;
import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.LIST;

/**
 * Created by AppsterBiz on 23-Mar-18
 */

public class OrderHistoryAdapter extends RecyclerView.Adapter {
    private Activity context;

    public OrderHistoryAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = null;
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case HEADER:
                binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_date_header, parent, false);
                viewHolder = new ActiveOrdersAdapter.DateHeaderVH((ItemDateHeaderBinding) binding);
                break;
            case LIST:
                binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_orders_history, parent, false);
                viewHolder = new OrderHistoryVH((ItemOrdersHistoryBinding) binding);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OrderHistoryVH) {
            ((OrderHistoryVH) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutOrderDetailBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.layout_order_detail, null, false);
                    builder.setView(binding.getRoot());
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    binding.closeDialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    binding.btnDetail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            context.startActivity(new Intent(context, OrderDetailActivity.class));
                        }
                    });
                    binding.btnContactCustomer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            context.startActivity(new Intent(context, CommunicationSingleActivity.class));
                        }
                    });
                    binding.btnIssueRefund.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    });
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 2 || position == 9 || position == 17) {
            return HEADER;
        } else return LIST;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class OrderHistoryVH extends RecyclerView.ViewHolder {
        ItemOrdersHistoryBinding binding;

        public OrderHistoryVH(@NonNull ItemOrdersHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
