package com.example.imagefinder.data.remote;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.model.IndexedPage;
import io.reactivex.Single;

public interface RemoteDataSource {

    @NonNull
    Single<IndexedPage> getThumbnailsByAllSource(
            int index,
            @NonNull String keyword
    );

    @NonNull
    Single<IndexedPage> getThumbnailByImageSource(
            int index,
            @NonNull String keyword
    );

    @NonNull
    Single<IndexedPage> getThumbnailByVideoSource(
            int index,
            @NonNull String keyword
    );
}
