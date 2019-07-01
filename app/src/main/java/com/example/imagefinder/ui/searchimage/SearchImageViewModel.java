package com.example.imagefinder.ui.searchimage;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.imagefinder.commons.SingleLiveEvent;
import com.example.imagefinder.data.local.LocalDataSource;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.data.remote.RemoteDataSource;
import com.example.imagefinder.ui.base.BaseViewModel;
import com.example.imagefinder.utils.TextUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;

import java.util.List;

public class SearchImageViewModel extends BaseViewModel {

    @NonNull
    public final MutableLiveData<String> keyword = new MutableLiveData<>();
    @NonNull
    private final RemoteDataSource remoteDataSource;
    @NonNull
    private final LocalDataSource localDataSource;
    @NonNull
    private final MutableLiveData<List<Thumbnail>> searchedImages = new MutableLiveData<>();
    @NonNull
    private final SingleLiveEvent<Boolean> isLocalDataUpdate = new SingleLiveEvent<>();

    public SearchImageViewModel(
            @NonNull RemoteDataSource remoteDataSource,
            @NonNull LocalDataSource localDataSource
    ) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    @NonNull
    public LiveData<List<Thumbnail>> getSearchedImages() {
        return searchedImages;
    }

    @NonNull
    public LiveData<Boolean> getIsLocalDataUpdate() {
        return isLocalDataUpdate;
    }

    @SuppressWarnings("ConstantConditions")
    public void loadImages() {
        if (TextUtils.isNotEmpty(keyword.getValue())) {
            addDispoable(remoteDataSource.getThumbnailsByAllSource(1, keyword.getValue())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result ->
                                    searchedImages.setValue(result.getThumbnails()),
                            Throwable::printStackTrace
                    )
            );
        }
    }


    @SuppressWarnings("WeakerAccess")
    public void storeImages(int position) {
        List<Thumbnail> thumbnails = searchedImages.getValue();

        if (thumbnails != null && position < thumbnails.size()) {
            addDispoable(localDataSource.insertThumbnail(thumbnails.get(position))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() ->
                                    isLocalDataUpdate.setValue(true),
                            Throwable::printStackTrace
                    )
            );
        }
    }
}
