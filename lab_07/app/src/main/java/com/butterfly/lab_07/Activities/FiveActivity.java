package com.butterfly.lab_07.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.butterfly.lab_07.R;
import com.butterfly.lab_07.units.Student;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FiveActivity extends AppCompatActivity {

    TextView name, surname, middleName, birthday, rating, course;
    final ArrayList<Student> studentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        name = (TextView) findViewById(R.id.textView_name);
        surname = (TextView) findViewById(R.id.textView_surname);
        middleName = (TextView) findViewById(R.id.textView_middleName);
        birthday = (TextView) findViewById(R.id.textView_birthday);
        rating = (TextView) findViewById(R.id.textView_rating);
        course = (TextView) findViewById(R.id.textView_course);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        surname.setText(intent.getStringExtra("surname"));
        middleName.setText(intent.getStringExtra("middleName"));
        birthday.setText(intent.getStringExtra("birthday"));
        rating.setText(intent.getStringExtra("rating"));
        course.setText(intent.getStringExtra("courses"));
    }

    public void onClickFive(View view) {
        switch (view.getId()) {
            case R.id.save:
                try {
                    Student student = new Student(name.getText().toString(),
                            surname.getText().toString(),
                            middleName.getText().toString(),
                            birthday.getText().toString(),
                            Double.parseDouble(rating.getText().toString()),
                            course.getText().toString());
                    studentArrayList.add(student);
                    Gson gson = new Gson();
                    String studentToJson = gson.toJson(student);
                    WriteFile(studentToJson);
                    Toast.makeText(this.getApplicationContext(), "Объект успешно создан.", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Log.d("Error", e.getMessage());
                    Toast.makeText(this.getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.cancel:
                Intent intent = new Intent(this, OneActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.list:
                Intent i = new Intent(this, ListActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    private boolean ExistBase(String fname) {
        boolean rc = false;
        File file = new File(super.getFilesDir(), fname);
        if (rc = file.exists())
            Log.d("Log_07", "Файл " + fname + " существует");
        else
            Log.d("Log_07", "Файл " + fname + " не найден");
        return rc;
    }

    private void WriteFile(String string) {
        if (!ExistBase("lab_07.json")) {
            File f = new File(super.getFilesDir(), "lab_07.json");
            try {
                f.createNewFile();
                Log.d("Log_07", "Файл " + "lab_07.json" + " создан");
            }
            catch (IOException e) {
                Log.d("Log_07", "Файл " + "lab_07.json" + " не создан");
            }
        }
        try {
            FileOutputStream outputStream = openFileOutput("lab_07.json", MODE_APPEND);
            outputStream.write(string.getBytes());
            outputStream.close();
            Log.d("Log_07", "Данные записаны");
        }
        catch (IOException e) {
            Log.d("Log_07", e.getMessage());
        }
    }
}
