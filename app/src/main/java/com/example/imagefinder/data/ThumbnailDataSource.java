package com.example.imagefinder.data;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.data.remote.RemoteDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class ThumbnailDataSource extends PageKeyedDataSource<Integer, Thumbnail> {

    private static final int INITIAL_INDEX = 1;
    @NonNull
    private final String keyword;
    @NonNull
    private final CompositeDisposable compositeDisposable;
    @NonNull
    private final RemoteDataSource remoteDataSource;

    ThumbnailDataSource(
            @NonNull String keyword,
            @NonNull CompositeDisposable compositeDisposable,
            @NonNull RemoteDataSource remoteDataSource
    ) {
        this.keyword = keyword;
        this.compositeDisposable = compositeDisposable;
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public void loadInitial(
            @NonNull LoadInitialParams<Integer> params,
            @NonNull LoadInitialCallback<Integer, Thumbnail> callback
    ) {
        compositeDisposable.add(remoteDataSource
                .getThumbnailsByAllSource(INITIAL_INDEX, keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                                callback.onResult(result.getThumbnails(), INITIAL_INDEX, INITIAL_INDEX + 1),
                        Throwable::printStackTrace
                )
        );
    }

    @Override
    public void loadBefore(
            @NonNull LoadParams<Integer> params,
            @NonNull LoadCallback<Integer, Thumbnail> callback
    ) {
    }

    @Override
    public void loadAfter(
            @NonNull LoadParams<Integer> params,
            @NonNull LoadCallback<Integer, Thumbnail> callback
    ) {
        compositeDisposable.add(remoteDataSource
                .getThumbnailsByAllSource(params.key + 1, keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->
                                callback.onResult(result.getThumbnails(), params.key + 1),
                        Throwable::printStackTrace
                )
        );
    }
}
