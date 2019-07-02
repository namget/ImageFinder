package com.example.imagefinder.data.paging;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import com.example.imagefinder.commons.SingleLiveEvent;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.data.remote.RemoteDataSource;
import io.reactivex.disposables.CompositeDisposable;

public class ThumbnailDataSourceFactory extends DataSource.Factory<Integer, Thumbnail> {

    @NonNull
    private final CompositeDisposable compositeDisposable;
    @NonNull
    private final RemoteDataSource remoteDataSource;
    @NonNull
    private final SingleLiveEvent<Boolean> isLoading;
    @NonNull
    private final SingleLiveEvent<Boolean> isError;
    @NonNull
    private String keyword;

    public ThumbnailDataSourceFactory(
            @NonNull String keyword,
            @NonNull CompositeDisposable compositeDisposable,
            @NonNull RemoteDataSource remoteDataSource,
            @NonNull SingleLiveEvent<Boolean> isLoading,
            @NonNull SingleLiveEvent<Boolean> isError
    ) {
        this.keyword = keyword;
        this.compositeDisposable = compositeDisposable;
        this.remoteDataSource = remoteDataSource;
        this.isLoading = isLoading;
        this.isError = isError;
    }

    public void setKeyword(@NonNull String keyword) {
        this.keyword = keyword;
    }

    @Override
    public DataSource<Integer, Thumbnail> create() {
        return new ThumbnailDataSource(
                keyword,
                compositeDisposable,
                remoteDataSource,
                isLoading,
                isError);
    }
}
