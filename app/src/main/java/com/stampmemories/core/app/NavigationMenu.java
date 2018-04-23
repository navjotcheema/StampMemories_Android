package com.stampmemories.core.app;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by AppsterBiz on 23-Mar-18
 */

public enum NavigationMenu implements Serializable {
    HOME, COMMUNICATION, NOTIFICATIONS, INVITE_A_FRIEND, FAQ, CHANGE_PASSWORD,
    SWITCH_TO_CUSTOMER, SWITCH_TO_PROVIDER, BECOME_A_PROVIDER, LOGOUT, LOGIN,
    MY_ACTIVITIES, MY_EXPERIENCES, MY_ORDERS;

    public String toString() {
        switch (this) {
            case HOME:
                return "Home";
            case COMMUNICATION:
                return "Communication";
            case NOTIFICATIONS:
                return "Notifications";
            case INVITE_A_FRIEND:
                return "Invite a Friend";
            case FAQ:
                return "FAQ";
            case CHANGE_PASSWORD:
                return "Change Password";
            case SWITCH_TO_CUSTOMER:
                return "Switch to Customer";
            case SWITCH_TO_PROVIDER:
                return "Switch to Provider";
            case LOGOUT:
                return "Logout";
            case LOGIN:
                return "Login";
            case MY_ACTIVITIES:
                return "My Activities";
            case MY_EXPERIENCES:
                return "My Experiences";
            case MY_ORDERS:
                return "My Orders";
        }
        return null;
    }

    public static Enum fromString(@NonNull String value) {
        switch (value) {
            case "My Activities":
                return MY_ACTIVITIES;
            case "My Experiences":
                return MY_EXPERIENCES;
            case "My Orders":
                return MY_ORDERS;
        }
        return null;
    }
}
