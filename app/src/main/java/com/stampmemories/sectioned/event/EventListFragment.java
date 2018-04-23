package com.stampmemories.sectioned.event;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.FragmentEventListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventListFragment extends Fragment {


    public EventListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentEventListBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false);
        binding.setEventListViewModel(new EventListViewModel(getActivity()));
        return binding.getRoot();
    }


}
