package com.stampmemories.core.app;

import android.support.annotation.NonNull;

/**
 * Created by AppsterBiz on 22-Mar-18
 */

public class AppConstants {

    @NonNull
    public static String APP_NAME = "StampMemories";
    public static final String DEVICE_TYPE = "android";
    public static final String DIRECT_LOGIN_TYPE = "custom";
    public static final String FB_LOGIN_TYPE = "facebook";
    public static final String GOOGLE_LOGIN_TYPE = "google";
    public final static String ERROR = "Error";
    public final static int PLACE_AUTOCOMPLETE_REQUEST_CODE = 123;
    public static final String RegxBlank = "^(?!\\s*$).+";
    public static final String RegxPassword = "^(?=.*[a-zA-Z])(?=.*[0-9])";
    public static final String BlankStringError = "Required Field";
    public static final String PhoneNoError = "Enter correct phone number";
    public static final String EmailError = "Enter correct email";
    public static final String ConfirmPasswordError = "Passwords don't match";
    public final static String[] gpsPermissions = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    public final static String STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    public enum USER_ROLES {
        CUSTOMER, PROVIDER, GUEST
    }

    public enum TRANSITIONS {
        ENTRY, EXIT, SLIDE_IN, SLIDE_OUT
    }

    public enum DROPDOWN_TYPE {
        INSURED, BUSINESS_TYPE, COMPANY_EXPERIENCE, ROLES, AGE_GROUP, DISTANCE_FROM_LOCATION, COST_RANGE,
        REGISTER_AS, ARE_YOU_A, LOCATION
    }

    public enum API_MODE {
        LOGIN, REGISTER, UPDATE_GENERAL_INFORMATION, PROFILE_AVAILABILITY,
        REQUEST_OTP, VERIFY_OTP, GET_ABOUT, UPDATE_ABOUT, GET_DOCUMENTS, UPDATE_DOCUMENTS,
        UPDATE_PAYMENT_PREFERENCES, GET_MY_ACTIVITIES, GET_MY_EXPERIENCES, GET_MY_ORDERS,
        ADD_ACTIVITY_EXPERIENCES, CHANGE_PASSWORD, FORGOT_PASSWORD, FAQ, GET_NOTIFICATIONS,
        INVITE_FRIEND, SOCIAL_LOGIN, CUSTOMER_ROLE, PROVIDER_ROLE
    }

    public enum PAYMENT_OPTION {
        EXISTING_CARD, ADD_NEW_CARD
    }

    public enum USER_TYPE {
        INDIVIDUAL, BUSINESS;

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
