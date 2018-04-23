package com.stampmemories.sectioned.event;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.stampmemories.R;

/**
 * Created by AppsterBiz on 25-Dec-17
 */

public class ActivityDetailMediaAdapter extends RecyclerView.Adapter<ActivityDetailMediaAdapter.MediaVH> {

    Activity context;

    public ActivityDetailMediaAdapter(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ActivityDetailMediaAdapter.MediaVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.media_layout, parent, false);
        return new MediaVH(view);
    }

    @Override
    public void onBindViewHolder(ActivityDetailMediaAdapter.MediaVH holder, int position) {
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().centerCrop()).load(R.drawable.sm_2).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public static class MediaVH extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MediaVH(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_video);
        }
    }
}
