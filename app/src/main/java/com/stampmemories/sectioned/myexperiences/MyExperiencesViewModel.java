package com.stampmemories.sectioned.myexperiences;

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
import com.stampmemories.sectioned.myactivities.MyActivitiesAdapter;

import static com.stampmemories.core.app.CommonFunction.showExportDialog;

/**
 * Created by AppsterBiz on 23-Mar-18
 */

public class MyExperiencesViewModel extends BaseObservable {
    private Activity context;

    public MyExperiencesViewModel(Activity context) {
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
    public View.OnClickListener onAddExperiencesClick() {
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

    @BindingAdapter("experiences_recycler")
    public static void bindMyExperiencesRecycler(@NonNull RecyclerView recyclerView, @NonNull Activity context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new MyActivitiesAdapter(context));
        recyclerView.setHasFixedSize(true);
    }
}
