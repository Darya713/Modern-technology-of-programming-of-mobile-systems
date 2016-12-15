package com.butterfly.lab_10_11.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.butterfly.lab_10_11.R;
import com.butterfly.lab_10_11.units.Student;

import java.util.ArrayList;

public class ItemDetailFragment extends Fragment {

    int position = 0;
    static String argument = "default";
    TextView tvTitle;
    TextView tvBody;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                position = getArguments().getInt("position", 0);
                argument = getArguments().getString("argument");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tvTitle = (TextView) view.findViewById(R.id.title);
        tvBody = (TextView) view.findViewById(R.id.body);
        //Bundle args = this.getArguments();
        ArrayList<Student> students = WorkWithDB.Select(getContext());
        if(argument == null || argument.equals("default")) {
            if (students.size() != 0) {
                tvTitle.setText(WorkWithDB.Select(getContext()).get(position).getSurname() +
                        " " + WorkWithDB.Select(getContext()).get(position).getName() +
                        " " + WorkWithDB.Select(getContext()).get(position).getMiddleName());
                tvBody.setText("День рождения: " + WorkWithDB.Select(getContext()).get(position).getBirthday() +
                        "\nРейтинг: " + WorkWithDB.Select(getContext()).get(position).getRating() +
                        "\nКурсы: " + WorkWithDB.Select(getContext()).get(position).getCourse());
            }
        }
        else {
            switch (argument) {
                case "Surname":
                    Order("Surname");
                    break;
                case "Name":
                    Order("Name");
                    break;
                case "Rating":
                    Order("Rating");
                    break;
            }
        }
    }

    private void Order(String argument) {
        tvTitle.setText(WorkWithDB.OrderSelect(getContext(), argument).get(position).getSurname() +
                "\n" + WorkWithDB.OrderSelect(getContext(), argument).get(position).getName() +
                "\n" + WorkWithDB.OrderSelect(getContext(), argument).get(position).getMiddleName());
        tvBody.setText("День рождения: " + WorkWithDB.OrderSelect(getContext(), argument).get(position).getBirthday() +
                "\nРейтинг: " + WorkWithDB.OrderSelect(getContext(), argument).get(position).getRating() +
                "\nКурсы: " + WorkWithDB.OrderSelect(getContext(), argument).get(position).getCourse());
    }

}
