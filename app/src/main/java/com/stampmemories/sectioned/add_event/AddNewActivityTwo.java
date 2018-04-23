package com.stampmemories.sectioned.add_event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.stampmemories.R;

import static com.stampmemories.sectioned.add_event.AddNewActivity.stateProgressBar;

/**
 * Created by AppsterBiz on 20-Dec-17.
 */

public class AddNewActivityTwo extends Fragment implements View.OnClickListener {

    Button btnNext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_activity_step_two, container, false);
        btnNext = view.findViewById(R.id.btn_next_two);
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
        btnNext.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(@NonNull View view) {
        int id = view.getId();
        if (id == R.id.btn_next_two) {
            if (getActivity() != null)
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.form_fragment_container, new AddNewActivityThree())
                        .addToBackStack(null).commit();
        }
    }
}
