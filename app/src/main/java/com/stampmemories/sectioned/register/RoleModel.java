package com.stampmemories.sectioned.register;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 12-Apr-18
 */

public class RoleModel {
    private Individual individual;

    private ArrayList<Business> business;

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public ArrayList<Business> getBusiness() {
        return business;
    }

    public void setBusiness(ArrayList<Business> business) {
        this.business = business;
    }

    public class Business {
        private String id;

        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public class Individual {
        private String id;

        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
