package com.example.imagefinder.data.paging;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import com.example.imagefinder.commons.SingleLiveEvent;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.data.remote.RemoteDataSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.example.imagefinder.commons.Constants.INITIAL_INDEX;


public class ThumbnailDataSource extends PageKeyedDataSource<Integer, Thumbnail> {

    @NonNull
    private final String keyword;
    @NonNull
    private final CompositeDisposable compositeDisposable;
    @NonNull
    private final RemoteDataSource remoteDataSource;
    @NonNull
    private final SingleLiveEvent<Boolean> isLoading;
    @NonNull
    private final SingleLiveEvent<Boolean> isError;

    ThumbnailDataSource(
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


    @Override
    public void loadInitial(
            @NonNull LoadInitialParams<Integer> params,
            @NonNull LoadInitialCallback<Integer, Thumbnail> callback
    ) {
        isLoading.postValue(true);
        addDisposable(remoteDataSource.getThumbnailsByAllSource(INITIAL_INDEX, keyword)
                .subscribe(result -> {
                            isLoading.postValue(false);
                            callback.onResult(result, INITIAL_INDEX, INITIAL_INDEX + 1);
                        },
                        error -> {
                            isLoading.postValue(false);
                            isError.postValue(true);
                        }
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
        int nextKey = params.key + 1;
        addDisposable(remoteDataSource.getThumbnailsByAllSource(nextKey, keyword)
                .subscribe(result -> callback.onResult(result, nextKey),
                        error -> isError.postValue(true)
                )
        );
    }

    private void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
