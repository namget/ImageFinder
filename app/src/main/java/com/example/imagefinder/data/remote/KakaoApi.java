package com.example.imagefinder.data.remote;

import com.example.imagefinder.data.remote.response.ImageResponse;
import com.example.imagefinder.data.remote.response.VideoResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KakaoApi {

    @GET(value = "v2/search/vclip")
    Single<VideoResponse> getVideos(
            @Query(value = "query", encoded = true) String keyword,
            @Query(value = "page", encoded = true) int page,
            @Query(value = "size", encoded = true) int size);

    @GET(value = "v2/search/image")
    Single<ImageResponse> getImages(
            @Query(value = "query", encoded = true) String keyword,
            @Query(value = "page", encoded = true) int page,
            @Query(value = "size", encoded = true) int size);
}
