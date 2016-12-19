package com.butterfly.lab_10_11.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.butterfly.lab_10_11.DatabaseHelper;
import com.butterfly.lab_10_11.units.Student;

import java.util.ArrayList;

class WorkWithDB {

    private static DatabaseHelper helper;

    static void Insert(Context context, Student student) {
        helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", student.getName());
        values.put("Surname", student.getSurname());
        values.put("MiddleName", student.getMiddleName());
        values.put("Birthday", student.getBirthday());
        values.put("Rating", student.getRating());
        values.put("Course", student.getCourse());

        db.insert("Students", null, values);
    }

    static void Delete(Context context, Student student) {
        helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete("Students", "Name = '" + student.getName() + "' and Surname = '" +
                student.getSurname() + "' and MiddleName = '" + student.getMiddleName() + "'", null);
    }

    static void DeleteAll(Context context) {
        helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete("Students", null, null);
    }

    static ArrayList<Student> Select(Context context) {
        helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query("Students", null, null, null, null, null, null);

        ArrayList<Student> studentArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex("_id");
            int name = cursor.getColumnIndex("Name");
            int surname = cursor.getColumnIndex("Surname");
            int middleName = cursor.getColumnIndex("MiddleName");
            int birthday = cursor.getColumnIndex("Birthday");
            int rating = cursor.getColumnIndex("Rating");
            int course = cursor.getColumnIndex("Course");
            int star = cursor.getColumnIndex("Favourites");

            do {
                Student student = new Student();
                student.setID(Integer.parseInt(cursor.getString(id)));
                student.setName(cursor.getString(name));
                student.setSurname(cursor.getString(surname));
                student.setMiddleName(cursor.getString(middleName));
                student.setBirthday(cursor.getString(birthday));
                student.setRating(Double.parseDouble(cursor.getString(rating)));
                student.setCourse(cursor.getString(course));
                student.setStar(Integer.parseInt(cursor.getString(star)));
                studentArrayList.add(student);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return studentArrayList;
    }

    static ArrayList<Student> OrderSelect(Context context, String argument) {
        helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Students ORDER BY " + argument, null);

        ArrayList<Student> studentArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int id = cursor.getColumnIndex("_id");
            int name = cursor.getColumnIndex("Name");
            int surname = cursor.getColumnIndex("Surname");
            int middleName = cursor.getColumnIndex("MiddleName");
            int birthday = cursor.getColumnIndex("Birthday");
            int rating = cursor.getColumnIndex("Rating");
            int course = cursor.getColumnIndex("Course");

            do {
                Student student = new Student();
                student.setID(Integer.parseInt(cursor.getString(id)));
                student.setName(cursor.getString(name));
                student.setSurname(cursor.getString(surname));
                student.setMiddleName(cursor.getString(middleName));
                student.setBirthday(cursor.getString(birthday));
                student.setRating(Double.parseDouble(cursor.getString(rating)));
                student.setCourse(cursor.getString(course));
                studentArrayList.add(student);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return studentArrayList;
    }

    static void Update(Context context, ArrayList<Integer> items) {
        helper = new DatabaseHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Favourites", 0);
        db.update("Students", values, null, null);
        values.put("Favourites", 1);
        for (int i = 0; i < items.size(); i++) {
            db.update("Students", values, "_id = ?", new String[]{String.valueOf(items.get(i))});
        }
    }

}
