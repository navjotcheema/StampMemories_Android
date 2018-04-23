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
import com.stampmemories.databinding.ItemActiveOrdersBinding;
import com.stampmemories.databinding.ItemDateHeaderBinding;
import com.stampmemories.databinding.LayoutOrderDetailBinding;
import com.stampmemories.sectioned.communication.CommunicationSingleActivity;

/**
 * Created by AppsterBiz on 23-Mar-18
 */

public class ActiveOrdersAdapter extends RecyclerView.Adapter {

    private Activity context;
    public static final int HEADER = 0;
    public static final int LIST = 1;


    public ActiveOrdersAdapter(Activity context) {
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
                return new DateHeaderVH((ItemDateHeaderBinding) binding);
            case LIST:
                binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_active_orders, parent, false);
                return new ActiveOrdersVH((ItemActiveOrdersBinding) binding);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ActiveOrdersVH) {
            ((ActiveOrdersVH) holder).itemView.setOnClickListener(new View.OnClickListener() {
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
        if (position == 0 || position == 5 || position == 10 || position == 13) {
            return HEADER;
        } else return LIST;

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class ActiveOrdersVH extends RecyclerView.ViewHolder {
        ItemActiveOrdersBinding binding;
        View itemView;

        public ActiveOrdersVH(@NonNull ItemActiveOrdersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView = binding.getRoot();
        }
    }

    public static class DateHeaderVH extends RecyclerView.ViewHolder {
        public ItemDateHeaderBinding binding;

        public DateHeaderVH(@NonNull ItemDateHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
