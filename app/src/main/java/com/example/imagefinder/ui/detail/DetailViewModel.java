package com.example.imagefinder.ui.detail;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.imagefinder.commons.SingleLiveEvent;
import com.example.imagefinder.data.local.LocalDataSource;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailViewModel extends BaseViewModel {

    @NonNull
    private final LocalDataSource localDataSource;
    @NonNull
    private final MutableLiveData<Thumbnail> thumbnail = new MutableLiveData<>();
    @NonNull
    private final SingleLiveEvent<Boolean> isLocalDataUpdate = new SingleLiveEvent<>();
    @NonNull
    private final SingleLiveEvent<Boolean> isUpdateSuccess = new SingleLiveEvent<>();

    public DetailViewModel(
            @NonNull LocalDataSource localDataSource
    ) {
        this.localDataSource = localDataSource;
    }

    @NonNull
    public LiveData<Thumbnail> getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail.setValue(thumbnail);
    }

    @NonNull
    public LiveData<Boolean> getIsLocalDataUpdate() {
        return isLocalDataUpdate;
    }

    @NonNull
    public LiveData<Boolean> getIsUpdateSuccess() {
        return isUpdateSuccess;
    }

    void storeImages() {
        if (thumbnail.getValue() != null) {
            addDisposable(localDataSource.insertThumbnail(thumbnail.getValue())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                                isLocalDataUpdate.setValue(true);
                                isUpdateSuccess.setValue(true);
                            },
                            error -> {
                                isUpdateSuccess.setValue(false);
                                error.printStackTrace();
                            }
                    )
            );
        }
    }

    void deleteImages() {
        if (thumbnail.getValue() != null) {
            addDisposable(localDataSource.deleteStoredThumbnail(thumbnail.getValue())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                                isLocalDataUpdate.setValue(true);
                                isUpdateSuccess.setValue(true);
                            },
                            error -> {
                                isUpdateSuccess.setValue(false);
                                error.printStackTrace();
                            }
                    )
            );
        }
    }
}
