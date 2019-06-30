package com.example.imagefinder.data.local;

public class LocalDataSourceImpl implements LocalDataSource {

    private static LocalDataSource INSTANCE;

    public static LocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSourceImpl();
        }
        return INSTANCE;
    }
}
