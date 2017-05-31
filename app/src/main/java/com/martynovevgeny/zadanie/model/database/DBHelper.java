package com.martynovevgeny.zadanie.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by websu on 30.05.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = "dbLogs";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_db";

    public static final String TABLE_CONTACT_NAME = "contact";

    public static final String CONTACT_ID = "_id_contact";
    public static final String CONTACT_NAME = "name_contact";
    public static final String CONTACT_SURNAME = "surname_contact";
    public static final String CONTACT_AGE = "age_contact";
    public static final String CONTACT_CITY = "phone_contact";
    public static final String CONTACT_EMAIL = "email_contact";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "--- on create database ---");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACT_NAME + "("
                + CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CONTACT_NAME + " TEXT NOT NULL, "
                + CONTACT_SURNAME + " TEXT NOT NULL, "
                + CONTACT_AGE + " INTEGER NOT NULL, "
                + CONTACT_CITY + " TEXT NOT NULL, "
                + CONTACT_EMAIL + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d(TAG, "--- on update database ---");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT_NAME);
    }
}
