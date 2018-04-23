package com.stampmemories.sectioned.dashboard;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.core.app.CommonFunction;
import com.stampmemories.databinding.NavMenuListItemBinding;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 20-Dec-17
 */

public class NavMenuAdapter extends RecyclerView.Adapter<NavMenuAdapter.ViewHolder1> {

    private Activity context;
    private ArrayList<DrawerModel.Drawer.Sub_menu> navMenuList = new ArrayList<>();

    NavMenuAdapter(Activity context, ArrayList<DrawerModel.Drawer.Sub_menu> navMenuList) {
        this.context = context;
        this.navMenuList = navMenuList;
    }

    @NonNull
    @Override
    public ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NavMenuListItemBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.nav_menu_list_item, parent, false);
        return new ViewHolder1(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder1 viewHolder1, int position) {
        final DrawerModel.Drawer.Sub_menu model = navMenuList.get(viewHolder1.getAdapterPosition());
        final MenuItemModel menuItemModel = new MenuItemModel(context, model,
                CommonFunction.getDrawableByName(context, model.getImage()), viewHolder1.getAdapterPosition(), false);
        viewHolder1.binding.setMenuItemModel(menuItemModel);
        viewHolder1.binding.navMenuTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!model.getSub_items().isEmpty())
                    menuItemModel.onExpandableMenuItemClick(viewHolder1.binding.navMenuTitle, menuItemModel.isExpanded, viewHolder1.binding.subMenuRecycler);
                else
                    menuItemModel.onMenuItemClick(viewHolder1.binding.navMenuTitle.getText().toString().trim().toUpperCase());
            }
        });
//        viewHolder1.binding.navMenuTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!model.getSub_items().isEmpty())
//                    menuItemModel.onExpandableMenuItemClick(viewHolder1.binding.navMenuTitle, menuItemModel.isExpanded, viewHolder1.binding.subMenuRecycler);
//                else {
//                    String title = ((TextView) view).getText().toString().trim().toUpperCase();
//                    if (title.equalsIgnoreCase(NavigationMenu.SWITCH_TO_CUSTOMER.toString())) {
//                        DashBoardViewModel.currentUser.set(AppKeys.USER_ROLES.CUSTOMER);
//                        navMenuList.clear();
//                        navMenuList.addAll(CommonFunction.getMenuList(context, false));
//                        notifyDataSetChanged();
//
//                    } else if (title.equalsIgnoreCase(NavigationMenu.SWITCH_TO_PROVIDER.toString())) {
//                        DashBoardViewModel.currentUser.set(AppKeys.USER_ROLES.PROVIDER);
//                        navMenuList.clear();
//                        navMenuList.addAll(CommonFunction.getMenuList(context, true));
//                        notifyDataSetChanged();
//                    } else {
//                        menuItemModel.onMenuItemClick(title);
//                    }
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return navMenuList.size();
    }


    static class ViewHolder1 extends RecyclerView.ViewHolder {
        NavMenuListItemBinding binding;

        private ViewHolder1(@NonNull NavMenuListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
