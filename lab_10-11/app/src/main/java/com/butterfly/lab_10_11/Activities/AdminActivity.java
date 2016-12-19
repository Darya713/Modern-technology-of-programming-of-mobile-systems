package com.butterfly.lab_10_11.Activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.butterfly.lab_10_11.R;

public class AdminActivity extends AppCompatActivity implements ItemsListFragment.OnItemSelectedListener {

    FrameLayout list_admin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        list_admin = (FrameLayout) findViewById(R.id.flContainer);

        Log.d("DEBUG", getResources().getConfiguration().orientation + "");

        if (savedInstanceState == null) {
            ItemsListFragment firstFragment = new ItemsListFragment();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.flContainer, firstFragment);
            ft.commit();
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ItemDetailFragment secondFragment = new ItemDetailFragment();
            Bundle args = new Bundle();
            args.putInt("position", 0);
            secondFragment.setArguments(args);
            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
            ft2.add(R.id.flContainer2, secondFragment);
            ft2.commit();
        }
    }

    @Override
    public void onItemSelected(int position, String argument, int check) {
        if (check == 0) {
            Toast.makeText(this, "Called By Fragment A: position - " + position, Toast.LENGTH_SHORT).show();

            // Load Item Detail Fragment
            ItemDetailFragment secondFragment = new ItemDetailFragment();

            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putString("argument", argument);
            secondFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle


            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContainer2, secondFragment) // replace flContainer
                        //.addToBackStack(null)
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flContainer, secondFragment) // replace flContainer
                        .addToBackStack(null)
                        .commit();
            }
        }
        check = 0;
    }

}
