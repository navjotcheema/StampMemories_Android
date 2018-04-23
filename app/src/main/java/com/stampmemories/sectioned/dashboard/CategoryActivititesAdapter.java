package com.stampmemories.sectioned.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.CategoryOptionsItemBinding;
import com.stampmemories.sectioned.event.EventDetailActivity;

/**
 * Created by AppsterBiz on 14-Dec-17.
 */

public class CategoryActivititesAdapter extends RecyclerView.Adapter<CategoryActivititesAdapter.CategoryVH> {
    private Activity context;
    //    private ArrayList<CategoriesResponse.Categories.Activity_categories> list = new ArrayList<>();
    private boolean limitTo4 = false;

//    public CategoryActivititesAdapter(Activity context, ArrayList<CategoriesResponse.Categories.Activity_categories> list, boolean limitTo4) {
//        this.context = context;
//        this.list = list;
//        this.limitTo4 = limitTo4;
//    }

    public CategoryActivititesAdapter(Activity context, boolean limitTo4) {
        this.context = context;
        this.limitTo4 = limitTo4;
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryOptionsItemBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.category_options_item, parent, false);
        return new CategoryVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {
        holder.binding.setContext(context);
        holder.binding.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, EventDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        if (limitTo4)
            return 4;
        else
            return 20;
    }

    public static class CategoryVH extends RecyclerView.ViewHolder {
        CategoryOptionsItemBinding binding;

        CategoryVH(@NonNull CategoryOptionsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }

    }
}