package com.stampmemories.sectioned.dashboard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.core.BaseActivity;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.core.app.CommonFunction;
import com.stampmemories.databinding.ActivityDashboardBinding;
import com.stampmemories.interfaces.DrawerAction;
import com.stampmemories.sectioned.profile.ProfileActivity;
import com.stampmemories.sectioned.settings.SettingsActivity;

public class Dashboard extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, DrawerAction {
    DashBoardViewModel dashBoardViewModel;
    ActivityDashboardBinding binding;
    @Nullable
    ActionBar actionBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        setSupportActionBar(binding.customToolbar);
        dashBoardViewModel = new DashBoardViewModel(this);
        DashBoardViewModel.currentUser.set(AppConstants.USER_ROLES.CUSTOMER);
        binding.setDashboardViewModel(dashBoardViewModel);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_menu_options));
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            return true;
        }
        if (id == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
                dashBoardViewModel.toggleDrawer(binding.drawerLayout, false);
            else
                dashBoardViewModel.toggleDrawer(binding.drawerLayout, true);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        return true;
    }

    @Override
    public void closeDrawer() {
        if (binding.drawerLayout != null && binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START);

    }

    @Override
    public void hideKeyboard() {
        if (getWindow().getCurrentFocus() != null)
            CommonFunction.hideKeyboard(getWindow().getCurrentFocus().getWindowToken(), this);
    }

    public void onProfileClick(View view) {
        closeDrawer();
        startActivity(new Intent(this, ProfileActivity.class));
    }

}
