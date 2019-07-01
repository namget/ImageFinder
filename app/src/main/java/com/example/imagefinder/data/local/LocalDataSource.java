package com.example.imagefinder.data.local;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.model.Thumbnail;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

public interface LocalDataSource {

    @NonNull
    Completable insertThumbnail(@NonNull Thumbnail thumbnail);

    @NonNull
    Single<List<Thumbnail>> loadStoredThumbnails();

    @NonNull
    Single<List<Thumbnail>> deleteStoredThumbnail(int position);
}
