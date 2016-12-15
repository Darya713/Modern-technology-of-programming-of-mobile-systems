package com.butterfly.lab_10_11.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.butterfly.lab_10_11.R;
import com.butterfly.lab_10_11.units.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ItemsListFragment extends ListFragment {

    private ArrayAdapter<Item> adapterItems;
    private ListView lvItems;
    private ImageView FAB;
    public ListAdapter adapter;
    String textToSend;
    int pos;
    static String arg = "default";
    int check = 0;

    private OnItemSelectedListener listener;
    ArrayList<Item> items = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create arraylist from item fixtures

        Bundle args = this.getArguments();
        if (args != null) {
            if (args.getString("sortBy").equals("surname")) {
                ArrayList<Student> temp = WorkWithDB.Select(getContext());
                Collections.sort(temp, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.getSurname().compareTo(s2.getSurname());
                    }
                });
                items.clear();
                for (int i = 0; i < Item.getItems(temp).size(); i++) {
                    items.add(Item.getItems(temp).get(i));
                }
            }
            if (args.getString("sortBy").equals("name")) {
                ArrayList<Student> temp = WorkWithDB.Select(getContext());
                Collections.sort(temp, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.getName().compareTo(s2.getName());
                    }
                });
                items.clear();
                for (int i = 0; i < Item.getItems(temp).size(); i++) {
                    items.add(Item.getItems(temp).get(i));
                }
            }
            if (args.getString("sortBy").equals("rating")) {
                ArrayList<Student> temp = WorkWithDB.Select(getContext());
                Collections.sort(temp, new Comparator<Student>() {
                    @Override
                    public int compare(Student s1, Student s2) {
                        return s1.getMiddleName().compareTo(s2.getMiddleName());
                    }
                });
                items.clear();
                for (int i = 0; i < Item.getItems(temp).size(); i++) {
                    items.add(Item.getItems(temp).get(i));
                }
            }
        }
        else {
            for (int i = 0; i < Item.getItems(getContext()).size(); i++) {
                items.add(Item.getItems(getContext()).get(i));
            }
        }
        adapterItems = new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_1, items);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items_list, container,
                false);
    }

    @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter = new ListAdapter(getActivity(), items);
        lvItems = (ListView) view.findViewById(R.id.lvItems);
        FAB = (ImageButton) view.findViewById(R.id.FAB);
        if (check == 1) {
            FAB.setVisibility(View.VISIBLE);
        }
        else {
            FAB.setVisibility(View.GONE);
        }
        lvItems.setAdapter(adapter);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position,
                                    long rowId) {
                // Retrieve item based on position
                // Fire selected event for item
                listener.onItemSelected(position, arg, check);
            }
        });
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray sparseBooleanArray = lvItems.getCheckedItemPositions();
                ArrayList<Integer> temp = new ArrayList<Integer>();
                int count = 0;
                for (int i = 0; i < sparseBooleanArray.size(); i++) {
                    int key = sparseBooleanArray.keyAt(i);
                    if (sparseBooleanArray.get(key))
                        temp.add(count++, items.get(key).getID());
                }
                WorkWithDB.Update(getActivity(), temp);
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.flContainer, new ItemsListFragment());
                ft.commit();
            }
        });
        registerForContextMenu(lvItems);

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                textToSend = "Фамилия: " + WorkWithDB.Select(getContext()).get(position).getSurname() + "\n" +
                        "Имя: " + WorkWithDB.Select(getContext()).get(position).getName() + "\n" +
                        "Отчество: " + WorkWithDB.Select(getContext()).get(position).getMiddleName() + "\n" +
                        "День рождения: " + WorkWithDB.Select(getContext()).get(position).getBirthday() + "\n" +
                        "Рейтинг: " + WorkWithDB.Select(getContext()).get(position).getRating() + "\n" +
                        "Курсы: " + WorkWithDB.Select(getContext()).get(position).getCourse();
                pos = position;
                return false;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ItemsListFragment.OnItemSelectedListener");
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int position, String argument, int check);
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        lvItems.setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_MULTIPLE
                        : ListView.CHOICE_MODE_SINGLE);
    }

    final int MENU_1 = 1;
    final int MENU_2 = 2;
    final int MENU_3 = 3;
    final int MENU_4 = 4;
    final int MENU_5 = 5;
    final int MENU_6 = 6;
    final int MENU_7 = 7;
    final int MENU_8 = 8;

    FragmentTransaction ft;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, MENU_1, Menu.NONE, "Copy");
        menu.add(Menu.NONE, MENU_2, Menu.NONE, "Send");
        menu.add(Menu.NONE, MENU_3, Menu.NONE, "Delete");
        menu.add(Menu.NONE, MENU_4, Menu.NONE, "Sort by surname");
        menu.add(Menu.NONE, MENU_5, Menu.NONE, "Sort by name");
        menu.add(Menu.NONE, MENU_6, Menu.NONE, "Sort by rating");
        menu.add(Menu.NONE, MENU_7, Menu.NONE, "Delete all");
        menu.add(Menu.NONE, MENU_8, Menu.NONE, "Add favourites");
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
                    Toast.makeText(getActivity(), "Some error", Toast.LENGTH_SHORT).show();
                }
                break;
            case MENU_3:
                WorkWithDB.Delete(getContext(), WorkWithDB.Select(getContext()).get(pos));
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.flContainer, new ItemsListFragment());
                ft.commit();
                break;
            case MENU_4:
                Sorting("Surname", "sortBy", "surname");
                break;
            case MENU_5:
                Sorting("Name", "sortBy", "name");
                break;
            case MENU_6:
                Sorting("Rating", "sortBy", "rating");
                break;
            case MENU_7:
                WorkWithDB.DeleteAll(getContext());
                ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.flContainer, new ItemsListFragment());
                ft.commit();
                break;
            case MENU_8:
                check = 1;
                setActivateOnItemClick(true);
                ArrayAdapter<Item> adapterItems = new ArrayAdapter<Item>(getActivity(),
                        android.R.layout.simple_list_item_multiple_choice, items);
                lvItems.setAdapter(adapterItems);
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isStar() == 1)
                        lvItems.setItemChecked(i, true);
                    else
                        lvItems.setItemChecked(i, false);
                }
                if (check == 1) {
                    FAB.setVisibility(View.VISIBLE);
                }
                else {
                    FAB.setVisibility(View.GONE);
                }

                break;
        }
        return super.onContextItemSelected(item);
    }


    private void Sorting(String arg, String key, String value) {
        ItemsListFragment.arg = arg;
        Bundle args = new Bundle();
        args.putString(key, value);
        ItemsListFragment fragment = new ItemsListFragment();
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
    }
}
