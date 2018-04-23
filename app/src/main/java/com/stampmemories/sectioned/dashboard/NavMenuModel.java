package com.stampmemories.sectioned.dashboard;

import java.util.List;

/**
 * Created by AppsterBiz on 20-Dec-17.
 */

public class NavMenuModel {
    String title;
    int drawable_id;
    List<String> submenu;
    boolean isExpanded = false;
    int arrow_drawable_id;

    public NavMenuModel(String title, int drawable_id, List<String> submenu, int arrow_drawable_id) {
        this.title = title;
        this.drawable_id = drawable_id;
        this.submenu = submenu;
        this.arrow_drawable_id = arrow_drawable_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDrawable_id() {
        return drawable_id;
    }

    public void setDrawable_id(int drawable_id) {
        this.drawable_id = drawable_id;
    }

    public List<String> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<String> submenu) {
        this.submenu = submenu;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getArrow_drawable_id() {
        return arrow_drawable_id;
    }

    public void setArrow_drawable_id(int arrow_drawable_id) {
        this.arrow_drawable_id = arrow_drawable_id;
    }
}
