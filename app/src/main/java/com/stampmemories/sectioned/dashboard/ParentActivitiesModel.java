package com.stampmemories.sectioned.dashboard;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

public class ParentActivitiesModel extends BaseObservable {
    int id;
    String name;
    @NonNull
    ObservableBoolean isSelected = new ObservableBoolean();

    public ParentActivitiesModel(int id, String name, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected.set(isSelected);
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

    public boolean getIsSelected() {
        return isSelected.get();
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected.set(isSelected);
        notifyChange();
    }

//    public View.OnClickListener onItemSelected(Context context) {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!getIsSelected())
//                    setIsSelected(true);
//                else
//                    setIsSelected(false);
//            }
//        };
//    }
}
