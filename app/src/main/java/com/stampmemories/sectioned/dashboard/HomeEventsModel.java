package com.stampmemories.sectioned.dashboard;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 19-Apr-18
 */

public class HomeEventsModel {
    private ArrayList<Experiences> experiences;

    private String experiences_count;

    private String activities_count;

    private String status;

    private ArrayList<Activities> activities;

    public ArrayList<Experiences> getExperiences() {
        return experiences;
    }

    public void setExperiences(ArrayList<Experiences> experiences) {
        this.experiences = experiences;
    }

    public String getExperiences_count() {
        return experiences_count;
    }

    public void setExperiences_count(String experiences_count) {
        this.experiences_count = experiences_count;
    }

    public String getActivities_count() {
        return activities_count;
    }

    public void setActivities_count(String activities_count) {
        this.activities_count = activities_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Activities> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activities> activities) {
        this.activities = activities;
    }

    public static class Activities {
        private ArrayList<EventName> EventName;

        private String count;

        private String parentCaegoryId;

        private String parentCaegoryName;

        public ArrayList<EventName> getEventName() {
            return EventName;
        }

        public void setEventName(ArrayList<EventName> EventName) {
            this.EventName = EventName;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getParentCaegoryId() {
            return parentCaegoryId;
        }

        public void setParentCaegoryId(String parentCaegoryId) {
            this.parentCaegoryId = parentCaegoryId;
        }

        public String getParentCaegoryName() {
            return parentCaegoryName;
        }

        public void setParentCaegoryName(String parentCaegoryName) {
            this.parentCaegoryName = parentCaegoryName;
        }
    }

    public static class Experiences {
        private ArrayList<EventName> EventName;

        private String count;

        private String parentCaegoryId;

        private String parentCaegoryName;

        public ArrayList<EventName> getEventName() {
            return EventName;
        }

        public void setEventName(ArrayList<EventName> EventName) {
            this.EventName = EventName;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getParentCaegoryId() {
            return parentCaegoryId;
        }

        public void setParentCaegoryId(String parentCaegoryId) {
            this.parentCaegoryId = parentCaegoryId;
        }

        public String getParentCaegoryName() {
            return parentCaegoryName;
        }

        public void setParentCaegoryName(String parentCaegoryName) {
            this.parentCaegoryName = parentCaegoryName;
        }

    }

    public static class EventName {
        private String main_category_name;

        private String title;

        private String price;

        private String image;

        private String sub_category_name;

        private String event_id;

        private String parent_category_name;

        private String longitude;

        private String rating;

        private String latitude;

        public String getMain_category_name() {
            return main_category_name;
        }

        public void setMain_category_name(String main_category_name) {
            this.main_category_name = main_category_name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSub_category_name() {
            return sub_category_name;
        }

        public void setSub_category_name(String sub_category_name) {
            this.sub_category_name = sub_category_name;
        }

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }

        public String getParent_category_name() {
            return parent_category_name;
        }

        public void setParent_category_name(String parent_category_name) {
            this.parent_category_name = parent_category_name;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
