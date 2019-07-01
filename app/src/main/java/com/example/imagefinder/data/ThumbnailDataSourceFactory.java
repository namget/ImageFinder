package com.example.imagefinder.data;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.data.remote.RemoteDataSource;
import io.reactivex.disposables.CompositeDisposable;

public class ThumbnailDataSourceFactory extends DataSource.Factory<Integer, Thumbnail> {

    @NonNull
    private final CompositeDisposable compositeDisposable;
    @NonNull
    private final RemoteDataSource remoteDataSource;
    @NonNull
    private String keyword;

    public ThumbnailDataSourceFactory(
            @NonNull String keyword,
            @NonNull CompositeDisposable compositeDisposable,
            @NonNull RemoteDataSource remoteDataSource
    ) {
        this.remoteDataSource = remoteDataSource;
        this.compositeDisposable = compositeDisposable;
        this.keyword = keyword;
    }

    public void setKeyword(@NonNull String keyword) {
        this.keyword = keyword;
    }

    @Override
    public DataSource<Integer, Thumbnail> create() {
        return new ThumbnailDataSource(keyword, compositeDisposable, remoteDataSource);
    }
}
