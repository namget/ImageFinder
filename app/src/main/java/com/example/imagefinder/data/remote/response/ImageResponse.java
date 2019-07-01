package com.example.imagefinder.data.remote.response;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class ImageResponse {

    @Expose
    private List<Document> documents;
    @Expose
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
        ImageResponse that = (ImageResponse) o;
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
        return "ImageResponse{" +
                "documents=" + documents +
                ", meta=" + meta +
                '}';
    }

    public static class Document {

        @Expose
        private String collection;
        @Expose
        private String datetime;
        @SerializedName("display_sitename")
        private String displaySitename;
        @SerializedName("doc_url")
        private String docUrl;
        @Expose
        private long height;
        @SerializedName("image_url")
        private String imageUrl;
        @SerializedName("thumbnail_url")
        private String thumbnailUrl;
        @Expose
        private long width;

        @NonNull
        public String getCollection() {
            return collection;
        }

        @NonNull
        public String getDatetime() {
            return datetime;
        }

        @NonNull
        public String getDisplaySitename() {
            return displaySitename;
        }

        @NonNull
        public String getDocUrl() {
            return docUrl;
        }

        public long getHeight() {
            return height;
        }

        @NonNull
        public String getImageUrl() {
            return imageUrl;
        }

        @NonNull
        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public long getWidth() {
            return width;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Document document = (Document) o;
            return height == document.height &&
                    width == document.width &&
                    Objects.equals(collection, document.collection) &&
                    Objects.equals(datetime, document.datetime) &&
                    Objects.equals(displaySitename, document.displaySitename) &&
                    Objects.equals(docUrl, document.docUrl) &&
                    Objects.equals(imageUrl, document.imageUrl) &&
                    Objects.equals(thumbnailUrl, document.thumbnailUrl);
        }

        @Override
        public int hashCode() {
            return Objects.hash(collection, datetime, displaySitename, docUrl, height, imageUrl, thumbnailUrl, width);
        }

        @NonNull
        @Override
        public String toString() {
            return "Document{" +
                    "collection='" + collection + '\'' +
                    ", datetime='" + datetime + '\'' +
                    ", displaySitename='" + displaySitename + '\'' +
                    ", docUrl='" + docUrl + '\'' +
                    ", height=" + height +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", thumbnailUrl='" + thumbnailUrl + '\'' +
                    ", width=" + width +
                    '}';
        }
    }
}
