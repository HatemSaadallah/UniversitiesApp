package com.example.universities2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class FeedReaderContract {
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_USERNAME + " TEXT UNIQUE," +
                    FeedEntry.COLUMN_NAME_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


    public static class FeedReaderDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "user.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }


}