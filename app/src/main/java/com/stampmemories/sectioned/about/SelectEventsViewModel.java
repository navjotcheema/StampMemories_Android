package com.stampmemories.sectioned.about;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SelectEventsViewModel extends ViewModel {

    @NonNull
    public View.OnClickListener onCloseClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).finish();
            }
        };
    }

    @BindingAdapter("all_event_types")
    public static void bindAllEventTypes(@NonNull RecyclerView recyclerView, @NonNull Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SelectEventsAdapter(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

    }
}
