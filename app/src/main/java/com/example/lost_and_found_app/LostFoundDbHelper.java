package com.example.lost_and_found_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LostFoundDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lost_found.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LostFoundContract.LostFoundEntry.TABLE_NAME + " (" +
                    LostFoundContract.LostFoundEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LostFoundContract.LostFoundEntry.COLUMN_POST_TYPE + " TEXT," +
                    LostFoundContract.LostFoundEntry.COLUMN_NAME + " TEXT," +
                    LostFoundContract.LostFoundEntry.COLUMN_PHONE + " TEXT," +
                    LostFoundContract.LostFoundEntry.COLUMN_DESCRIPTION + " TEXT," +
                    LostFoundContract.LostFoundEntry.COLUMN_DATE + " TEXT," +
                    LostFoundContract.LostFoundEntry.COLUMN_LOCATION + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LostFoundContract.LostFoundEntry.TABLE_NAME;

    public LostFoundDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

