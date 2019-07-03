package com.example.imagefinder.enums;

public enum ThumbnailSource {

    VIDEO_THUMBNAIL("동영상 썸네일"), IMAGE_THUMBNAIL("이미지 썸네일");

    final private String name;

    ThumbnailSource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
