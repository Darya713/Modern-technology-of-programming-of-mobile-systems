package com.butterfly.lab_08;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "oop_lab_08", null, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE register (" +
                "login TEXT PRIMARY KEY, " +
                "password TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST register");
        db.execSQL("CREATE TABLE register (" +
                "login TEXT PRIMARY KEY, " +
                "password TEXT);");
    }
}
