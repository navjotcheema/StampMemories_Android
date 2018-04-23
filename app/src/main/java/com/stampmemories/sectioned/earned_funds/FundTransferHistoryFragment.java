package com.stampmemories.sectioned.earned_funds;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.FragmentFundTransferHistoryBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class FundTransferHistoryFragment extends Fragment {


    public FundTransferHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentFundTransferHistoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fund_transfer_history, container, false);
        binding.setContext(getActivity());
        return binding.getRoot();
    }

}
