package com.butterfly.lab_10_11.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.butterfly.lab_10_11.R;

public class FourActivity extends AppCompatActivity {

    TextView name, surname, middleName, birthday, rating;
    Spinner courses;
    MenuItem action_save;

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
                intent1.putExtra("birthday", birthday.getText().toString());
                intent1.putExtra("rating", rating.getText().toString());
                intent1.putExtra("index", getIndex(courses, courses.getSelectedItem().toString()));
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

    private int getIndex(Spinner spinner, String myString){
        int index = 0;
        for (int i = 0; i < spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
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
