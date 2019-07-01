package com.example.imagefinder.data.local;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.utils.TextUtils;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.List;

public class FakeLocalDataSourceImpl implements LocalDataSource {

    private static LocalDataSource INSTANCE;

    private final List<Thumbnail> tmpStoredThumbnail = new ArrayList<>();

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeLocalDataSourceImpl();
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public Completable insertThumbnail(@NonNull Thumbnail thumbnail) {

        return Completable.fromObservable(
                Observable.just(thumbnail)
                        .filter(this::isStored)
                        .map(tmpStoredThumbnail::add)
        );
    }

    @NonNull
    @Override
    public Single<List<Thumbnail>> loadStoredThumbnails() {
        return Single.just(tmpStoredThumbnail);
    }

    @NonNull
    @Override
    public Single<List<Thumbnail>> deleteStoredThumbnail(int position) {

        return Single.just(tmpStoredThumbnail)
                .map(t -> {
                    t.remove(position);
                    return t;
                });
    }

    private boolean isStored(Thumbnail thumbnail) {
        for (Thumbnail stored : tmpStoredThumbnail) {
            if (TextUtils.isEquals(stored.getImageUri(), thumbnail.getImageUri())) {
                return false;
            }
        }
        return true;
    }
}