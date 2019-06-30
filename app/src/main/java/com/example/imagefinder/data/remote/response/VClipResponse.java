
package com.example.imagefinder.data.remote.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class VClipResponse {

    @SerializedName("documents")
    private List<Document> documents;
    @SerializedName("meta")
    private Meta meta;

    public List<Document> getDocuments() {
        return documents;
    }

    public Meta getMeta() {
        return meta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VClipResponse that = (VClipResponse) o;
        return Objects.equals(documents, that.documents) &&
                Objects.equals(meta, that.meta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documents, meta);
    }

    @Override
    public String toString() {
        return "VClipResponse{" +
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

        public String getAuthor() {
            return author;
        }

        public String getDatetime() {
            return datetime;
        }

        public long getPlayTime() {
            return playTime;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public String getTitle() {
            return title;
        }

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
