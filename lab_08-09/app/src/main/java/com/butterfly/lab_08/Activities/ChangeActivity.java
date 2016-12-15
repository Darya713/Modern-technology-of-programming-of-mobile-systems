package com.butterfly.lab_08.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.butterfly.lab_08.R;
import com.butterfly.lab_08.units.Student;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.butterfly.lab_08.Activities.OneActivity.studentArrayList;

public class ChangeActivity extends AppCompatActivity {

    EditText surname, name, middleName, birthday, rating;
    Spinner course;
    MenuItem action_change;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        surname = (EditText) findViewById(R.id.change_surname);
        name = (EditText) findViewById(R.id.change_name);
        middleName = (EditText) findViewById(R.id.change_middleName);
        birthday = (EditText) findViewById(R.id.change_datePicker);
        rating = (EditText) findViewById(R.id.change_ratingBar);
        course = (Spinner) findViewById(R.id.change_courses);

        if (getIntent().getStringExtra("surname") == null)
            surname.setText("");
        else
            surname.setText(getIntent().getStringExtra("surname"));
        if (getIntent().getStringExtra("name") == null)
            name.setText("");
        else
            name.setText(getIntent().getStringExtra("name"));
        if (getIntent().getStringExtra("middleName") == null)
            middleName.setText("");
        else
            middleName.setText(getIntent().getStringExtra("middleName"));
        if (getIntent().getStringExtra("birthday") == null)
            birthday.setText("");
        else
            birthday.setText(getIntent().getStringExtra("birthday"));
        if (getIntent().getStringExtra("rating") == null)
            rating.setText("");
        else
            rating.setText(getIntent().getStringExtra("rating"));
        if (getIntent().getStringExtra("index") == null)
            course.setSelection(0);
        else
            course.setSelection(Integer.parseInt(getIntent().getStringExtra("index")));

        //control();
    }

    /*public void control() {
        surname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        name;
        middleName;
        birthday;
        rating;
        course;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        action_change = menu.findItem(R.id.action_change);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        action_change.setEnabled(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                try {
                    Student student = new Student(name.getText().toString(),
                            surname.getText().toString(),
                            middleName.getText().toString(),
                            birthday.getText().toString(),
                            Double.parseDouble(rating.getText().toString()),
                            course.getSelectedItem().toString());
                    studentArrayList.add(student);
                    Gson gson = new Gson();
                    String studentToJson = gson.toJson(student);
                    WriteFile(studentToJson);
                    Toast.makeText(this.getApplicationContext(), "Объект успешно создан.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, OneActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                catch (Exception e) {
                    Log.d("Error", e.getMessage());
                    Toast.makeText(this.getApplicationContext(), "Произошла ошибка!", Toast.LENGTH_SHORT).show();
                }
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
