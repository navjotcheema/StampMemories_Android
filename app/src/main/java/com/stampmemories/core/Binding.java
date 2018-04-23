package com.stampmemories.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stampmemories.R;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.helper.InstantAutoComplete;
import com.stampmemories.sectioned.faq.FaqResponse;
import com.stampmemories.sectioned.faq.ProviderFaqAdapter;

import static com.stampmemories.core.app.CommonFunction.getCountryFlag;

/**
 * Created by AppsterBiz on 12-Apr-18
 */

public class Binding {

    @BindingAdapter("imageUrl")
    public static void loadImage(@NonNull ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url))
            Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("bind:countryFlag")
    public static void loadFlags(@NonNull ImageView imageView, @NonNull String locale) {
        if (!TextUtils.isEmpty(locale))
            imageView.setImageDrawable(getCountryFlag(imageView.getContext(), locale));

    }

    @BindingAdapter("smlogo")
    public static void loadLogo(@NonNull ImageView imageView, @NonNull Context context) {
        Glide.with(context).load(context.getResources().getDrawable(R.drawable.stamp_memories_logo)).into(imageView);
    }

    @BindingAdapter("bind:transformation")
    public static void addPasswordTransformation(@NonNull EditText editText, String dummy) {
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter({"bind:select", "bind:type"})
    public static void bindInsuredOption(@NonNull final InstantAutoComplete chooser, @NonNull Context context, @NonNull AppConstants.DROPDOWN_TYPE type) {

        switch (type) {
            case INSURED:
                chooser.setAdapter(ArrayAdapter.createFromResource(context, R.array.date_spec, R.layout.item_dial_code));
                chooser.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        chooser.showDropDown();
                        return false;
                    }

                });
                break;
            case BUSINESS_TYPE:
                chooser.setAdapter(ArrayAdapter.createFromResource(context, R.array.business_type, R.layout.item_dial_code));
                chooser.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        chooser.showDropDown();
                        return false;
                    }

                });
                break;
            case COMPANY_EXPERIENCE:
                chooser.setAdapter(ArrayAdapter.createFromResource(context, R.array.company_year, R.layout.item_dial_code));
                chooser.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        chooser.showDropDown();
                        return false;
                    }

                });
                break;
            case AGE_GROUP:
                chooser.setAdapter(ArrayAdapter.createFromResource(context, R.array.age_group, R.layout.item_dial_code));
                chooser.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        chooser.showDropDown();
                        return false;
                    }

                });
                break;
            case DISTANCE_FROM_LOCATION:
                chooser.setAdapter(ArrayAdapter.createFromResource(context, R.array.location_list, R.layout.item_dial_code));
                chooser.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        chooser.showDropDown();
                        return false;
                    }

                });
                break;
            case COST_RANGE:
                chooser.setAdapter(ArrayAdapter.createFromResource(context, R.array.cost_list, R.layout.item_dial_code));
                chooser.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        chooser.showDropDown();
                        return false;
                    }

                });
                break;
        }

    }

    @BindingAdapter("bind:faq_recycler")
    public static void bindFaqRecycler(@NonNull RecyclerView recyclerView, ObservableArrayList<FaqResponse.Faq> list) {
        ProviderFaqAdapter adapter = new ProviderFaqAdapter((AppCompatActivity) recyclerView.getContext());
        adapter.setFaqs(list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("test")
    public static void test(TabLayout tabLayout, Context context) {

    }
}
