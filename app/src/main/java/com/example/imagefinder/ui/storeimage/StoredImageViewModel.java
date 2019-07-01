package com.example.imagefinder.ui.storeimage;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.imagefinder.data.local.LocalDataSource;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.ui.base.BaseViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;

import java.util.List;

public class StoredImageViewModel extends BaseViewModel {

    @NonNull
    private final LocalDataSource localDataSource;
    @NonNull
    private final MutableLiveData<List<Thumbnail>> storedImages = new MutableLiveData<>();

    public StoredImageViewModel(@NonNull LocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    @NonNull
    public LiveData<List<Thumbnail>> getStoredImages() {
        return storedImages;
    }

    void updateImages() {
        addDisposable(localDataSource.loadStoredThumbnails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storedImages::setValue,
                        Throwable::printStackTrace
                )
        );
    }

    void deleteImages(int position) {
        addDisposable(localDataSource.deleteStoredThumbnail(position)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storedImages::setValue,
                        Throwable::printStackTrace)
        );
    }
}
