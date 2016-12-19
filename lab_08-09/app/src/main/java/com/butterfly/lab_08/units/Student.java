package com.butterfly.lab_08.units;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student implements Serializable {

    private String Name;
    private String Surname;
    private String MiddleName;
    private int Age;
    private String Birthday;
    private double Rating;
    private String Course;

    public Student(String name, String surname, String middleName, String birthday, double rating, String course) {
        this.Name = name;
        this.Surname = surname;
        this.MiddleName = middleName;
        this.Birthday = birthday;
        this.Rating = rating;
        this.Course = course;

        this.Age = calculateAge(this.Birthday);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public int calculateAge(String birthday) {
        Date date = new Date();
        int age;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try
        {
            date = format.parse(birthday);
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        if (date.getMonth() < new Date().getMonth()) {
            age = new Date().getYear() - date.getYear();
        }
        else if (date.getMonth() == new Date().getMonth() && date.getDate() < new Date().getDate()) {
            age = (new Date().getYear() - date.getYear());
        }
        else if (date.getDate() == new Date().getDate()) {
            age = (new Date().getYear() - date.getYear());
        }
        else {
            age = (new Date().getYear() - date.getYear()) - 1;
        }

        return age;
    }

    @Override
    public String toString() {
        return "STUDENT{" +
                "Name='" + Name + "'" +
                ", Surname='" + Surname + "'" +
                ", MiddleName='" + MiddleName + "'" +
                ", Birthday=" + Birthday + "'" +
                ", Age=" + Age + "'" +
                ", Rating=" + Rating +
                ", Course=" + Course +
                '}';
    }
}
