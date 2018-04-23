package com.stampmemories.sectioned.event.filter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.stampmemories.R;
import com.stampmemories.databinding.ItemCategoriesBinding;

/**
 * Created by AppsterBiz on 27-Mar-18
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesVH> {

    private Activity context;
    private boolean hasSubcategories = true;

    public CategoriesAdapter(Activity context) {
        this.context = context;
    }

    CategoriesAdapter(Activity context, boolean hasSubcategories) {
        this.context = context;
        this.hasSubcategories = false;
    }

    @NonNull
    @Override
    public CategoriesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoriesBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_categories, parent, false);
        return new CategoriesVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesVH holder, int position) {
        holder.binding.subcatItemImageProgress.setVisibility(View.INVISIBLE);
        holder.binding.subcatItemTitle.setText("Lesson");
        Glide.with(context).load(context.getResources().getDrawable(R.drawable.sm_3)).into(holder.binding.subcatItemImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hasSubcategories)
                {
                    ((AppCompatActivity)context).getSupportFragmentManager()
                            .beginTransaction().replace(R.id.filter_container,new SubCategoriesFragment())
                            .addToBackStack(null).commit();
                }
                else
                    context.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class CategoriesVH extends RecyclerView.ViewHolder {
        ItemCategoriesBinding binding;
        View itemView;

        CategoriesVH(@NonNull ItemCategoriesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView = binding.getRoot();
        }
    }
}
