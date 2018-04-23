package com.stampmemories.sectioned.profile;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.ItemTodoBinding;

/**
 * Created by AppsterBiz on 04-Apr-18
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ToDoVH> {
    private Activity context;

    ToDoListAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ToDoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ItemTodoBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_todo, parent, false);
        return new ToDoVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoVH holder, int position) {
        holder.binding.itemViewTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ToDoDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class ToDoVH extends RecyclerView.ViewHolder {
        ItemTodoBinding binding;

        ToDoVH(@NonNull ItemTodoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
