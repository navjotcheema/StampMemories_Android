package com.stampmemories.sectioned.event.filter;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stampmemories.R;
import com.stampmemories.databinding.FragmentCategoriesBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment {


    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentCategoriesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false);

        binding.catRecycler.setHasFixedSize(true);
        binding.catRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.catRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.catRecycler.setAdapter(new CategoriesAdapter(getActivity()));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((SubCategoriesFragment.OnSubcategoriesLoaded) getActivity()).changeTitle("Categories");
    }
}
