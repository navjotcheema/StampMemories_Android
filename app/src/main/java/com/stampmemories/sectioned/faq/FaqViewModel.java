package com.stampmemories.sectioned.faq;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.stampmemories.core.api.ApiManager;
import com.stampmemories.core.app.AppConstants;
import com.stampmemories.core.app.AppKey;
import com.stampmemories.helper.Alert;
import com.stampmemories.interfaces.OnApiResponse;

/**
 * Created by AppsterBiz on 21-Mar-18
 */

public class FaqViewModel extends BaseObservable implements OnApiResponse {
    private Activity context;
    private int totalCount = 0;
    private int currentPage = 1;
    private int totalPages = 0;
    private ObservableList<FaqResponse.Faq> faqList = new ObservableArrayList<>();
    @NonNull
    private ObservableBoolean isLoading = new ObservableBoolean();

    FaqViewModel(Activity context) {
        this.context = context;
        setIsLoading(true);
    }

    private void callFaqApi(int currentPage) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("page_no", currentPage);
        ApiManager.requestApi(context, FaqViewModel.this, AppConstants.API_MODE.FAQ, jsonObject);
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    @NonNull
    public View.OnClickListener onBackClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.finish();

            }
        };
    }

    public ObservableList<FaqResponse.Faq> getFaqList() {
        return faqList;
    }

    public void setFaqList(ObservableList<FaqResponse.Faq> faqList) {
        this.faqList = faqList;
        notifyChange();
    }

    public boolean getIsLoading() {
        return isLoading.get();
    }

    void setIsLoading(boolean isLoading) {
        this.isLoading.set(isLoading);
        if (currentPage <= totalPages)
            callFaqApi(currentPage);
        notifyChange();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void onApiSuccess(AppConstants.API_MODE mode, @NonNull JsonObject response) {
        totalCount = response.get("faq_count").getAsInt();
        totalPages = (int) (Math.ceil(totalCount / 10));
        faqList = (new Gson().fromJson(response.getAsJsonArray("faq"), new TypeToken<ObservableArrayList<FaqResponse>>() {
        }.getType()));
        setFaqList(faqList);
    }

    @Override
    public void onApiFailed(AppConstants.API_MODE mode, int statusCode, String response) {
        Alert.showAlert(context, AppKey.ERROR, response);

    }
}
