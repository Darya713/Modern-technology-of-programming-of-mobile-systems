package com.butterfly.lab_10_11.Activities;

import android.content.Context;

import com.butterfly.lab_10_11.units.Student;

import java.util.ArrayList;

public class Item {

    private int ID;
    private String title;
    private String body;
    private int star;

    public Item(int ID, String title, String body, int star) {
        this.ID = ID;
        this.title = title;
        this.body = body;
        this.star = star;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int isStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static ArrayList<Item> getItems(Context context) {
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Student> elements = WorkWithDB.Select(context);
        for (int i = 0; i < elements.size(); i++) {
            int newID = elements.get(i).getID();
            String newTitle = elements.get(i).getSurname() + " " + elements.get(i).getName() +
                    " " + elements.get(i).getMiddleName();
            String newBody = "Birthday: " + elements.get(i).getBirthday() + "\n" +
                    "Rating: " + elements.get(i).getRating() + "\n" +
                    "Course: " + elements.get(i).getCourse() + "\n";
            int star = elements.get(i).isStar();
            items.add(new Item(newID, newTitle, newBody, star));
        }
        return items;
    }

    public static ArrayList<Item> getItems(ArrayList<Student> elements) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < elements.size(); i++) {
            int newID = elements.get(i).getID();
            String newTitle = elements.get(i).getSurname() + " " + elements.get(i).getName() + "\n";
            String newBody = "Birthday: " + elements.get(i).getBirthday() + "\n" +
                    "Rating: " + elements.get(i).getRating() + "\n" +
                    "Course: " + elements.get(i).getCourse();
            int star = elements.get(i).isStar();
            items.add(new Item(newID, newTitle, newBody, star));
        }
        return items;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
