package com.stampmemories.sectioned.invitefriend;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stampmemories.core.app.AppConstants;

import static com.stampmemories.core.app.CommonFunction.getInviteTypes;
import static com.stampmemories.sectioned.dashboard.DashBoardViewModel.currentUser;

/**
 * Created by AppsterBiz on 21-Mar-18
 */

public class InviteFriendViewModel extends BaseObservable {
    @NonNull
    private ObservableField<Boolean> showForm = new ObservableField<>();

    InviteFriendViewModel() {
        setShowForm(getCurrentUser() == AppConstants.USER_ROLES.PROVIDER);
    }


    @Nullable
    public Enum getCurrentUser() {
        return currentUser.get();
    }

    @Nullable
    public Boolean getShowForm() {
        return showForm.get();
    }

    void setShowForm(Boolean showForm) {
        this.showForm.set(showForm);
        notifyChange();
    }

    @NonNull
    public View.OnClickListener onBackClick(@NonNull final Context context) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) context).onBackPressed();

            }
        };
    }

    @BindingAdapter("invite_methods")
    public static void bindInvitemethods(@NonNull RecyclerView recyclerView, @NonNull Context context) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        Activity activity = (AppCompatActivity) context;
        recyclerView.setAdapter(new InviteFriendAdapter((activity), getInviteTypes(activity)));
    }

    public void onBackNavigate(@NonNull Activity activity) {
        if (getShowForm() && getCurrentUser() == AppConstants.USER_ROLES.CUSTOMER) {
            setShowForm(false);
        } else {
            activity.finish();
        }
    }
}
