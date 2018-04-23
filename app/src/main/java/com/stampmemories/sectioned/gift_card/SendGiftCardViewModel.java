package com.stampmemories.sectioned.gift_card;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RadioGroup;

import com.stampmemories.R;
import com.stampmemories.core.app.AppConstants;
import com.stripe.android.view.CardInputWidget;

public class SendGiftCardViewModel extends BaseObservable {
    @NonNull
    private ObservableField<Enum> payment_option = new ObservableField<>();

    public SendGiftCardViewModel() {
        payment_option.set(AppConstants.PAYMENT_OPTION.EXISTING_CARD);
    }

    public void onCheckChanged(RadioGroup radioGroup, int id) {
        if (id == R.id.rb_pay_with_card_sgc) {
            setPayment_option(AppConstants.PAYMENT_OPTION.EXISTING_CARD);

        } else if (id == R.id.rb_pay_with_another_card_sgc) {
            setPayment_option(AppConstants.PAYMENT_OPTION.ADD_NEW_CARD);
        }

    }

    @Nullable
    public Enum getPayment_option() {
        return payment_option.get();
    }

    public void setPayment_option(Enum option) {
        payment_option.set(option);
        notifyChange();
    }

    @BindingAdapter("setCardData")
    public static void bindSetCardData(@NonNull CardInputWidget cardInputWidget, Enum type) {
        if (type == AppConstants.PAYMENT_OPTION.EXISTING_CARD) {
            cardInputWidget.setCardNumber("45454444444412");
            cardInputWidget.setCvcCode("123");
            cardInputWidget.setExpiryDate(12, 2018);
            cardInputWidget.setEnabled(false);
        } else {
            cardInputWidget.clear();
            cardInputWidget.setEnabled(true);
        }

    }
}
