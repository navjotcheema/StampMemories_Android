package com.stampmemories.sectioned.faq;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

/**
 * Created by AppsterBiz on 17-Apr-18
 */

public class FaqItemViewModel extends BaseObservable {
    private String question, answer;
    private FaqResponse.Faq faq;
    private FaqToggleListener listener;
    private ObservableBoolean isExpanded;

    public FaqItemViewModel(@NonNull FaqResponse.Faq faq, FaqToggleListener listener) {
        this.faq = faq;
        this.listener = listener;
        question = faq.getQuestion();
        answer = faq.getAnswer();
        isExpanded.set(faq.isExpanded());
    }

    public void onItemClick() {
        listener.onItemClick(getIsExpanded());
    }

    public boolean getIsExpanded() {
        return isExpanded.get();
    }

    public void setIsExpanded(boolean isExpanded) {
        this.isExpanded.set(isExpanded);
        notifyChange();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public interface FaqToggleListener {

        void onItemClick(boolean showAnswer);
    }
}
