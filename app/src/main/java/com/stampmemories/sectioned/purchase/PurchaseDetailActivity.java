package com.stampmemories.sectioned.purchase;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;

import com.stampmemories.R;
import com.stampmemories.sectioned.SectionDetail;
import com.stampmemories.sectioned.SectionHeader;
import com.stampmemories.sectioned.SectionSlots;
import com.stampmemories.databinding.ActivityPurchaseDetailBinding;

import net.idik.lib.slimadapter.SlimAdapter;
import net.idik.lib.slimadapter.SlimInjector;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDetailActivity extends AppCompatActivity {
    SlimAdapter slimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPurchaseDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase_detail);
        binding.txtTnc.setText(Html.fromHtml("Terms of service: By confirming this booking, you agree to the Stamp Memories LLC " +
                "<a href='com.stampmemories.sectioned.purchase.TnCActivity://'>Terms and Conditions</a>" + ", " + "<a href='com.stampmemories.sectioned.purchase.PrivacyPolicyActivity://'>Privacy Policy</a>"
                + " and Provider conditions for the specific experience/activity that you are purchasing."));
        binding.txtTnc.setClickable(true);
        binding.txtTnc.setMovementMethod(LinkMovementMethod.getInstance());

//        -------------------------
        List<Object> currentData = new ArrayList<>();
        currentData.add(new SectionSlots(10, 2));
        currentData.add(new SectionHeader("What is the Activity about:"));
        currentData.add(new SectionDetail(getString(R.string.dummy_less_text)));
        currentData.add(new SectionHeader("Included items :"));
        currentData.add(new SectionDetail("Food - Snacks"));
        currentData.add(new SectionHeader("To Bring :"));
        currentData.add(new SectionDetail("Water Bottle, Skipping Rope, Yoga Mat"));
        currentData.add(new SectionHeader("Age Group :"));
        currentData.add(new SectionDetail("Young Adults (12-18)"));
        currentData.add(new SectionHeader("Level :"));
        currentData.add(new SectionDetail("Intermediate"));
        currentData.add(new SectionHeader("Cancellation :"));
        currentData.add(new SectionDetail("Once booked activity cannot be cancelled."));
        currentData.add(new SectionHeader("Rescheduling :"));
        currentData.add(new SectionDetail("Once booked activity cannot be rescheduled."));
//        currentData.add(new SectionHeader("Address :"));
//        currentData.add(new SectionDetail("Ontario Street East, Montreal, QC, Canada\n" +
//                "Rue Ontario E\n" +
//                "Montréal , QC, Canada"));
        currentData.add(new SectionHeader("Address :"));
        currentData.add(new SectionMap());


        binding.recyclerPurchaseDetail.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerPurchaseDetail.setNestedScrollingEnabled(true);
        //create full Layout
        slimAdapter = SlimAdapter.create();

        slimAdapter.register(R.layout.txt_spinner, new SlimInjector<SectionSlots>() {
            @Override
            public void onInject(SectionSlots data, IViewInjector injector) {
//                injector.text(R.id.textView_caps, data.getHeader());
            }
        }).register(R.layout.caps_text, new SlimInjector<SectionHeader>() {
            @Override
            public void onInject(SectionHeader data, IViewInjector injector) {
                injector.text(R.id.textView_caps, data.getHeader());
            }
        }).register(R.layout.txt_black, new SlimInjector<SectionDetail>() {
            @Override
            public void onInject(SectionDetail data, IViewInjector injector) {
                injector.text(R.id.textView_black, data.getDetail());
            }
        }).register(R.layout.caps_text, new SlimInjector<SectionHeader>() {
            @Override
            public void onInject(SectionHeader data, IViewInjector injector) {
                injector.text(R.id.textView_caps, data.getHeader());
            }
        }).register(R.layout.txt_black, new SlimInjector<SectionDetail>() {
            @Override
            public void onInject(SectionDetail data, IViewInjector injector) {
                injector.text(R.id.textView_black, data.getDetail());
            }
        }).register(R.layout.caps_text, new SlimInjector<SectionHeader>() {
            @Override
            public void onInject(SectionHeader data, IViewInjector injector) {
                injector.text(R.id.textView_caps, data.getHeader());
            }
        }).register(R.layout.txt_black, new SlimInjector<SectionDetail>() {
            @Override
            public void onInject(SectionDetail data, IViewInjector injector) {
                injector.text(R.id.textView_black, data.getDetail());
            }
        }).register(R.layout.item_map, new SlimInjector<SectionMap>() {
            @Override
            public void onInject(SectionMap data, IViewInjector injector) {

            }
        }).attachTo(binding.recyclerPurchaseDetail)
                .updateData(currentData);


        binding.textView41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SpotDetailsActivity.class));
            }
        });
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    class SectionMap {
        public SectionMap() {
        }
    }
}
