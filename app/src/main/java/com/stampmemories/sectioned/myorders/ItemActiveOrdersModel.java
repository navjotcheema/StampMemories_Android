package com.stampmemories.sectioned.myorders;

import android.databinding.BaseObservable;

/**
 * Created by AppsterBiz on 23-Mar-18.
 */

public class ItemActiveOrdersModel extends BaseObservable {
    String date;
    String title, type, status;

    public ItemActiveOrdersModel(String date, String title, String type, String status) {
        this.date = date;
        this.title = title;
        this.type = type;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
