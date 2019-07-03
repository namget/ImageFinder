package com.example.imagefinder.ui.searchimage;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.example.imagefinder.commons.SingleLiveEvent;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.data.paging.ThumbnailDataSourceFactory;
import com.example.imagefinder.data.remote.RemoteDataSource;
import com.example.imagefinder.ui.base.BaseViewModel;

import static com.example.imagefinder.commons.Constants.PAGE_SIZE;

public class SearchImageViewModel extends BaseViewModel {

    @NonNull
    public final MutableLiveData<String> keyword = new MutableLiveData<>();
    @NonNull
    final LiveData<PagedList<Thumbnail>> pagedListLiveData;
    @NonNull
    private final ThumbnailDataSourceFactory thumbnailDataSourceFactory;
    @NonNull
    private final SingleLiveEvent<Boolean> isLoading = new SingleLiveEvent<>();
    @NonNull
    private final SingleLiveEvent<Boolean> isError = new SingleLiveEvent<>();

    public SearchImageViewModel(
            @NonNull RemoteDataSource remoteDataSource
    ) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE * 2)
                .build();

        thumbnailDataSourceFactory = new ThumbnailDataSourceFactory(
                "",
                getCompositeDisposable(),
                remoteDataSource,
                isLoading,
                isError
        );

        pagedListLiveData = new LivePagedListBuilder<>(
                thumbnailDataSourceFactory,
                config
        ).build();
    }

    @NonNull
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    @NonNull
    public LiveData<Boolean> getIsError() {
        return isError;
    }

    public void loadImages() {
        if (pagedListLiveData.getValue() != null && keyword.getValue() != null) {
            thumbnailDataSourceFactory.setKeyword(keyword.getValue());
            pagedListLiveData.getValue().getDataSource().invalidate();
        }
    }
}
