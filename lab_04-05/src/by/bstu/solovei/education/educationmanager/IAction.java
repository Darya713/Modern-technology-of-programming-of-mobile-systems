package by.bstu.solovei.education.educationmanager;

import by.bstu.solovei.education.staff.Staff;

public interface IAction {
    Staff createGroup(Staff var1, int var2, int var3);

    Staff createGroupFromFile(int var1, int var2, String var3);
}
