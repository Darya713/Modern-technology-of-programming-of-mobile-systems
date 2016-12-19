package by.bstu.solovei.education.educationmanager;

import by.bstu.solovei.education.staff.Staff;
import by.bstu.solovei.education.units.Listener;
import by.bstu.solovei.education.units.Person;
import by.bstu.solovei.education.units.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Manager implements IAction {
    public Manager() {}
    @Override
    public Staff createGroup(Staff someGroup, int maxStudents, int maxListener) {
        Staff newGroup = new Staff();
        Iterator iterator = someGroup.getAllStudents().iterator();

        while(iterator.hasNext()) {
            Person person = (Person)iterator.next();
            if (person.getClass() == Student.class && maxStudents != 0) {
                newGroup.add(person);
                --maxStudents;
            }

            if (person.getClass() == Listener.class && maxListener != 0) {
                newGroup.add(person);
                --maxListener;
            }
        }

        return newGroup;
    }

    @Override
    public Staff createGroupFromFile(int maxStudents, int maxListener, String someGroup) {
        return null;
    }

    public int summRating(Staff someCourse) {
        int summ = 0;
        for (Person member: someCourse.getStudlist()) {
            if (member.getClass() == Student.class) {
                summ += ((Student)member).getRating();
            }
        }
        return summ;
    }

    public int summMoneyToPay(Staff someCourse) {
        int summ = 0;
        ArrayList<Person> temp = someCourse.getListeners();
        for (Person member: temp) {
            summ += ((Listener)member).getMoney();
        }
        return summ;
    }

    public Staff sort(Staff someCourse, String params) {
        switch (params) {
            case "Name": {
                Collections.sort(someCourse.getStudlist(), (o1, o2) -> o1.getName().compareTo(o2.getName()));
            } break;
            case "Surname": {
                Collections.sort(someCourse.getStudlist(), (o1, o2) -> o1.getSurname().compareTo(o2.getSurname()));
            } break;
            case "Age": {
                Collections.sort(someCourse.getStudlist(), (o1, o2) -> Integer.compare(o1.getAge(),o2.getAge()));
            } break;
            case "Rating": {
                Collections.sort(someCourse.getStudlist(), (o1, o2) ->
                        Integer.compare(((Student)o1).getRating(),((Student)o2).getRating()));
            } break;
        }
        return someCourse;
    }

    public boolean compareByAge(Person a, Person b) {
        return a.getAge() > b.getAge();
    }

    public Staff topThreeStudents(Staff someCourse) {
        someCourse = sort(someCourse, "rating");
        ArrayList<Person> persons = new ArrayList<>(someCourse.getStudlist().subList(0, 3));
        return  new Staff(persons);
    }
}
