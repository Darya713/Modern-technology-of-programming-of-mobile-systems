package by.bstu.solovei.education.units;

import by.bstu.solovei.education.enumName;
import by.bstu.solovei.education.enumSurname;
import by.bstu.solovei.education.organization.enumOrg;

import java.io.Serializable;
import java.util.Random;

/**
 * Класс, для создания слушателей (работников организаций)
 */
public class Listener extends Person implements Serializable {

    private String Organizations;
    private int Money;
    private long BankAccount;
    private int Rating = new Random().nextInt(10);

    public Listener() {
        long Min = 1000000000000000L;
        long Max = 9999999999999999L;
        int idOfName = (int)Math.floor(Math.random() * 10);
        enumName name = enumName.values()[idOfName];
        int idOfSurname = (int)Math.floor(Math.random() * 10);
        enumSurname surname = enumSurname.values()[idOfSurname];
        int idOfOrg = (int)Math.floor(Math.random() * 4);
        enumOrg org = enumOrg.values()[idOfOrg];
        this.Name = name.toString();
        this.Surname = surname.toString();
        this.Organizations = org.toString();
        this.Money = org.getAmountOfMoneyToPay();
        this.id = this.Name.hashCode() + this.Surname.hashCode();
        this.BankAccount = Min + (long)(Math.random() * ((Max - Min) + 1));
    }

    /*@Override
    public String toString() {
        return "\n\tLISTENER\n\t{\n\t\t" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", BankAccount=" + BankAccount + "\n\t" +
                '}';
    }*/

    @Override
    public String toString() {
        return "LISTENER{" +
                "Name='" + Name + "'" +
                ", Surname='" + Surname + "'" +
                ", BankAccount=" + BankAccount +
                '}';
    }

    public long getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(long bankAccount) {
        BankAccount = bankAccount;
    }

    @Override
    public int getRating() {
        return Rating;
    }

    @Override
    public void setRating(int rating) {
        Rating = rating;
    }

    public String getOrganizations() {
        return Organizations;
    }

    public void setOrganizations(String organizations) {
        this.Organizations = organizations;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        this.Money = money;
    }
}
