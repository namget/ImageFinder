package com.example.imagefinder.ui.storeimage;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.local.LocalDataSource;
import com.example.imagefinder.ui.base.BaseViewModel;

public class StoredImageViewModel extends BaseViewModel {

    @NonNull
    private final LocalDataSource localDataSource;

    public StoredImageViewModel(@NonNull LocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }
}
