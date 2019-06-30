package com.example.imagefinder.data.model;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.remote.response.ImageResponse;
import com.example.imagefinder.data.remote.response.VideoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class IndexedPage {

    private int index;

    private boolean isEndImageSource;

    private boolean isEndImageVideoSource;

    @NonNull
    private List<Thumbnail> thumbnails;

    private IndexedPage(
            int index,
            boolean isEndImageSource,
            boolean isEndImageVideoSource,
            @NonNull List<Thumbnail> thumbnails
    ) {
        this.index = index;
        this.isEndImageSource = isEndImageSource;
        this.isEndImageVideoSource = isEndImageVideoSource;
        this.thumbnails = thumbnails;
    }

    @NonNull
    public static IndexedPage of(
            int index,
            @NonNull ImageResponse imageResponse,
            @NonNull VideoResponse videoResponse) {

        final List<Thumbnail> thumbnails = new ArrayList<>();

        for (ImageResponse.Document imageDoc : imageResponse.getDocuments()) {
            thumbnails.add(Thumbnail.from(imageDoc));
        }

        for (VideoResponse.Document videoDoc : videoResponse.getDocuments()) {
            thumbnails.add(Thumbnail.from(videoDoc));
        }

        return new IndexedPage(
                index,
                imageResponse.getMeta().getIsEnd(),
                videoResponse.getMeta().getIsEnd(),
                thumbnails
        );
    }

    @NonNull
    public static IndexedPage from(int index, @NonNull ImageResponse imageResponse) {
        final List<Thumbnail> thumbnails = new ArrayList<>();

        for (ImageResponse.Document imageDoc : imageResponse.getDocuments()) {
            thumbnails.add(Thumbnail.from(imageDoc));
        }

        return new IndexedPage(
                index,
                imageResponse.getMeta().getIsEnd(),
                false,
                thumbnails
        );
    }

    @NonNull
    public static IndexedPage from(int index, @NonNull VideoResponse videoResponse) {
        final List<Thumbnail> thumbnails = new ArrayList<>();

        for (VideoResponse.Document videoDoc : videoResponse.getDocuments()) {
            thumbnails.add(Thumbnail.from(videoDoc));
        }

        return new IndexedPage(
                index,
                false,
                videoResponse.getMeta().getIsEnd(),
                thumbnails
        );
    }

    public int getIndex() {
        return index;
    }

    public boolean isEndImageSource() {
        return isEndImageSource;
    }

    public boolean isEndImageVideoSource() {
        return isEndImageVideoSource;
    }

    @NonNull
    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexedPage that = (IndexedPage) o;
        return index == that.index &&
                isEndImageSource == that.isEndImageSource &&
                isEndImageVideoSource == that.isEndImageVideoSource &&
                thumbnails.equals(that.thumbnails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, isEndImageSource, isEndImageVideoSource, thumbnails);
    }

    @NonNull
    @Override
    public String toString() {
        return "IndexedPage{" +
                "index=" + index +
                ", isEndImageSource=" + isEndImageSource +
                ", isEndImageVideoSource=" + isEndImageVideoSource +
                ", thumbnails=" + thumbnails +
                '}';
    }
}
