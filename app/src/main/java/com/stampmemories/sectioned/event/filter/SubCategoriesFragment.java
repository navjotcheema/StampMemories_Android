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
import com.stampmemories.databinding.FragmentSubCategoriesBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoriesFragment extends Fragment {


    public SubCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentSubCategoriesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sub_categories, container, false);
        binding.subCatRecycler.setHasFixedSize(true);
        binding.subCatRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.subCatRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.subCatRecycler.setAdapter(new CategoriesAdapter(getActivity(), false));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((OnSubcategoriesLoaded) getActivity()).changeTitle("Subcategories");
    }

    public interface OnSubcategoriesLoaded {
        void changeTitle(String title);
    }

}
