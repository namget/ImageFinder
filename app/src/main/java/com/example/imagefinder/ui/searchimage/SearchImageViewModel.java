package com.example.imagefinder.ui.searchimage;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.local.LocalDataSource;
import com.example.imagefinder.data.remote.RemoteDataSource;
import com.example.imagefinder.ui.base.BaseViewModel;

public class SearchImageViewModel extends BaseViewModel {

    @NonNull
    private final RemoteDataSource remoteDataSource;

    @NonNull
    private final LocalDataSource localDataSource;

    public SearchImageViewModel(
            @NonNull RemoteDataSource remoteDataSource,
            @NonNull LocalDataSource localDataSource
    ) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }
}
