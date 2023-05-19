package com.example.lost_and_found_app;

import android.provider.BaseColumns;

public final class LostFoundContract {
    // This constructor prevents instantiation
    private LostFoundContract() {}

    public static class LostFoundEntry implements BaseColumns {
        public static final String TABLE_NAME = "lost_found_items";
        public static final String COLUMN_POST_TYPE = "post_type";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_ID = "id";
    }
}
