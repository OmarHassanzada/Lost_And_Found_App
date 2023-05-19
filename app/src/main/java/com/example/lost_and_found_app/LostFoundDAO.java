package com.example.lost_and_found_app;

import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class LostFoundDAO {
    private SQLiteDatabase DB;
    private LostFoundDbHelper Helper;

    private static final String TABLE_NAME = "lost_found_items";
    private static final String COLUMN_ID = "id";

    public LostFoundDAO(Context context) {
        Helper = new LostFoundDbHelper(context);
    }

    public void open() {
        DB = Helper.getWritableDatabase();
    }

    public void close() {
        Helper.close();
    }

    public long insertItem(String postType, String name, String phone, String description, String date, String location) {
        ContentValues values = new ContentValues();
        values.put(LostFoundContract.LostFoundEntry.COLUMN_POST_TYPE, postType);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_NAME, name);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_PHONE, phone);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_DESCRIPTION, description);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_DATE, date);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_LOCATION, location);

        return DB.insert(LostFoundContract.LostFoundEntry.TABLE_NAME, null, values);
    }

    public void updateItem(long itemId, String postType, String name, String phone, String description, String date, String location) {
        ContentValues values = new ContentValues();
        values.put(LostFoundContract.LostFoundEntry.COLUMN_POST_TYPE, postType);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_NAME, name);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_PHONE, phone);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_DESCRIPTION, description);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_DATE, date);
        values.put(LostFoundContract.LostFoundEntry.COLUMN_LOCATION, location);

        String selection = LostFoundContract.LostFoundEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(itemId) };

        DB.update(LostFoundContract.LostFoundEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public void deleteItem(LostFoundItem item) {
        SQLiteDatabase db = Helper.getWritableDatabase();
        String[] whereArgs = {String.valueOf(item.getItemId())};
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", whereArgs);
        db.close();
    }

    public List<LostFoundItem> getAllItems() {
        List<LostFoundItem> items = new ArrayList<>();

        Cursor cursor = DB.query(
                LostFoundContract.LostFoundEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_ID));
            String postType = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_POST_TYPE));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_PHONE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_DESCRIPTION));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_DATE));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_LOCATION));

            LostFoundItem item = new LostFoundItem(itemId, postType, name, phone, description, date, location);
            items.add(item);
        }

        cursor.close();

        return items;
    }


    public LostFoundItem getItemById(long itemId) {
        String selection = LostFoundContract.LostFoundEntry.COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(itemId) };

        Cursor cursor = DB.query(
                LostFoundContract.LostFoundEntry.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        LostFoundItem item = null;
        if (cursor.moveToFirst()) {
            String postType = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_POST_TYPE));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_PHONE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_DESCRIPTION));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_DATE));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(LostFoundContract.LostFoundEntry.COLUMN_LOCATION));

            item = new LostFoundItem(itemId, postType, name, phone, description, date, location);
        }

        cursor.close();

        return item;
    }

}

