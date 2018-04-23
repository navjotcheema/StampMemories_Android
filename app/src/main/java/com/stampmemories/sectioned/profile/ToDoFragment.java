package com.stampmemories.sectioned.profile;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.FragmentToDoBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFragment extends Fragment {


    public ToDoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentToDoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_to_do, container, false);
        binding.setContext(getActivity());
        return binding.getRoot();
    }

}
