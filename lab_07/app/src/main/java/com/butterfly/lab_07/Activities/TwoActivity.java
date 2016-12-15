package com.butterfly.lab_07.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.butterfly.lab_07.R;

public class TwoActivity extends AppCompatActivity {

    TextView name, surname, middleName;
    DatePicker datePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        name = (TextView) findViewById(R.id.textView_name);
        surname = (TextView) findViewById(R.id.textView_surname);
        middleName = (TextView) findViewById(R.id.textView_middleName);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        name.setText(getIntent().getStringExtra("name"));
        surname.setText(getIntent().getStringExtra("surname"));
        middleName.setText(getIntent().getStringExtra("middleName"));
    }

    public void onClickTwo(View v) {
        switch (v.getId()) {
            case R.id.back2:
                super.onBackPressed();
                break;
            case R.id.next2:
                Intent intent = new Intent(this, ThreeActivity.class);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("surname", surname.getText().toString());
                intent.putExtra("middleName", middleName.getText().toString());
                intent.putExtra("birthday", datePicker.getDayOfMonth() + "." + (datePicker.getMonth() + 1) + "." + datePicker.getYear());
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
