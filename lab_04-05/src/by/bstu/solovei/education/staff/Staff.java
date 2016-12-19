package by.bstu.solovei.education.staff;

import by.bstu.solovei.education.exception.EduException;
import by.bstu.solovei.education.units.Listener;
import by.bstu.solovei.education.units.Person;
import by.bstu.solovei.education.units.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Staff {
    private ArrayList<Person> studlist;

    public Staff() {
        this.studlist = new ArrayList<Person>();
    }

    public Staff(ArrayList<Person> person) {
        this.studlist = person;
    }

    public boolean add(Person person) {
        this.studlist.add(person);
        return true;
    }
    public boolean remove (Person person) {
        this.studlist.remove(person);
        return true;
    }

    public ArrayList<Person> getStudlist() {
        ArrayList arrayList = new ArrayList();
        Iterator iterator = this.studlist.iterator();

        while(iterator.hasNext()) {
            Person person = (Person) iterator.next();
            if (person.getClass() == Student.class) {
                arrayList.add(person);
            }
        }
        return arrayList;
    }

    public ArrayList<Person> getAllStudents() {
        return this.studlist;
    }

    public ArrayList<Person> getListeners() {
        ArrayList arrayList = new ArrayList();
        Iterator iterator = this.studlist.iterator();

        while(iterator.hasNext()) {
            Person person = (Person) iterator.next();
            if (person.getClass() == Listener.class) {
                arrayList.add(person);
            }
        }
        return arrayList;
    }

    public void setStudlist(ArrayList<Person> studlist) {
        this.studlist = studlist;
    }

    /*@Override
    public String toString() {
        return ("Staff \n{\t" +
                this.studlist +
                "\n" + '}');
    }*/

    @Override
    public String toString() {
        return "Staff{" +
                this.studlist +
                '}';
    }
}
