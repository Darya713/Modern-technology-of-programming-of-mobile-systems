package com.butterfly.lab_07.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.butterfly.lab_07.R;

public class FourActivity extends AppCompatActivity {

    TextView name, surname, middleName, birthday, rating;
    Spinner courses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        name = (TextView) findViewById(R.id.textView_name);
        surname = (TextView) findViewById(R.id.textView_surname);
        middleName = (TextView) findViewById(R.id.textView_middleName);
        birthday = (TextView) findViewById(R.id.textView_birthday);
        rating = (TextView) findViewById(R.id.textView_rating);
        courses = (Spinner) findViewById(R.id.courses);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        surname.setText(intent.getStringExtra("surname"));
        middleName.setText(intent.getStringExtra("middleName"));
        birthday.setText(intent.getStringExtra("birthday"));
        rating.setText(intent.getStringExtra("rating"));

    }

    public void onClickFour(View view) {
        switch (view.getId()) {
            case R.id.back4:
                super.onBackPressed();
                break;
            case R.id.next4:
                Intent intent = new Intent(this, FiveActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("surname", surname.getText().toString());
                intent.putExtra("middleName", middleName.getText().toString());
                intent.putExtra("birthday", birthday.getText().toString());
                intent.putExtra("rating", rating.getText().toString());
                intent.putExtra("courses", courses.getSelectedItem().toString());
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
