package com.butterfly.lab_07.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.butterfly.lab_07.R;
import com.butterfly.lab_07.units.Student;

import java.util.ArrayList;

public class OneActivity extends AppCompatActivity {

    static ArrayList<Student> studentArrayList = new ArrayList<>();

    EditText name, surname, middleName;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        middleName = (EditText) findViewById(R.id.middleName);
        next = (Button) findViewById(R.id.next2);
    }

    public void onClickOne(View view) {
        if (name.getText().length() != 0 && surname.getText().length() != 0 && middleName.getText().length() != 0) {
            Intent intent = new Intent(this, TwoActivity.class);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("surname", surname.getText().toString());
            intent.putExtra("middleName", middleName.getText().toString());
            startActivity(intent);
        }
        else
            Toast.makeText(this.getApplicationContext(), "Заполните все пустые поля!", Toast.LENGTH_SHORT).show();
    }
}
