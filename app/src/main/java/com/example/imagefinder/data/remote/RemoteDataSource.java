package com.example.imagefinder.data.remote;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.model.Thumbnail;
import io.reactivex.Single;

import java.util.List;

public interface RemoteDataSource {

    @NonNull
    Single<List<Thumbnail>> getThumbnailsByAllSource(
            int index,
            @NonNull String keyword
    );
}
