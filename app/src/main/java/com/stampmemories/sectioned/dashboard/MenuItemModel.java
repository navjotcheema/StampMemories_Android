package com.stampmemories.sectioned.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stampmemories.R;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.core.app.AppKey;
import com.stampmemories.helper.AppPreference;
import com.stampmemories.helper.SimpleDividerItemDecoration;
import com.stampmemories.interfaces.DrawerAction;
import com.stampmemories.sectioned.change_password.ChangePasswordActivity;
import com.stampmemories.sectioned.faq.FaqActivity;
import com.stampmemories.sectioned.invitefriend.InviteFriendActivity;
import com.stampmemories.sectioned.login.LoginActivity;
import com.stampmemories.sectioned.notifications.NotificationsActivity;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 22-Mar-18
 */

public class MenuItemModel extends BaseObservable {
    private Activity context;
    private DrawerModel.Drawer.Sub_menu navMenu;
    private Drawable menuIcon;
    private int position = 0;
    boolean isExpanded = false;

    MenuItemModel(Activity context, DrawerModel.Drawer.Sub_menu navMenu, Drawable menuIcon, int position, boolean isExpanded) {
        this.context = context;
        this.navMenu = navMenu;
        this.menuIcon = menuIcon;
        this.position = position;
        this.isExpanded = isExpanded;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public String getMenuTitle() {
        return navMenu.getTitle();
    }

    public boolean getHasSubMenu() {
        return (navMenu.getSub_items().size() > 0);
    }

    public ArrayList<String> getSubMenu() {
        return navMenu.getSub_items();
    }

    public Drawable getMenuIcon() {
        return menuIcon;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @BindingAdapter({"submenu_list", "sub_menu"})
    public static void bindSubmenuList(@NonNull RecyclerView recyclerView, @NonNull Activity context, ArrayList<String> submenuList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new NavSubMenuAdapter(context, submenuList));
    }

    @BindingAdapter("set_right_arrow")
    public static void bindRightArrow(@NonNull TextView textView, boolean hasSubMenu) {
        if (hasSubMenu) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_right_white_24dp, 0);
        }
    }

    @BindingAdapter({"src", "context"})
    public static void loadDrawerIcons(@NonNull ImageView imageView, Drawable drawableRes, @NonNull Activity context) {
        Glide.with(context).load(drawableRes).into(imageView);
    }

    void onExpandableMenuItemClick(@NonNull TextView textView, boolean isExpanded, @NonNull RecyclerView recyclerView) {
        if (!isExpanded) {
            this.isExpanded = true;
            recyclerView.setVisibility(View.VISIBLE);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_white_24dp, 0);
        } else {
            this.isExpanded = false;
            recyclerView.setVisibility(View.GONE);
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_right_white_24dp, 0);
        }

    }

    void onMenuItemClick(@NonNull String menuTitle) {
        ((DrawerAction) context).closeDrawer();
        ((DrawerAction) context).hideKeyboard();
        switch (menuTitle) {
            case "HOME":
                break;
            case "COMMUNICATION":
                break;
            case "NOTIFICATIONS":
                getContext().startActivity(new Intent(getContext(), NotificationsActivity.class));
                break;
            case "INVITE A FRIEND":
                getContext().startActivity(new Intent(getContext(), InviteFriendActivity.class));
                break;
            case "FAQ":
                getContext().startActivity(new Intent(getContext(), FaqActivity.class));
                break;
            case "BECOME A PROVIDER":
                break;
            case "SWITCH TO CUSTOMER":
                DashBoardViewModel.currentUser.set(AppConstants.USER_ROLES.CUSTOMER);
                break;
            case "SWITCH TO PROVIDER":
                DashBoardViewModel.currentUser.set(AppConstants.USER_ROLES.PROVIDER);
                break;
            case "CHANGE PASSWORD":
                context.startActivity(new Intent(context, ChangePasswordActivity.class));
                break;
            case "LOGIN":
                navToLogin();
                break;
            case "LOGOUT":
                AppPreference.getInstance(context).putBoolean(AppKey.IS_LOGGED_IN, false);
                navToLogin();
                break;
        }

    }

    private void navToLogin() {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        context.finish();
    }

}
