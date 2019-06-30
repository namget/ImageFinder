package com.example.imagefinder.data.model;

import androidx.annotation.NonNull;
import com.example.imagefinder.data.remote.response.ImageResponse;
import com.example.imagefinder.data.remote.response.VideoResponse;
import com.example.imagefinder.enums.ThumbnailSource;
import com.example.imagefinder.utils.DateUtils;

import java.util.Date;
import java.util.Objects;

@SuppressWarnings("unused")
public class Thumbnail {

    @NonNull
    private final String imageUri;

    @NonNull
    private final Date dateTime;

    @NonNull
    private final String content;

    @NonNull
    private final ThumbnailSource source;

    private Thumbnail(
            @NonNull String imageUri,
            @NonNull Date dateTime,
            @NonNull String content,
            @NonNull ThumbnailSource source
    ) {
        this.imageUri = imageUri;
        this.dateTime = dateTime;
        this.content = content;
        this.source = source;
    }

    @NonNull
    @SuppressWarnings("WeakerAccess")
    public static Thumbnail from(@NonNull ImageResponse.Document document) {
        return new Thumbnail(document.getThumbnailUrl(),
                DateUtils.parseKakaoDateToDate(document.getDatetime()),
                document.getCollection() + ": " + document.getDisplaySitename(),
                ThumbnailSource.IMAGE_THUMBNAIL);
    }

    @NonNull
    @SuppressWarnings("WeakerAccess")
    public static Thumbnail from(@NonNull VideoResponse.Document document) {
        return new Thumbnail(document.getThumbnail(),
                DateUtils.parseKakaoDateToDate(document.getDatetime()),
                document.getAuthor() + ", " + document.getTitle(),
                ThumbnailSource.VIDEO_THUMBNAIL);
    }

    @NonNull
    public String getImageUri() {
        return imageUri;
    }

    @NonNull
    public Date getDateTime() {
        return dateTime;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    @NonNull
    public ThumbnailSource getSource() {
        return source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thumbnail thumbnail = (Thumbnail) o;
        return imageUri.equals(thumbnail.imageUri) &&
                dateTime.equals(thumbnail.dateTime) &&
                content.equals(thumbnail.content) &&
                source == thumbnail.source;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUri, dateTime, content, source);
    }

    @NonNull
    @Override
    public String toString() {
        return "Thumbnail{" +
                "imageUri='" + imageUri + '\'' +
                ", dateTime=" + dateTime +
                ", content='" + content + '\'' +
                ", source=" + source +
                '}';
    }
}
