package com.example.rozrah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "rozrah";
    public static final String TABLE_CLIENTS = "clients";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NUMBER = "number";
    public static final String KEY_NOF = "nof";
    public static final String KEY_MONTHS = "months";
    public static final String KEY_YEARS = "years";
    public static final String KEY_CVV = "cvv";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_CLIENTS + "(" + KEY_ID + " integer primary key," +
                KEY_EMAIL + " text," +
                KEY_PASSWORD + " text," +
                KEY_NAME + " text," +
                KEY_NUMBER + " text," +
                KEY_NOF + " text," +
                KEY_MONTHS + " text," +
                KEY_YEARS + " text," +
                KEY_CVV + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_CLIENTS);
        onCreate(db);
    }
}
