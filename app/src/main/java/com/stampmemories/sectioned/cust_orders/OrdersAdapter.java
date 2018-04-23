package com.stampmemories.sectioned.cust_orders;

import android.app.Activity;
import android.app.AlertDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.ItemDateHeaderBinding;
import com.stampmemories.databinding.ItemOrderCustomerBinding;
import com.stampmemories.databinding.LayoutCustomerOrderDialogBinding;
import com.stampmemories.sectioned.myorders.ActiveOrdersAdapter;

import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.HEADER;
import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.LIST;

/**
 * Created by AppsterBiz on 05-Apr-18
 */

public class OrdersAdapter extends RecyclerView.Adapter {
    private Activity context;

    public OrdersAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding;
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case HEADER:
                dataBinding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_date_header, parent, false);
                viewHolder = new ActiveOrdersAdapter.DateHeaderVH((ItemDateHeaderBinding) dataBinding);
                break;
            case LIST:
                dataBinding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_order_customer, parent, false);
                viewHolder = new OrdersVH((ItemOrderCustomerBinding) dataBinding);
                break;
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OrdersVH) {
            OrdersVH ordersVH = (OrdersVH) holder;
            ordersVH.binding.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    LayoutCustomerOrderDialogBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(),
                            R.layout.layout_customer_order_dialog, null, false);
                    builder.setView(binding.getRoot());
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    binding.setViewModel(new CustomerOrderDialogViewModel(alertDialog));
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
        return (position == 0 || position == 9 ? HEADER : LIST);
    }

    static class OrdersVH extends RecyclerView.ViewHolder {
        ItemOrderCustomerBinding binding;

        public OrdersVH(@NonNull ItemOrderCustomerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
