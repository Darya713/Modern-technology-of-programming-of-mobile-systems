package com.butterfly.lab_08.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.butterfly.lab_08.R;

public class ThreeActivity extends AppCompatActivity {

    TextView name, surname, middleName, birthday;
    RatingBar ratingBar;
    MenuItem action_save;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        name = (TextView) findViewById(R.id.textView_name);
        surname = (TextView) findViewById(R.id.textView_surname);
        middleName = (TextView) findViewById(R.id.textView_middleName);
        birthday = (TextView) findViewById(R.id.textView_birthday);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        name.setText(getIntent().getStringExtra("name"));
        surname.setText(getIntent().getStringExtra("surname"));
        middleName.setText(getIntent().getStringExtra("middleName"));
        birthday.setText(getIntent().getStringExtra("birthday"));
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
                intent1.putExtra("rating", String.valueOf(ratingBar.getRating()));
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

    public void onClickThree(View view) {
        switch (view.getId()) {
            case R.id.back3:
                super.onBackPressed();
                break;
            case R.id.next3:
                if (ratingBar.getRating() != 0.0) {
                    Intent intent = new Intent(this, FourActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("surname", surname.getText().toString());
                    intent.putExtra("middleName", middleName.getText().toString());
                    intent.putExtra("birthday", birthday.getText().toString());
                    intent.putExtra("rating", String.valueOf(ratingBar.getRating()));
                    startActivity(intent);
                }
                else
                    Toast.makeText(this.getApplicationContext(), "Укажите рейтинг!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
