package com.butterfly.lab_07.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.butterfly.lab_07.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ListActivity extends AppCompatActivity {

    TextView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = (TextView) findViewById(R.id.listOfStudents);
        list.setText(ReadFile());
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
