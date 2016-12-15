package com.butterfly.lab_07.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.butterfly.lab_07.R;

public class ThreeActivity extends AppCompatActivity {

    TextView name, surname, middleName, birthday;
    RatingBar ratingBar;

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
