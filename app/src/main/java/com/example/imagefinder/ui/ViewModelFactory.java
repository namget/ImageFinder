package com.example.imagefinder.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.imagefinder.data.local.FakeLocalDataSourceImpl;
import com.example.imagefinder.data.local.LocalDataSource;
import com.example.imagefinder.data.remote.RemoteDataSource;
import com.example.imagefinder.data.remote.RemoteDataSourceImpl;
import com.example.imagefinder.ui.detail.DetailViewModel;
import com.example.imagefinder.ui.home.HomeViewModel;
import com.example.imagefinder.ui.searchimage.SearchImageViewModel;
import com.example.imagefinder.ui.storeimage.StoredImageViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static ViewModelFactory INSTANCE;

    @NonNull
    private final RemoteDataSource remoteDataSource;

    @NonNull
    private final LocalDataSource localDataSource;

    private ViewModelFactory(
            @NonNull RemoteDataSource remoteDataSource,
            @NonNull LocalDataSource localDataSource
    ) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static ViewModelFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ViewModelFactory(RemoteDataSourceImpl.getInstance(), FakeLocalDataSourceImpl.getInstance());
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel();
        } else if (modelClass.isAssignableFrom(SearchImageViewModel.class)) {
            return (T) new SearchImageViewModel(remoteDataSource);
        } else if (modelClass.isAssignableFrom(StoredImageViewModel.class)) {
            return (T) new StoredImageViewModel(localDataSource);
        } else if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            return (T) new DetailViewModel(localDataSource);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
        }
    }
}
