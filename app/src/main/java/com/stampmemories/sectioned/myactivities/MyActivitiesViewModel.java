package com.stampmemories.sectioned.myactivities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stampmemories.sectioned.add_event.AddNewActivity;

import static com.stampmemories.core.app.CommonFunction.showExportDialog;

/**
 * Created by AppsterBiz on 23-Mar-18
 */

public class MyActivitiesViewModel extends BaseObservable {
    private Activity context;

    public MyActivitiesViewModel(Activity context) {
        this.context = context;
    }

    public Activity getContext() {
        return context;
    }

    @NonNull
    public View.OnClickListener onBackClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.onBackPressed();
            }
        };
    }

    @NonNull
    public View.OnClickListener onAddActivitiesClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, AddNewActivity.class));

            }
        };
    }
    @NonNull
    public View.OnClickListener onExportClick(final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExportDialog(context);

            }
        };
    }
    

    @BindingAdapter("activities_recycler")
    public static void bindMyActivitiesRecycler(@NonNull RecyclerView recyclerView, @NonNull Activity context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new MyActivitiesAdapter(context));
        recyclerView.setHasFixedSize(true);
    }

}
