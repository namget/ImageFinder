package com.example.imagefinder.data.remote;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.model.Thumbnail;
import com.example.imagefinder.utils.TextUtils;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

import static com.example.imagefinder.commons.Constants.*;

public class RemoteDataSourceImpl implements RemoteDataSource {

    private static RemoteDataSource INSTANCE;

    private final KakaoApi kakaoApi;

    private final String key = "KakaoAK 8cec532cb83faad0cae1d3943b48f0ca";

    private RemoteDataSourceImpl() {
        String URL = "https://dapi.kakao.com/";
        kakaoApi = new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(
                                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                        )
                        .addInterceptor(
                                chain -> chain.proceed(
                                        chain.request()
                                                .newBuilder()
                                                .addHeader("Authorization", key)
                                                .build()
                                )
                        )
                        .build()
                )
                .build()
                .create(KakaoApi.class);
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSourceImpl();
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public Single<List<Thumbnail>> getThumbnailsByAllSource(int index, @NonNull String keyword) {
        if (TextUtils.isNotEmpty(keyword)) {
            return Single.zip(
                    getThumbnailsByImageSource(index, keyword),
                    getThumbnailsByVideoSource(index, keyword),
                    (thumbnailsFromImageSource, thumbnailsFromVideoSource) -> {
                        List<Thumbnail> thumbnails = new ArrayList<>(thumbnailsFromImageSource);
                        thumbnails.addAll(thumbnailsFromVideoSource);
                        return thumbnails;
                    })
                    .map(Thumbnail::sortByDateTime)
                    .subscribeOn(Schedulers.io());
        } else {
            return Single.just(new ArrayList<>());
        }
    }

    @NonNull
    private Single<List<Thumbnail>> getThumbnailsByImageSource(int index, @NonNull String keyword) {
        if (index <= IMAGE_SOURCE_MAX_INDEX) {
            return kakaoApi.getImages(keyword, index, PAGE_SIZE)
                    .subscribeOn(Schedulers.io())
                    .map(Thumbnail::toListByResponse);
        } else {
            return Single.just(new ArrayList<>());
        }
    }

    @NonNull
    private Single<List<Thumbnail>> getThumbnailsByVideoSource(int index, @NonNull String keyword) {
        if (index <= VIDEO_SOURCE_MAX_INDEX) {
            return kakaoApi.getVideos(keyword, index, PAGE_SIZE)
                    .subscribeOn(Schedulers.io())
                    .map(Thumbnail::toListByResponse);
        } else {
            return Single.just(new ArrayList<>());
        }
    }
}
