package com.stampmemories.sectioned.dashboard;

import android.databinding.ObservableArrayList;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 01-Feb-18.
 */

public class DrawerModel {
    private Drawer drawer;

    public Drawer getDrawer() {
        return drawer;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

    public class Drawer {
        private ObservableArrayList<Sub_menu> sub_menu;

        public ObservableArrayList<Sub_menu> getSub_menu() {
            return sub_menu;
        }

        public void setSub_menu(ObservableArrayList<Sub_menu> sub_menu) {
            this.sub_menu = sub_menu;
        }

        public class Sub_menu {
            private String title;

            private ArrayList<String> sub_items;

            private String image;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public ArrayList<String> getSub_items() {
                return sub_items;
            }

            public void setSub_items(ArrayList<String> sub_items) {
                this.sub_items = sub_items;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }


}
