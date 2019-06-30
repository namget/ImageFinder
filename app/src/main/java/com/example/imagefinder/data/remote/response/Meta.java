package com.example.imagefinder.data.remote.response;


import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@SuppressWarnings("unused")
public class Meta {

    @SerializedName("is_end")
    private boolean isEnd;
    @SerializedName("pageable_count")
    private long pageableCount;
    @SerializedName("total_count")
    private long totalCount;

    public boolean getIsEnd() {
        return isEnd;
    }

    public long getPageableCount() {
        return pageableCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meta meta = (Meta) o;
        return isEnd == meta.isEnd &&
                pageableCount == meta.pageableCount &&
                totalCount == meta.totalCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isEnd, pageableCount, totalCount);
    }

    @NonNull
    @Override
    public String toString() {
        return "Meta{" +
                "isEnd=" + isEnd +
                ", pageableCount=" + pageableCount +
                ", totalCount=" + totalCount +
                '}';
    }
}