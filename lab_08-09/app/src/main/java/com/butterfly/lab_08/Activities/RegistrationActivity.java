package com.butterfly.lab_08.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.butterfly.lab_08.DatabaseHelper;
import com.butterfly.lab_08.R;

public class RegistrationActivity extends Activity {

    DatabaseHelper database = new DatabaseHelper(this);
    SQLiteDatabase db;

    EditText st_login, st_password, st_repeat_password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        st_login = (EditText) findViewById(R.id.st_login);
        st_password = (EditText) findViewById(R.id.st_password);
        st_repeat_password = (EditText) findViewById(R.id.st_repeat_password);
        register = (Button) findViewById(R.id.register);

        db = database.getWritableDatabase();
    }

    public void onClick(View view) {
        String login = st_login.getText().toString();
        if (st_password.getText().toString().equals(st_repeat_password.getText().toString())) {
            if (db.query("register", new String[]{"login", "password"}, "login = ?",
                    new String[]{login}, null, null, null).getCount() == 0) {
                ContentValues values = new ContentValues();
                values.put("login", st_login.getText().toString());
                values.put("password", st_password.getText().toString());
                long count = db.insert("register", null, values);
                Toast.makeText(getApplicationContext(), String.valueOf(count),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "Студент с таким логином уже существует!",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Пароли не совпадают!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
