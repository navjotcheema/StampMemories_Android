package com.stampmemories.sectioned.invitefriend;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.databinding.ActivityInviteFriendBinding;
import com.stampmemories.interfaces.OnToggleInviteCallback;

public class InviteFriendActivity extends AppCompatActivity implements OnToggleInviteCallback {

    private InviteFriendViewModel inviteFriendViewModel;
    ActivityInviteFriendBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_invite_friend);
        inviteFriendViewModel = new InviteFriendViewModel();
        binding.setInviteFriendViewModel(inviteFriendViewModel);
    }

    @Override
    public void onBackPressed() {
        inviteFriendViewModel.onBackNavigate(this);
    }

    @Override
    public void onToggleLayout(boolean showForm) {
        if (showForm) {
            inviteFriendViewModel.setShowForm(true);
            binding.recycler.setVisibility(View.GONE);
            binding.layoutForm.setVisibility(View.VISIBLE);
        }

    }
}
