package com.stampmemories.sectioned.myorders;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.FragmentOrderHistoryBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderHistoryFragment extends Fragment {


    public OrderHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentOrderHistoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_history, container, false);
        binding.setOrderHistoryViewModel(new OrderHistoryViewModel(getActivity()));
        return binding.getRoot();
    }

}
