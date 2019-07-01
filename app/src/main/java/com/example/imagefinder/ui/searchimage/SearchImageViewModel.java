package com.example.imagefinder.ui.searchimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.example.imagefinder.commons.SingleLiveEvent;
import com.example.imagefinder.data.ThumbnailDataSourceFactory;
import com.example.imagefinder.data.local.LocalDataSource;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.data.remote.RemoteDataSource;
import com.example.imagefinder.ui.base.BaseViewModel;
import com.example.imagefinder.utils.TextUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.example.imagefinder.commons.Constants.PAGE_SIZE;

public class SearchImageViewModel extends BaseViewModel {

    @NonNull
    public final MutableLiveData<String> keyword = new MutableLiveData<>();
    @NonNull
    private final RemoteDataSource remoteDataSource;
    @NonNull
    private final LocalDataSource localDataSource;
    @NonNull
    private final SingleLiveEvent<Boolean> isLocalDataUpdate = new SingleLiveEvent<>();
    @NonNull
    private final PagedList.Config config;
    @Nullable
    public LiveData<PagedList<Thumbnail>> pagedListLiveData;
    @Nullable
    private ThumbnailDataSourceFactory thumbnailDataSourceFactory;

    public SearchImageViewModel(
            @NonNull RemoteDataSource remoteDataSource,
            @NonNull LocalDataSource localDataSource
    ) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE * 2)
                .build();
    }

    @NonNull
    public LiveData<Boolean> getIsLocalDataUpdate() {
        return isLocalDataUpdate;
    }

    @SuppressWarnings({"ConstantConditions", "WeakerAccess"})
    public boolean loadImages() {
        if (TextUtils.isNotEmpty(keyword.getValue())) {
            createPagedListLiveData(keyword.getValue());
            return true;
        }
        return false;
    }

    @SuppressWarnings("WeakerAccess")
    public void storeImages(@NonNull Thumbnail thumbnail) {
        addDisposable(localDataSource.insertThumbnail(thumbnail)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->
                                isLocalDataUpdate.setValue(true),
                        Throwable::printStackTrace
                )
        );
    }

    private void createPagedListLiveData(@NonNull String keyword) {
        if (thumbnailDataSourceFactory == null) {
            thumbnailDataSourceFactory = new ThumbnailDataSourceFactory(
                    keyword,
                    getCompositeDisposable(),
                    remoteDataSource
            );
        } else {
            thumbnailDataSourceFactory.setKeyword(keyword);
        }
        pagedListLiveData = new LivePagedListBuilder<>(thumbnailDataSourceFactory, config).build();
    }
}
