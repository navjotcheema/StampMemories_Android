package com.stampmemories.sectioned.faq;

import android.app.Activity;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stampmemories.core.BaseViewHolder;
import com.stampmemories.databinding.FaqItemBinding;
import com.stampmemories.databinding.ItemProgressBinding;

import java.util.ArrayList;

/**
 * Created by AppsterBiz on 20-Jan-18
 */

public class ProviderFaqAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static ObservableArrayList<FaqResponse.Faq> list = new ObservableArrayList<>();
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    public ProviderFaqAdapter(Activity context) {
        Activity context1 = context;
    }

    public ArrayList<FaqResponse.Faq> getFaqs() {
        return list;
    }

    public void setFaqs(ObservableArrayList<FaqResponse.Faq> list) {
        ProviderFaqAdapter.list = list;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM:
                FaqItemBinding blogViewBinding = FaqItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new FaqVH(blogViewBinding);
            case LOADING:
            default:
                ItemProgressBinding progressBinding = ItemProgressBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new LoadingVH(progressBinding);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == list.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    public FaqResponse.Faq getItem(int position) {
        return list.get(position);
    }


    public class FaqVH extends BaseViewHolder implements FaqItemViewModel.FaqToggleListener {
        private FaqItemBinding binding;
        FaqResponse.Faq faq = new FaqResponse.Faq();
        FaqItemViewModel faqItemViewModel;

        FaqVH(@NonNull FaqItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void onItemClick(boolean showAnswer) {
            if (showAnswer)
                faqItemViewModel.setIsExpanded(false);
            else
                faqItemViewModel.setIsExpanded(true);

        }

        @Override
        public void onBind(int position) {
            faq = list.get(position);
            faqItemViewModel = new FaqItemViewModel(faq, this);
            binding.setViewModel(faqItemViewModel);
            binding.executePendingBindings();
        }
    }

    private class LoadingVH extends BaseViewHolder {
        ItemProgressBinding progressBinding;

        LoadingVH(@NonNull ItemProgressBinding progressBinding) {
            super(progressBinding.getRoot());
            this.progressBinding = progressBinding;
        }

        @Override
        public void onBind(int position) {
            progressBinding.setShowProgress(isLoadingAdded);
        }
    }

}
