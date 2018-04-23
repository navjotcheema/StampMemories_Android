package com.stampmemories.sectioned.background_check;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.FragmentBackgroundCheckBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackgroundCheckFragment extends Fragment {


    public BackgroundCheckFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentBackgroundCheckBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_background_check, container, false);
        binding.setBackgroundCheckViewModel(new BackgroundCheckViewModel());
        binding.txtTnc.setText(Html.fromHtml("I agree per the Stamp Memories " +
                "<a href='com.stampmemories.sectioned.purchase.TnCActivity://'>Terms and Conditions</a>" + " to initiate the background check."));
        binding.txtTnc.setClickable(true);
        binding.txtTnc.setMovementMethod(LinkMovementMethod.getInstance());
        return binding.getRoot();
    }

}
