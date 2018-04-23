package com.stampmemories.sectioned.dashboard;

import android.databinding.ObservableBoolean;

public class ParentExperiences {
    int id;
    String name;
    ObservableBoolean isSelected;

    public ParentExperiences(int id, String name, ObservableBoolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableBoolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(ObservableBoolean isSelected) {
        this.isSelected = isSelected;
    }
}
