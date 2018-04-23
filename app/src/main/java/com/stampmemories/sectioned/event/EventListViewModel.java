package com.stampmemories.sectioned.event;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.sectioned.dashboard.CategoryExperiencesAdapter;
import com.stampmemories.sectioned.event.filter.FilterActivity;

/**
 * Created by AppsterBiz on 27-Mar-18.
 */

public class EventListViewModel extends BaseObservable {
    private Activity context;
    private boolean showMap = false;

    public EventListViewModel(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
    }

    public boolean isShowMap() {
        return showMap;
    }

    public void setShowMap(boolean showMap) {
        this.showMap = showMap;
        notifyChange();
    }

    @BindingAdapter("all_events")
    public static void bindAllEvents(@NonNull RecyclerView recyclerView, Activity context) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        recyclerView.setAdapter(new CategoryExperiencesAdapter(context, false));
    }

    public Drawable getIcon() {
        return (isShowMap() ? context.getResources().getDrawable(R.drawable.grid) : context.getResources().getDrawable(R.drawable.ic_map_white_24dp));
    }

    @NonNull
    public View.OnClickListener onMapToggleClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowMap()) {
                    setShowMap(false);
                } else
                    setShowMap(true);
            }
        };
    }

    @NonNull
    public View.OnClickListener onFilterClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, FilterActivity.class));
            }
        };
    }
}
