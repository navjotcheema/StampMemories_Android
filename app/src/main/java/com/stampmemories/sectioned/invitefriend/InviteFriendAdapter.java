package com.stampmemories.sectioned.invitefriend;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.stampmemories.R;
import com.stampmemories.core.app.CommonFunction;
import com.stampmemories.databinding.ItemInviteFriendBinding;
import com.stampmemories.helper.Alert;
import com.stampmemories.interfaces.OnToggleInviteCallback;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 05-Apr-18
 */

public class InviteFriendAdapter extends RecyclerView.Adapter<InviteFriendAdapter.InviteVH> {
    private Activity context;
    private ArrayList<InviteFriendModel> list = new ArrayList<>();

    InviteFriendAdapter(Activity context, ArrayList<InviteFriendModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InviteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInviteFriendBinding binding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.item_invite_friend, parent, false);
        return new InviteVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final InviteVH holder, int position) {
        InviteFriendModel model = list.get(holder.getAdapterPosition());
        holder.binding.title.setText(model.getTitle());
        holder.binding.subTitle.setText(model.getSubTitle());
        Glide.with(context).load(CommonFunction.getDrawableByName(context, model.getTitle().toLowerCase())).into(holder.binding.icon);
        holder.binding.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getAdapterPosition() == 0) {
                    ((OnToggleInviteCallback) context).onToggleLayout(true);
                } else if (holder.getAdapterPosition() == 1) {
                    Alert.showAlert(context, "STATUS", "Google SDK will be implemented");
                } else if (holder.getAdapterPosition() == 2) {
                    Alert.showAlert(context, "STATUS", "Facebook Dialog SDK will be implemented");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class InviteVH extends RecyclerView.ViewHolder {
        ItemInviteFriendBinding binding;

        public InviteVH(@NonNull ItemInviteFriendBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
