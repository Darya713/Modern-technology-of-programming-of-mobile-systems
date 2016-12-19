package com.butterfly.lab_08.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.butterfly.lab_08.R;

public class WindowActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(getIntent().getStringExtra("list"));
    }
}
