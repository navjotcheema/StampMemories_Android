package com.stampmemories.sectioned.myorders;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.FragmentActiveOrdersBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveOrdersFragment extends Fragment {


    public ActiveOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentActiveOrdersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_active_orders, container, false);
        binding.setActiveOrdersViewModel(new ActiveOrderViewModel(getActivity()));
        return binding.getRoot();
    }

}
