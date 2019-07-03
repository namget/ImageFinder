package com.example.imagefinder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.example.imagefinder.data.remote.response.ImageResponse;
import com.example.imagefinder.data.remote.response.VideoResponse;
import com.example.imagefinder.enums.DataState;
import com.example.imagefinder.enums.ThumbnailSource;
import com.example.imagefinder.utils.DateUtils;

import java.util.*;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Thumbnail implements Parcelable {

    public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
        @Override
        public Thumbnail createFromParcel(Parcel source) {
            return new Thumbnail(source);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };
    @NonNull
    private final String imageUri;
    @NonNull
    private final Date dateTime;
    @NonNull
    private final String content;
    @NonNull
    private final ThumbnailSource source;
    @NonNull
    private final DataState dataState;

    private Thumbnail(
            @NonNull String imageUri,
            @NonNull Date dateTime,
            @NonNull String content,
            @NonNull ThumbnailSource source,
            @NonNull DataState dataState
    ) {
        this.imageUri = imageUri;
        this.dateTime = dateTime;
        this.content = content;
        this.source = source;
        this.dataState = dataState;
    }

    protected Thumbnail(Parcel in) {
        this.imageUri = Objects.requireNonNull(in.readString());
        this.dateTime = new Date(in.readLong());
        this.content = Objects.requireNonNull(in.readString());
        this.source = ThumbnailSource.values()[in.readInt()];
        this.dataState = DataState.values()[in.readInt()];
    }

    @NonNull
    public static Thumbnail stateChangeToLocalData(Thumbnail thumbnail) {
        return new Thumbnail(
                thumbnail.getImageUri(),
                thumbnail.getDateTime(),
                thumbnail.getContent(),
                thumbnail.getSource(),
                DataState.LOCAL_STORED
        );
    }

    @NonNull
    public static Thumbnail from(@NonNull ImageResponse.Document document) {
        return new Thumbnail(document.getThumbnailUrl(),
                DateUtils.parseKakaoDateToDate(document.getDatetime()),
                document.getCollection() + ": " + document.getDisplaySitename(),
                ThumbnailSource.IMAGE_THUMBNAIL,
                DataState.CACHED);
    }

    @NonNull
    public static Thumbnail from(@NonNull VideoResponse.Document document) {
        return new Thumbnail(document.getThumbnail(),
                DateUtils.parseKakaoDateToDate(document.getDatetime()),
                document.getAuthor() + ", " + document.getTitle(),
                ThumbnailSource.VIDEO_THUMBNAIL,
                DataState.CACHED);
    }

    @NonNull
    public static List<Thumbnail> toListByResponse(ImageResponse response) {
        List<Thumbnail> thumbnails = new ArrayList<>();
        for (ImageResponse.Document document : response.getDocuments()) {
            thumbnails.add(Thumbnail.from(document));
        }
        return thumbnails;
    }

    @NonNull
    public static List<Thumbnail> toListByResponse(VideoResponse response) {
        List<Thumbnail> thumbnails = new ArrayList<>();
        for (VideoResponse.Document document : response.getDocuments()) {
            thumbnails.add(Thumbnail.from(document));
        }
        return thumbnails;
    }

    public static List<Thumbnail> sortByDateTime(List<Thumbnail> thumbnails) {
        List<Thumbnail> result = new ArrayList<>(thumbnails);
        Collections.sort(result, (o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));
        return result;
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

    @NonNull
    public DataState getDataState() {
        return dataState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thumbnail thumbnail = (Thumbnail) o;
        return imageUri.equals(thumbnail.imageUri) &&
                dateTime.equals(thumbnail.dateTime) &&
                content.equals(thumbnail.content) &&
                source == thumbnail.source &&
                dataState == thumbnail.dataState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUri, dateTime, content, source, dataState);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return "Thumbnail{" +
                "imageUri='" + imageUri + '\'' +
                ", dateTime=" + dateTime +
                ", content='" + content + '\'' +
                ", source=" + source +
                ", dataState=" + dataState +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUri);
        dest.writeLong(this.dateTime.getTime());
        dest.writeString(this.content);
        dest.writeInt(this.source.ordinal());
        dest.writeInt(this.dataState.ordinal());
    }
}
