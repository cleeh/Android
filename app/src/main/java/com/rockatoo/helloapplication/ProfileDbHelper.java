package com.rockatoo.helloapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ProfileDbHelper extends SQLiteOpenHelper {
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PROFILE_TABLE);
    }

    public class ProfileContract{
        public class ProfileEntry implements BaseColumns{
            // Block Object Creation
            private ProfileEntry(){}

            // Set DB Table Info
            public static final String TABLE_NAME = "profiles";
            public static final String COLUMN_NAME_NAME = "name";
            public static final String COLUMN_NAME_EMAIL = "email";
            public static final String COLUMN_NAME_PHONE = "phone";
        }
    }

    /*
     * DB SQL Command
     */
    // profiles Table Creation Context Definition
    private static final String SQL_CREATE_PROFILE_TABLE =
            "create table " + ProfileContract.ProfileEntry.TABLE_NAME + " (" +
                    ProfileContract.ProfileEntry._ID + " integer primary key, " +
                    ProfileContract.ProfileEntry.COLUMN_NAME_NAME + " text," +
                    ProfileContract.ProfileEntry.COLUMN_NAME_EMAIL + " text," +
                    ProfileContract.ProfileEntry.COLUMN_NAME_PHONE + " text" +
                    ")";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "profile.db";
    public ProfileDbHelper(Context context){
        super(context, "profile.db", null, DB_VERSION);
    }

    public ContentValues getProfile(long rowId){
        // No Info
        if(rowId == -1){
            return null;
        }
        SQLiteDatabase db = getReadableDatabase();
        // If Database can't read
        if(db == null){
            throw new SQLiteCantOpenDatabaseException();
        }

        // Select Table and Table Row
        String table = ProfileContract.ProfileEntry.TABLE_NAME;
        String[] columns = {ProfileContract.ProfileEntry._ID,
                ProfileContract.ProfileEntry.COLUMN_NAME_NAME,
                ProfileContract.ProfileEntry.COLUMN_NAME_EMAIL,
                ProfileContract.ProfileEntry.COLUMN_NAME_PHONE
        };
        // Set SQL Query Condition
        String selection = ProfileContract.ProfileEntry._ID + "= ?";
        String[] selectionArgs = {String.valueOf(rowId)};
        // Execute Query
        Cursor cursor = db.query(table, columns, selection, selectionArgs, null, null, null);

        // Extract Info from Cursor
        String name = getStringFromCursor(cursor, ProfileContract.ProfileEntry.COLUMN_NAME_NAME);
        String email = getStringFromCursor(cursor, ProfileContract.ProfileEntry.COLUMN_NAME_EMAIL);
        String phone = getStringFromCursor(cursor, ProfileContract.ProfileEntry.COLUMN_NAME_PHONE);

        // Put Info in ContentValues
        ContentValues values = new ContentValues();
        values.put(ProfileContract.ProfileEntry.COLUMN_NAME_NAME, name);
        values.put(ProfileContract.ProfileEntry.COLUMN_NAME_EMAIL, email);
        values.put(ProfileContract.ProfileEntry.COLUMN_NAME_PHONE, phone);

        return values;
    }

    public long setProfile(String name, String email, String phone){
        SQLiteDatabase db = getWritableDatabase();
        if(db == null){
            throw new SQLiteCantOpenDatabaseException();
        }

        ContentValues values = new ContentValues();
        values.put(ProfileContract.ProfileEntry.COLUMN_NAME_NAME, name);
        values.put(ProfileContract.ProfileEntry.COLUMN_NAME_EMAIL, email);
        values.put(ProfileContract.ProfileEntry.COLUMN_NAME_PHONE, phone);

        // Insert Info in DB Table
        long rowId = db.insert(ProfileContract.ProfileEntry.TABLE_NAME, null, values);

        return rowId;
    }

    private String getStringFromCursor(Cursor cursor, String columnName){
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }
}