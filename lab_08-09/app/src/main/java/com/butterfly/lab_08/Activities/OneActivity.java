package com.butterfly.lab_08.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.butterfly.lab_08.R;
import com.butterfly.lab_08.units.Student;

import java.util.ArrayList;

public class OneActivity extends AppCompatActivity {

    static ArrayList<Student> studentArrayList = new ArrayList<>();

    EditText name, surname, middleName;
    Button next;
    MenuItem action_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        middleName = (EditText) findViewById(R.id.middleName);
        next = (Button) findViewById(R.id.next2);
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
                Intent intent1 = new Intent(this, ChangeActivity.class);
                intent1.putExtra("name", name.getText().toString());
                intent1.putExtra("surname", surname.getText().toString());
                intent1.putExtra("middleName", middleName.getText().toString());
                startActivity(intent1);
                return (true);
            case R.id.action_exit:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
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
