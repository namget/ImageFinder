package com.example.imagefinder.data.remote;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.model.IndexedPage;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.imagefinder.commons.Contants.PAGE_SIZE;

public class RemoteDataSourceImpl implements RemoteDataSource {

    private static RemoteDataSource INSTANCE;

    private final KakaoApi kakaoApi;

    private final String key = "KakaoAK 8cec532cb83faad0cae1d3943b48f0ca";

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSourceImpl();
        }
        return INSTANCE;
    }

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

    @NonNull
    @Override
    public Single<IndexedPage> getThumbnailsByAllSource(int index, @NonNull String keyword) {
        return Observable.zip(
                kakaoApi.getImages(keyword, index, PAGE_SIZE).toObservable(),
                kakaoApi.getVideos(keyword, index, PAGE_SIZE).toObservable(),
                (imageResponse, videoResponse) -> IndexedPage.of(index, imageResponse, videoResponse)
        )
                .subscribeOn(Schedulers.io())
                .firstOrError();
    }

    @NonNull
    @Override
    public Single<IndexedPage> getThumbnailByImageSource(int index, @NonNull String keyword) {
        return kakaoApi.getImages(keyword, index, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .map(imageResponse -> IndexedPage.from(index, imageResponse));
    }

    @NonNull
    @Override
    public Single<IndexedPage> getThumbnailByVideoSource(int index, @NonNull String keyword) {
        return kakaoApi.getVideos(keyword, index, PAGE_SIZE)
                .subscribeOn(Schedulers.io())
                .map(videoResponse -> IndexedPage.from(index, videoResponse));
    }
}
