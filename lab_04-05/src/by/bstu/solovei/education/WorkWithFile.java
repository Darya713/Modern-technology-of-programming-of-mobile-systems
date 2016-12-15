package by.bstu.solovei.education;

import by.bstu.solovei.education.staff.Staff;
import com.thoughtworks.xstream.XStream;

import java.io.*;

public final class WorkWithFile {
    public static void WriteToJson(String str) {
        try {
            FileWriter fileWriter = new FileWriter("ToJson.txt");
            fileWriter.write(str
                .replace("\\u003d", "=")
                .replace("\\u0027", "'"));
            fileWriter.flush(); //запись всех данных в поток
            fileWriter.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String ReadFromJson() {
        String str = "";
        File file = new File("ToJson.txt");
        try {
            FileReader fileReader = new FileReader(file);
            /*int i;
            while ((i = fileReader.read()) != -1)
                str += (char)i;
            System.out.println(str);*/

            char[] buffer = new char[(int)file.length()];
            fileReader.read(buffer);
            System.out.println(new String(buffer));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static void WriteToXML(Staff staff) {
        File file = new File("ToXML.txt");
        try {
            XStream xStream = new XStream();
            String xml = xStream.toXML(staff);
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(xml);
            printWriter.close();
        }
        catch (IOException e) {
            e.getMessage();
        }
    }
}
