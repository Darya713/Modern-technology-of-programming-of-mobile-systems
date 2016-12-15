package by.bstu.solovei.education.units;

/**
 * Базовый класс
 */
public abstract class Person {
    protected String Name;
    protected String Surname;
    protected int Age;
    protected int id;
    protected int Rating;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }
}
