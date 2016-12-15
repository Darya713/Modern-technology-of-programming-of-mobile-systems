package by.bstu.solovei.education.main;

import by.bstu.solovei.education.WorkWithFile;
import by.bstu.solovei.education.educationmanager.Manager;
import by.bstu.solovei.education.staff.Staff;
import by.bstu.solovei.education.units.Listener;
import by.bstu.solovei.education.units.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        Listener Andrey = new Listener();
        Student st = new Student();
        System.out.println(Andrey);
        System.out.println(st);

        Manager manager = new Manager();
        Staff staff = new Staff();
        for (int i = 0; i < (int)(Math.random() * 10); i++) {
            staff.add(new Student());
        }
        for (int i = 0; i < (int)(Math.random() * 10); i++) {
            staff.add(new Listener());
        }
        staff = manager.createGroup(staff, 5, 5);
        LOG.info(staff.toString()); //.replace("[", "\r").replace("]", "")

        Gson gson = new Gson();
        String staffToJson = gson.toJson(staff.toString());

        WorkWithFile.WriteToJson(staffToJson);

        WorkWithFile.ReadFromJson();

        WorkWithFile.WriteToXML(staff);

        LOG.info(Integer.toString(manager.summMoneyToPay(staff)));
        if (staff.getStudlist().size() > 2)
            LOG.info(manager.topThreeStudents(staff).toString());
    }
}
