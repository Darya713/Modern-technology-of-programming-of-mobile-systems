package com.butterfly.lab_10_11.Activities;

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

import com.butterfly.lab_10_11.R;
import com.butterfly.lab_10_11.units.Student;

public class FiveActivity extends AppCompatActivity {

    TextView name, surname, middleName, birthday, rating, course;
    MenuItem action_save;

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
                    Student student = new Student(name.getText().toString(),
                            surname.getText().toString(),
                            middleName.getText().toString(),
                            birthday.getText().toString(),
                            Double.parseDouble(rating.getText().toString()),
                            course.getText().toString());
                    WorkWithDB.Insert(this, student);

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

}
