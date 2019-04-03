package com.android.team.moshey.ui.viewmodels;

import com.android.team.moshey.models.MosheyRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created By blackcoder
 * On 03/04/19
 **/
public class BookingViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final MosheyRepository mRepository;

    public BookingViewModelFactory(MosheyRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new BookingViewModel(mRepository);
    }
}
