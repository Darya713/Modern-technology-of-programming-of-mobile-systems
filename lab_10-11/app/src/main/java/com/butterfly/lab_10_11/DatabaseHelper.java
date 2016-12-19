package com.butterfly.lab_10_11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "oop_lab_10", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE register (" +
                "login TEXT PRIMARY KEY, " +
                "password TEXT);");
        db.execSQL("CREATE TABLE Students " +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Surname TEXT, " +
                "MiddleName TEXT, " +
                "Birthday TEXT, " +
                "Rating REAL, " +
                "Course TEXT, " +
                "Favourites INTEGER DEFAULT 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
