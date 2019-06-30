package com.example.imagefinder.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.imagefinder.data.remote.RemoteDataSource;
import com.example.imagefinder.ui.home.HomeViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static ViewModelFactory INSTANCE;

    @NonNull
    private final RemoteDataSource remoteDataSource;

    public static ViewModelFactory getInstance(
            @NonNull RemoteDataSource remoteDataSource
    ) {
        if (INSTANCE == null) {
            INSTANCE = new ViewModelFactory(remoteDataSource);
        }
        return INSTANCE;
    }

    private ViewModelFactory(
            @NonNull RemoteDataSource remoteDataSource
    ) {
        this.remoteDataSource = remoteDataSource;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel();
        }
        return super.create(modelClass);
    }
}
