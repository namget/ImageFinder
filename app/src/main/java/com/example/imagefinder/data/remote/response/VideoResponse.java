package com.example.imagefinder.data.remote.response;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class VideoResponse {

    @SerializedName("documents")
    private List<Document> documents;
    @SerializedName("meta")
    private Meta meta;

    @NonNull
    public List<Document> getDocuments() {
        return documents;
    }

    @NonNull
    public Meta getMeta() {
        return meta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoResponse that = (VideoResponse) o;
        return Objects.equals(documents, that.documents) &&
                Objects.equals(meta, that.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documents, meta);
    }

    @NonNull
    @Override
    public String toString() {
        return "VideoResponse{" +
                "documents=" + documents +
                ", meta=" + meta +
                '}';
    }

    public static class Document {

        @SerializedName("author")
        private String author;
        @SerializedName("datetime")
        private String datetime;
        @SerializedName("play_time")
        private long playTime;
        @SerializedName("thumbnail")
        private String thumbnail;
        @SerializedName("title")
        private String title;
        @SerializedName("url")
        private String url;

        @NonNull
        public String getAuthor() {
            return author;
        }

        @NonNull
        public String getDatetime() {
            return datetime;
        }

        public long getPlayTime() {
            return playTime;
        }

        @NonNull
        public String getThumbnail() {
            return thumbnail;
        }

        @NonNull
        public String getTitle() {
            return title;
        }

        @NonNull
        public String getUrl() {
            return url;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Document document = (Document) o;
            return playTime == document.playTime &&
                    Objects.equals(author, document.author) &&
                    Objects.equals(datetime, document.datetime) &&
                    Objects.equals(thumbnail, document.thumbnail) &&
                    Objects.equals(title, document.title) &&
                    Objects.equals(url, document.url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(author, datetime, playTime, thumbnail, title, url);
        }

        @NonNull
        @Override
        public String toString() {
            return "Document{" +
                    "author='" + author + '\'' +
                    ", datetime='" + datetime + '\'' +
                    ", playTime=" + playTime +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
