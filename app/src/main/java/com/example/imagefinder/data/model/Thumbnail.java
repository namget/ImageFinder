package com.example.imagefinder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.example.imagefinder.data.remote.response.ImageResponse;
import com.example.imagefinder.data.remote.response.VideoResponse;
import com.example.imagefinder.enums.ThumbnailSource;
import com.example.imagefinder.utils.DateUtils;

import java.util.*;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Thumbnail implements Parcelable {

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
    public static Thumbnail from(@NonNull ImageResponse.Document document) {
        return new Thumbnail(document.getThumbnailUrl(),
                DateUtils.parseKakaoDateToDate(document.getDatetime()),
                document.getCollection() + ": " + document.getDisplaySitename(),
                ThumbnailSource.IMAGE_THUMBNAIL);
    }

    @NonNull
    public static Thumbnail from(@NonNull VideoResponse.Document document) {
        return new Thumbnail(document.getThumbnail(),
                DateUtils.parseKakaoDateToDate(document.getDatetime()),
                document.getAuthor() + ", " + document.getTitle(),
                ThumbnailSource.VIDEO_THUMBNAIL);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUri);
        dest.writeLong(this.dateTime.getTime());
        dest.writeString(this.content);
        dest.writeInt(this.source.ordinal());
    }

    protected Thumbnail(Parcel in) {
        this.imageUri = Objects.requireNonNull(in.readString());
        this.dateTime = new Date(in.readLong());
        this.content = Objects.requireNonNull(in.readString());
        int tmpSource = in.readInt();
        this.source = ThumbnailSource.values()[in.readInt()];
    }

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
}
