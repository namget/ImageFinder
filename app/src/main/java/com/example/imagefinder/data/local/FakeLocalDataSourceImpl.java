package com.example.imagefinder.data.local;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.model.Thumbnail;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeLocalDataSourceImpl implements LocalDataSource {

    private static LocalDataSource INSTANCE;

    private final Map<String, Thumbnail> tmpStoredThumbnail = new HashMap<>();

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeLocalDataSourceImpl();
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public Completable insertThumbnail(@NonNull Thumbnail thumbnail) {

        return Completable.fromSingle(
                Observable.just(thumbnail)
                        .map(Thumbnail::stateChangeToLocalData)
                        .filter(value -> !(tmpStoredThumbnail.containsKey(value.getImageUri())))
                        .firstOrError()
                        .map(value -> {
                            tmpStoredThumbnail.put(value.getImageUri(), value);
                            return tmpStoredThumbnail.get(value.getImageUri());
                        })
        );
    }

    @NonNull
    @Override
    public Single<List<Thumbnail>> loadStoredThumbnails() {
        return Single.just(tmpStoredThumbnail)
                .map(Map::values)
                .map(ArrayList::new);
    }

    @NonNull
    @Override
    public Completable deleteStoredThumbnail(@NonNull Thumbnail thumbnail) {

        return Completable.fromSingle(
                Observable.just(thumbnail)
                        .map(Thumbnail::getImageUri)
                        .filter(tmpStoredThumbnail::containsKey)
                        .firstOrError()
                        .map(tmpStoredThumbnail::remove)
        );
    }
}
