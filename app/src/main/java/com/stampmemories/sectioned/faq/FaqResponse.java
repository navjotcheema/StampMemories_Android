package com.stampmemories.sectioned.faq;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 20-Jan-18
 */

public class FaqResponse {
    private String message;

    private String status;

    private double faq_count;

    private ArrayList<Faq> faq;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Faq> getFaq() {
        return faq;
    }

    public void setFaq(ArrayList<Faq> faq) {
        this.faq = faq;
    }

    public double getFaq_count() {
        return faq_count;
    }

    public void setFaq_count(double faq_count) {
        this.faq_count = faq_count;
    }

    public static class Faq {

        private boolean isExpanded;
        private String id;

        private String is_deleted;

        private String updated_at;

        private String answer;

        private String created_at;

        private String question;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(String is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public boolean isExpanded() {
            return isExpanded;
        }

        public void setExpanded(boolean expanded) {
            isExpanded = expanded;
        }

    }
}
