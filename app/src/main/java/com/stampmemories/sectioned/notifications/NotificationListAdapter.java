package com.stampmemories.sectioned.notifications;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.stampmemories.R;
import com.stampmemories.databinding.ItemDateHeaderBinding;
import com.stampmemories.databinding.ItemNotificationsBinding;
import com.stampmemories.sectioned.myorders.ActiveOrdersAdapter;

import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.HEADER;
import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.LIST;

/**
 * Created by AppsterBiz on 04-Apr-18
 */

public class NotificationListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;

    public NotificationListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        ViewDataBinding dataBinding = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (viewType) {
            case HEADER:
                dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_date_header, parent, false);
                viewHolder = new ActiveOrdersAdapter.DateHeaderVH((ItemDateHeaderBinding) dataBinding);
                break;
            case LIST:
                dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_notifications, parent, false);
                viewHolder = new NotificationsVH((ItemNotificationsBinding) dataBinding);
                break;
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof NotificationsVH) {
            NotificationsVH notificationsVH = (NotificationsVH) holder;
            TextDrawable drawable = TextDrawable.builder().round().build("A", Color.RED);
            notificationsVH.binding.postIcon.setImageDrawable(drawable);
            notificationsVH.binding.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, NotificationDetailActivity.class));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0 || position == 5) ? HEADER : LIST;
    }

    static class NotificationsVH extends RecyclerView.ViewHolder {
        ItemNotificationsBinding binding;

        NotificationsVH(@NonNull ItemNotificationsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
