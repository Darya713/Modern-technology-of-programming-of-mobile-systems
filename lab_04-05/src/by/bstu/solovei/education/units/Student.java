package by.bstu.solovei.education.units;

import by.bstu.solovei.education.enumName;
import by.bstu.solovei.education.enumSurname;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Класс, для создания студентов
 */
public class Student extends Person implements Serializable {

    private String Birthday;
    private String Day, Month, Year;
    private int Rating = new Random().nextInt(10);

    public Student() {
        int idOfName = (int)Math.floor(Math.random() * 9 + 1);
        enumName name = enumName.values()[idOfName];
        int idOfSurname = (int)Math.floor(Math.random() * 9 + 1);
        enumSurname surname = enumSurname.values()[idOfSurname];
        this.Name = name.toString();
        this.Surname = surname.toString();
        this.id = this.Name.hashCode() + this.Surname.hashCode();
        date(0, 0, 0);
        this.Age = calculateAge(this.Birthday);
    }

    /*@Override
    public String toString() {
        return "\n\tSTUDENT\n\t{\n\t\t" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Birthday=" + Birthday + "\n\t" +
                '}';
    }*/

    @Override
    public String toString() {
        return "STUDENT{" +
                "Name='" + Name + "'" +
                ", Surname='" + Surname + "'" +
                ", Birthday=" + Birthday + "'" +
                ", Age=" + Age + "'" +
                ", Rating=" + Rating +
                '}';
    }

    private String date(int day, int month, int year) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (month == 0)
            month = (int)(Math.floor(Math.random() * 11 + 1));
        int numberOfDay = getNumberOfDay(month);
        if (day == 0)
            day = (int)(Math.floor(Math.random() * numberOfDay + 1));
        if (day > numberOfDay)
            day = numberOfDay;
        if (year == 0)
            year = (int)(1990 + (Math.random() * (1998 - 1990) + 1));
        //parse to Date
        try {
            Date date = format.parse(year + "-" + month + "-" + day);
            format.applyPattern("dd.MM.yyyy");
            Birthday = format.format(date);
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        /*try
        {
            Birthday = LocalDate.parse(date, format);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }*/
        return Birthday;
    }

    private int getNumberOfDay(int month) {
        int numberOfDay = 0;
        switch (month) {
            case 1: numberOfDay = 31; break;
            case 2: numberOfDay = 28; break;
            case 3: numberOfDay = 31; break;
            case 4: numberOfDay = 30; break;
            case 5: numberOfDay = 31; break;
            case 6: numberOfDay = 30; break;
            case 7: numberOfDay = 31; break;
            case 8: numberOfDay = 31; break;
            case 9: numberOfDay = 30; break;
            case 10: numberOfDay = 31; break;
            case 11: numberOfDay = 30; break;
            case 12: numberOfDay = 31; break;
        }
        return numberOfDay;
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
}
