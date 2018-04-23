package com.stampmemories.sectioned.background_check;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.FragmentBcStatusBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class BcStatusFragment extends Fragment {


    public BcStatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentBcStatusBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bc_status, container, false);
        binding.setContext(getActivity());

        return binding.getRoot();
    }

}
