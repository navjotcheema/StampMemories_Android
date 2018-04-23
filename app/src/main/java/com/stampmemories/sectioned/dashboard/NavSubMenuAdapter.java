package com.stampmemories.sectioned.dashboard;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stampmemories.R;
import com.stampmemories.databinding.NavSubMenuListItemBinding;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 20-Dec-17
 */

public class NavSubMenuAdapter extends RecyclerView.Adapter<NavSubMenuAdapter.MySubMenuVH> {

    private Activity context;
    private ArrayList<String> navSubMenuList = new ArrayList<>();

    NavSubMenuAdapter(Activity context, ArrayList<String> navSubMenuList) {
        this.context = context;
        this.navSubMenuList = navSubMenuList;
    }


    @NonNull
    @Override
    public MySubMenuVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NavSubMenuListItemBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.nav_sub_menu_list_item, parent, false);
        return new MySubMenuVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MySubMenuVH holder, int position) {
        final SubMenuItemModel model = new SubMenuItemModel(context, navSubMenuList.get(holder.getAdapterPosition()));
        holder.binding.setSubMenuModel(model);
        holder.binding.navSubMenuTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull View view) {
                model.onSubmenuItemClick(((TextView) view).getText().toString().trim().toUpperCase());
            }
        });

    }

    @Override
    public int getItemCount() {
        return navSubMenuList.size();
    }

    static class MySubMenuVH extends RecyclerView.ViewHolder {
        NavSubMenuListItemBinding binding;

        private MySubMenuVH(@NonNull NavSubMenuListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
