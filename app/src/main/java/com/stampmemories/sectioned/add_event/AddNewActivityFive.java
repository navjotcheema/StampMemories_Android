package com.stampmemories.sectioned.add_event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.stampmemories.R;

import static com.stampmemories.sectioned.add_event.AddNewActivity.stateProgressBar;

/**
 * Created by AppsterBiz on 20-Dec-17.
 */

public class AddNewActivityFive extends Fragment implements View.OnClickListener {

    Button btnSubmit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_activity_step_five, container, false);
        btnSubmit = view.findViewById(R.id.btn_submit_five);
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FIVE);
        btnSubmit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(@NonNull View view) {
        int id = view.getId();
        if (id == R.id.btn_submit_five) {
            Toast.makeText(getActivity(), "Complete", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < getActivity().getSupportFragmentManager().getBackStackEntryCount(); i++)
                getActivity().getSupportFragmentManager().popBackStack();
        }
    }
}
