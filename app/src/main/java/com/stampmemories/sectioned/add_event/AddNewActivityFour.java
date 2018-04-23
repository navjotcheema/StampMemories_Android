package com.stampmemories.sectioned.add_event;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.stampmemories.R;

import static com.stampmemories.sectioned.add_event.AddNewActivity.stateProgressBar;

/**
 * Created by AppsterBiz on 20-Dec-17
 */

public class AddNewActivityFour extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {


    RadioButton session;
    boolean hasSessionSequence = false;

    int count = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_activity_step_four, container, false);
        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.FOUR);
        Button btnNext = view.findViewById(R.id.btn_next_four);
        btnNext.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupWidgets();
    }

    private void setupWidgets() {
//        btnAddMore.setOnClickListener(this);
//        //setup spinner adapters
//        dateSpecific.setOnItemSelectedListener(this);
//        sessionSequence.setOnItemSelectedListener(this);
//        sessionOptions.setOnCheckedChangeListener(this);
////        dateSpecific.setSelection(0);
////        sessionSequence.setSelection(1);
//        eventAvailabilityRecycler.setNestedScrollingEnabled(false);

    }

    @Override
    public void onClick(@NonNull View view) {
        int id = view.getId();
        if (id == R.id.btn_next_four) {
            if (getActivity() != null)
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.form_fragment_container, new AddNewActivityFive())
                        .addToBackStack(null).commit();
        } else if (id == R.id.addMore_date) {

            new AlertDialog.Builder(getActivity()).setView(R.layout.add_activity_date_detail)
                    .setPositiveButton("ADD", null)
                    .setNegativeButton("DISMISS", null)
                    .create().show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


    }

    private void setupDaysList() {
//        eventAvailabilityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        EventsAvailabilityAdapter adapter = new EventsAvailabilityAdapter(getActivity());
//        eventAvailabilityRecycler.setAdapter(adapter);
//        eventAvailabilityRecycler.setHasFixedSize(true);
//        eventAvailabilityRecycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

    }

    private void setupDateSpecificList() {
//        eventAvailabilityRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new DateSpecificDataAdapter(getActivity(), hasSessionSequence);
//        eventAvailabilityRecycler.setNestedScrollingEnabled(false);
//        eventAvailabilityRecycler.setAdapter(adapter);
//        eventAvailabilityRecycler.setHasFixedSize(true);
//        eventAvailabilityRecycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(@NonNull RadioGroup radioGroup, int isChecked) {
        int id = radioGroup.getCheckedRadioButtonId();
        if (id == R.id.radio_one) {
//            numSessionsOffered.setText("1");
//            numSessionsOffered.setEnabled(false);
        }
        if (id == R.id.radio_multiple) {
//            numSessionsOffered.setText("");
//            numSessionsOffered.setEnabled(true);
        }

    }

    private void addLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.addView(new EditText(getActivity()));
    }
}
