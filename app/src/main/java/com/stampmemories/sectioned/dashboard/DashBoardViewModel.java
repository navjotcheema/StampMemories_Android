package com.stampmemories.sectioned.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stampmemories.R;
import com.stampmemories.core.app.AppConstants.USER_ROLES;
import com.stampmemories.sectioned.event.ViewAllActivity;
import com.stampmemories.sectioned.location.LocationActivity;

import java.util.ArrayList;

import static com.stampmemories.core.app.CommonFunction.getMenuList;

/**
 * Created by AppsterBiz on 21-Mar-18
 */

public class DashBoardViewModel extends BaseObservable {
    private Activity context;
    @NonNull
    public static ObservableField<Enum> currentUser = new ObservableField<>();

    private ObservableArrayList<HomeEventsModel.Activities> activities = new ObservableArrayList<>();
    private ObservableArrayList<HomeEventsModel.Experiences> experiences = new ObservableArrayList<>();

    public DashBoardViewModel(Activity context) {
        this.context = context;
    }


    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    @NonNull
    public ObservableField<Enum> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Enum user) {
        currentUser.set(user);
        notifyChange();

    }

    public ObservableArrayList<HomeEventsModel.Activities> getActivities() {
        return activities;
    }

    public void setActivities(ObservableArrayList<HomeEventsModel.Activities> activities) {
        this.activities = activities;
        notifyChange();
    }

    public ObservableArrayList<HomeEventsModel.Experiences> getExperiences() {
        return experiences;
    }

    public void setExperiences(ObservableArrayList<HomeEventsModel.Experiences> experiences) {
        this.experiences = experiences;
        notifyChange();
    }

    public ArrayList<DrawerModel.Drawer.Sub_menu> getDrawer(Enum user) {
        if (user == USER_ROLES.GUEST) {
            return getMenuList(context, false);
        } else {
            if (user == USER_ROLES.CUSTOMER) {
                return getMenuList(context, false);
            } else
                return getMenuList(context, true);
        }
    }

    @BindingAdapter({"activities"})
    public static void activities(@NonNull TabLayout tabLayout, final ObservableArrayList<HomeEventsModel.Activities> activities) {

        for (int i = 0; i < activities.size(); i++)
            tabLayout.addTab(tabLayout.newTab().setText(activities.get(i).getParentCaegoryName()));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Toast.makeText(context, tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @BindingAdapter({"experiences"})
    public static void experiences(@NonNull TabLayout tabLayout, final ObservableArrayList<HomeEventsModel.Experiences> experiences) {

        for (int i = 0; i < experiences.size(); i++)
            tabLayout.addTab(tabLayout.newTab().setText(experiences.get(i).getParentCaegoryName()));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Toast.makeText(context, tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @BindingAdapter("activities_list")
    public static void bindActivitiesList(@NonNull RecyclerView recyclerView, Activity context) {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(new CategoryActivititesAdapter(context, true));
        recyclerView.setHasFixedSize(true);
    }

    @BindingAdapter("experiences_list")
    public static void bindExperiencesList(@NonNull RecyclerView recyclerView, Activity context) {
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(new CategoryExperiencesAdapter(context, true));
        recyclerView.setHasFixedSize(true);
    }

    @BindingAdapter("init_drawer")
    public static void bindDrawer(@NonNull final DrawerLayout drawerLayout, final Activity context) {
        final ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(context, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @NonNull
    private static ArrayList<ParentActivitiesModel> getStringList() {
        ArrayList<ParentActivitiesModel> list = new ArrayList<>();
        list.add(new ParentActivitiesModel(1, "Lesson", true));
        list.add(new ParentActivitiesModel(2, "Music", false));
        list.add(new ParentActivitiesModel(3, "Tutor", false));
        list.add(new ParentActivitiesModel(4, "Sports", false));
        list.add(new ParentActivitiesModel(5, "Dance", false));
        list.add(new ParentActivitiesModel(6, "Writing", false));
        list.add(new ParentActivitiesModel(7, "Photoshoot", false));
        return list;
    }
    /* method to handle opening and
        closing drawer on hamburger icon click
        @param drawerLayout, @param openDrawer as boolean, to know whether to open or close the drawer
     */

    void toggleDrawer(@NonNull DrawerLayout drawerLayout, boolean openDrawer) {
        if (openDrawer)
            drawerLayout.openDrawer(GravityCompat.START);
        else
            drawerLayout.closeDrawer(GravityCompat.START);
    }

    /* method to toggle user menu between Customer & Provider
     * user roles as enum
     * USER_ROLES.CUSTOMER= Customer
     * USER_ROLES.PROVIDER= Provider
     * */
    void toggleUser(NavigationView navigationView, USER_ROLES userRole) {
        boolean isProvider = false;
        boolean isGuest = false;
        boolean isCustomer = true;
        if (userRole == USER_ROLES.CUSTOMER) {
            isCustomer = true;
            isGuest = false;
            isProvider = false;
        } else if (userRole == USER_ROLES.PROVIDER) {
            isProvider = true;
            isGuest = false;
            isCustomer = false;
        }

    }

    /*Bind NavigationDrawer Menu*/
    @BindingAdapter({"menu_recycler", "menu_list"})
    public static void bindNavigationMenu(@NonNull RecyclerView recyclerView, Activity context, ArrayList<DrawerModel.Drawer.Sub_menu> navMenuList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
//        ArrayList<DrawerModel.Drawer.Sub_menu> navMenuList = new ArrayList<>();
        NavMenuAdapter adapter = new NavMenuAdapter(context, navMenuList);

        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("src")
    public static void bindImages(@NonNull ImageView imageView, @NonNull Activity context) {
        Glide.with(context).load(context.getResources().getDrawable(R.drawable.sm_5)).into(imageView);
    }

    @NonNull
    public View.OnClickListener onShowAllClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ViewAllActivity.class));
            }
        };
    }

    @NonNull
    public View.OnClickListener onLocationClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, LocationActivity.class));
            }
        };
    }

}
