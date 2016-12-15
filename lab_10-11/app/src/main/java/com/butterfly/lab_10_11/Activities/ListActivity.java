package com.butterfly.lab_10_11.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.butterfly.lab_10_11.R;

public class ListActivity extends AppCompatActivity {

    TextView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = (TextView) findViewById(R.id.listOfStudents);
        list.setText(WorkWithDB.Select(this).toString());
    }
}
