package com.butterfly.lab_08.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.butterfly.lab_08.DatabaseHelper;
import com.butterfly.lab_08.R;

public class MainActivity extends AppCompatActivity {

    EditText login, password;
    Button enter, registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        enter = (Button) findViewById(R.id.enter);
        registration = (Button) findViewById(R.id.registration);

        control();
    }

    public void control() {
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    Intent admin = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(admin);
                }
                else if (new DatabaseHelper(MainActivity.this).getWritableDatabase().query("register",
                        new String[]{"login", "password"}, "login = ? AND password = ?",
                        new String[]{login.getText().toString(), password.getText().toString()},
                        null, null, null).getCount() == 1) {
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    Intent student = new Intent(MainActivity.this, OneActivity.class);
                    startActivity(student);
                } else {
                    Toast.makeText(MainActivity.this, "not OK", Toast.LENGTH_SHORT).show();
                }
                login.setText("");
                password.setText("");
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
