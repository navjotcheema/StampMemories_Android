package com.stampmemories.sectioned.myactivities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stampmemories.R;
import com.stampmemories.sectioned.event.EventDetailActivity;

/**
 * Created by AppsterBiz on 07-Mar-18.
 */

public class MyActivitiesAdapter extends RecyclerView.Adapter<MyActivitiesAdapter.MyEventsViewHolder> {
    private Activity context;

    public MyActivitiesAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyActivitiesAdapter.MyEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_activities, parent, false);
        return new MyEventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyActivitiesAdapter.MyEventsViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Select an action");
                builder.setNeutralButton("VIEW ROSTER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.startActivity(new Intent(context, RosterActivity.class));
                    }
                });
                builder.setNegativeButton("UNPOST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(context, "Unposted", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        context.startActivity(new Intent(context, EventDetailActivity.class).putExtra("isEditable", true));
                    }
                });
                builder.create().show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class MyEventsViewHolder extends RecyclerView.ViewHolder {
        public MyEventsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
