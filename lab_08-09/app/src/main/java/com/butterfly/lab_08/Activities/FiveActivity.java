package com.butterfly.lab_08.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.butterfly.lab_08.R;
import com.butterfly.lab_08.units.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FiveActivity extends AppCompatActivity {

    TextView name, surname, middleName, birthday, rating, course;
    MenuItem action_save;

    ArrayList<Student> studentArrayList = new ArrayList<>();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        action_save = menu.findItem(R.id.action_save);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        action_save.setEnabled(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                return (true);
            case R.id.action_change:
                return (true);
            case R.id.action_exit:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickFive(View view) {
        switch (view.getId()) {
            case R.id.save:
                try {
                    Gson gson = new Gson();
                    Student student = new Student(name.getText().toString(),
                            surname.getText().toString(),
                            middleName.getText().toString(),
                            birthday.getText().toString(),
                            Double.parseDouble(rating.getText().toString()),
                            course.getText().toString());
                    if (ReadFile() != "") {
                        TypeToken<ArrayList<Student>> token = new TypeToken<ArrayList<Student>>() {};
                        studentArrayList = gson.fromJson(ReadFile(), token.getType());
                    }
                    studentArrayList.add(student);
                    String studentToJson = gson.toJson(studentArrayList);
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
            FileOutputStream outputStream = openFileOutput("lab_07.json", MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            Log.d("Log_07", "Данные записаны");
        }
        catch (IOException e) {
            Log.d("Log_07", e.getMessage());
        }
    }

    private String ReadFile() {
        //File file = new File(super.getFilesDir(), filename);
        String str = "";
        FileInputStream stream = null;
        StringBuilder sb = new StringBuilder();
        try {
            stream = openFileInput("lab_07.json");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                while ((str = br.readLine()) != null) {
                    sb.append(str);
                }
            }
            finally {
                stream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
