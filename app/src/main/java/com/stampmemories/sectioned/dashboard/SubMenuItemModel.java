package com.stampmemories.sectioned.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.stampmemories.interfaces.DrawerAction;
import com.stampmemories.sectioned.about.AboutActivity;
import com.stampmemories.sectioned.background_check.BackgroundCheckActivity;
import com.stampmemories.sectioned.credits.CreditsActivity;
import com.stampmemories.sectioned.cust_orders.OrdersActivity;
import com.stampmemories.sectioned.documents.DocumentsActivity;
import com.stampmemories.sectioned.earned_funds.EarnedFundsActivity;
import com.stampmemories.sectioned.general_information.GeneralInformationActivity;
import com.stampmemories.sectioned.gift_card.ApplyGiftCardActivity;
import com.stampmemories.sectioned.gift_card.SendGiftCardActivity;
import com.stampmemories.sectioned.myactivities.MyActivitiesActivity;
import com.stampmemories.sectioned.myexperiences.MyExperiencesActivity;
import com.stampmemories.sectioned.myorders.MyOrdersActivity;
import com.stampmemories.sectioned.notifications.ContactSupportActivity;
import com.stampmemories.sectioned.notifications.NotificationsActivity;
import com.stampmemories.sectioned.payment_prefs.PaymentPrefsActivity;

/**
 * Created by AppsterBiz on 22-Mar-18
 */

public class SubMenuItemModel extends BaseObservable {
    private Activity context;
    private String subMenuTitle = "";

    SubMenuItemModel(Activity context, String subMenuTitle) {
        this.context = context;
        this.subMenuTitle = subMenuTitle;
    }

    public String getSubMenuTitle() {
        return subMenuTitle;
    }

    void onSubmenuItemClick(@NonNull String title) {
        ((DrawerAction) context).closeDrawer();
        ((DrawerAction) context).hideKeyboard();
        switch (title) {
            case "MY ACTIVITIES":
                context.startActivity(new Intent(context, MyActivitiesActivity.class));
                break;
            case "MY EXPERIENCES":
                context.startActivity(new Intent(context, MyExperiencesActivity.class));

                break;
            case "MY ORDERS":
                context.startActivity(new Intent(context, MyOrdersActivity.class));

                break;
            case "CREDITS":
                context.startActivity(new Intent(context, CreditsActivity.class));

                break;
            case "GENERAL INFORMATION":
                context.startActivity(new Intent(context, GeneralInformationActivity.class));

                break;
            case "ABOUT":
                context.startActivity(new Intent(context, AboutActivity.class));

                break;
            case "DOCUMENTS":
                context.startActivity(new Intent(context, DocumentsActivity.class));

                break;
            case "PAYMENT PREFERENCES":
                context.startActivity(new Intent(context, PaymentPrefsActivity.class));

                break;
            case "PAYMENT INFORMATION":
                context.startActivity(new Intent(context, PaymentPrefsActivity.class));

                break;
            case "BACKGROUND CHECK":
                context.startActivity(new Intent(context, BackgroundCheckActivity.class));

                break;

            case "EARNED FUNDS":
                context.startActivity(new Intent(context, EarnedFundsActivity.class));

                break;
            case "APPLY GIFT CARD":
                context.startActivity(new Intent(context, ApplyGiftCardActivity.class));

                break;
            case "SEND GIFT CARD":
                context.startActivity(new Intent(context, SendGiftCardActivity.class));
                break;
            case "NOTIFICATION":
                context.startActivity(new Intent(context, NotificationsActivity.class));
                break;
            case "SUPPORT":
                context.startActivity(new Intent(context, ContactSupportActivity.class));
                break;
            case "ORDERS":
                context.startActivity(new Intent(context, OrdersActivity.class));
                break;
        }

    }
}
