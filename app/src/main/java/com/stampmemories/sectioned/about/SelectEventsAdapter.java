package com.stampmemories.sectioned.about;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.ItemDateHeaderBinding;
import com.stampmemories.databinding.ItemSelectEventBinding;
import com.stampmemories.sectioned.myorders.ActiveOrdersAdapter;

import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.HEADER;
import static com.stampmemories.sectioned.myorders.ActiveOrdersAdapter.LIST;

public class SelectEventsAdapter extends RecyclerView.Adapter {
    Context context;

    public SelectEventsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        ViewDataBinding dataBinding = null;
        switch (viewType) {
            case HEADER:
                dataBinding = DataBindingUtil.inflate(((AppCompatActivity) context).getLayoutInflater(), R.layout.item_date_header, parent, false);
                viewHolder = new ActiveOrdersAdapter.DateHeaderVH((ItemDateHeaderBinding) dataBinding);
                break;
            case LIST:
                dataBinding = DataBindingUtil.inflate(((AppCompatActivity) context).getLayoutInflater(), R.layout.item_select_event, parent, false);
                viewHolder = new SelectEventsVH((ItemSelectEventBinding) dataBinding);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder.getAdapterPosition() == 0) {
            ((ActiveOrdersAdapter.DateHeaderVH) holder).binding.textView.setText("Activities");
        }
        if (holder.getAdapterPosition() == 9) {
            ((ActiveOrdersAdapter.DateHeaderVH) holder).binding.textView.setText("Experiences");
        }


        if (holder instanceof SelectEventsVH) {
            ((SelectEventsVH) holder).binding.textView24.setText("Activity/Experience " + holder.getAdapterPosition());
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

    static class SelectEventsVH extends RecyclerView.ViewHolder {
        ItemSelectEventBinding binding;

        public SelectEventsVH(@NonNull ItemSelectEventBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
