package com.stampmemories.sectioned.event;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.stampmemories.R;
import com.stampmemories.sectioned.purchase.PurchaseDetailActivity;

/**
 * Created by AppsterBiz on 26-Mar-18
 */

public class EventsAvailabilityAdapter extends RecyclerView.Adapter<EventsAvailabilityAdapter.EventsAvailabiltyVH> {
    private Activity context;

    EventsAvailabilityAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EventsAvailabiltyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = context.getLayoutInflater().inflate(R.layout.item_event_availability, parent, false);
        return new EventsAvailabiltyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAvailabiltyVH holder, int position) {
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PurchaseDetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class EventsAvailabiltyVH extends RecyclerView.ViewHolder {
        Button book;

        EventsAvailabiltyVH(@NonNull View itemView) {
            super(itemView);
            book = itemView.findViewById(R.id.buttonBook);
        }
    }

}
