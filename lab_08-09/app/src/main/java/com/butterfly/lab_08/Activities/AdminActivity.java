package com.butterfly.lab_08.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.butterfly.lab_08.R;
import com.butterfly.lab_08.units.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.butterfly.lab_08.Activities.OneActivity.studentArrayList;

public class AdminActivity extends AppCompatActivity {

    ListView list_admin;
    Button openInNewWindow;
    String textToSend;
    int pos;
    ArrayList<String> students = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        list_admin = (ListView) findViewById(R.id.list_admin);
        openInNewWindow = (Button) findViewById(R.id.open);

        try {
            Gson gson = new Gson();
            TypeToken<ArrayList<Student>> token = new TypeToken<ArrayList<Student>>() {
            };
            studentArrayList = gson.fromJson(ReadFile(), token.getType());
            if (getIntent().getStringExtra("sortBySurname") != null)
                Collections.sort(studentArrayList, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.getSurname().compareTo(s2.getSurname());
                    }
                });
            if (getIntent().getStringExtra("sortByName") != null)
                Collections.sort(studentArrayList, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.getName().compareTo(s2.getName());
                    }
                });
            if (getIntent().getStringExtra("sortByRating") != null)
                Collections.sort(studentArrayList, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return Double.compare(s1.getRating(), s2.getRating());
                    }
                });
            for (int i = 0; i < studentArrayList.size(); i++) {
                students.add(i, studentArrayList.get(i).getSurname() + " "
                        + studentArrayList.get(i).getName() + " "
                        + studentArrayList.get(i).getMiddleName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);
            list_admin.setAdapter(adapter);

            registerForContextMenu(list_admin);

            list_admin.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    textToSend = "Фамилия: " + studentArrayList.get(position).getSurname() + "\n" +
                            "Имя: " + studentArrayList.get(position).getName() + "\n" +
                            "Отчество: " + studentArrayList.get(position).getMiddleName() + "\n" +
                            "День рождения: " + studentArrayList.get(position).getBirthday() + "\n" +
                            "Рейтинг: " + studentArrayList.get(position).getRating() + "\n" +
                            "Курсы: " + studentArrayList.get(position).getCourse();
                    pos = position;
                    return false;
                }
            });
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Нет студентов.", Toast.LENGTH_SHORT).show();
        }
    }

    private String ReadFile() {
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

    final int MENU_1 = 1;
    final int MENU_2 = 2;
    final int MENU_3 = 3;
    final int MENU_4 = 4;
    final int MENU_5 = 5;
    final int MENU_6 = 6;
    final int MENU_7 = 7;
    final int MENU_8 = 8;
    final int MENU_9 = 9;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list_admin) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle("");
            menu.add(0, MENU_1, 0, "Copy");
            menu.add(0, MENU_2, 0, "Send");
            menu.add(0, MENU_3, 0, "Delete");
            menu.add(0, MENU_4, 0, "Sort by surname");
            menu.add(0, MENU_5, 0, "Sort by name");
            menu.add(0, MENU_6, 0, "Sort by rating");
            menu.add(0, MENU_7, 0, "Delete all");
            menu.add(0, MENU_8, 0, "Single choice");
            menu.add(0, MENU_9, 0, "Multiple choice");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_1:
                break;
            case MENU_2:
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, textToSend);
                try {
                    startActivity(Intent.createChooser(intent, "Описание действия"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Some error", Toast.LENGTH_SHORT).show();
                }
                break;
            case MENU_3:
                studentArrayList.remove(pos);
                WriteFile(new Gson().toJson(studentArrayList));
                finish();
                Intent i = new Intent(this, AdminActivity.class);
                startActivity(i);
                break;
            case MENU_4:
                finish();
                Intent intent1 = new Intent(this, AdminActivity.class);
                intent1.putExtra("sortBySurname", "surname");
                startActivity(intent1);
                break;
            case MENU_5:
                finish();
                Intent intent2 = new Intent(this, AdminActivity.class);
                intent2.putExtra("sortByName", "name");
                startActivity(intent2);
                break;
            case MENU_6:
                finish();
                Intent intent3 = new Intent(this, AdminActivity.class);
                intent3.putExtra("sortByRating", "rating");
                startActivity(intent3);
                break;
            case MENU_7:
                studentArrayList.clear();
                WriteFile(new Gson().toJson(studentArrayList));
                finish();
                Intent intent4 = new Intent(this, AdminActivity.class);
                startActivity(intent4);
                break;
            case MENU_8:
                openInNewWindow.setClickable(true);
                list_admin.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_list_item_single_choice,
                        students);
                list_admin.setAdapter(adapter);
                break;
            case MENU_9:
                openInNewWindow.setClickable(true);
                list_admin.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                        this, android.R.layout.simple_list_item_multiple_choice,
                        students);
                list_admin.setAdapter(adapter1);
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, WindowActivity.class);
        SparseBooleanArray sparseBooleanArray = list_admin.getCheckedItemPositions();
        String string = "";
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            int key = sparseBooleanArray.keyAt(i);
            if (sparseBooleanArray.get(key))
                string += "Фамилия: " + studentArrayList.get(key).getSurname() + "\n" +
                        "Имя: " + studentArrayList.get(key).getName() + "\n" +
                        "Отчество: " + studentArrayList.get(key).getMiddleName() + "\n" +
                        "День рождения: " + studentArrayList.get(key).getBirthday() + "\n" +
                        "Рейтинг: " + studentArrayList.get(key).getRating() + "\n" +
                        "Курсы: " + studentArrayList.get(key).getCourse() + "\n\n";
        }
        intent.putExtra("list", string);
        startActivity(intent);
        openInNewWindow.setClickable(false);
    }

    private boolean ExistBase(String fname) {
        boolean rc = false;
        File file = new File(super.getFilesDir(), fname);
        if (rc = file.exists())
            Log.d("Log_07", "Файл " + fname + " существует");
        else
            Log.d("Log_07", "Файл " + fname + " не найден");
        return rc;
    }

    private void WriteFile(String string) {
        if (!ExistBase("lab_07.json")) {
            File f = new File(super.getFilesDir(), "lab_07.json");
            try {
                f.createNewFile();
                Log.d("Log_07", "Файл " + "lab_07.json" + " создан");
            }
            catch (IOException e) {
                Log.d("Log_07", "Файл " + "lab_07.json" + " не создан");
            }
        }
        try {
            FileOutputStream outputStream = openFileOutput("lab_07.json", MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            Log.d("Log_07", "Данные записаны");
        }
        catch (IOException e) {
            Log.d("Log_07", e.getMessage());
        }
    }
}
