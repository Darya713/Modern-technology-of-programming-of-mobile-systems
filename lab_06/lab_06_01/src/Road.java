import java.io.FileWriter;
import java.io.IOException;

public class Road {
    private FileWriter Traffic;
    static int counterL = 5;
    static int counterR = 5;

    public Road(String file) throws IOException {
        this.Traffic = new FileWriter(file, true);
    }

    public synchronized void dumping(String str, String z) {
        try {
            this.Traffic.append(str);
            System.out.println(str);
            double e = Math.random() * 50.0D;
            Thread.sleep((long)e);
            System.out.println("проехала машина , которая ожидала " + (long)e + " секунд");
            if(z == "Левоcтороннее движение: ") {
                System.out.println("lНа дороге осталось " + counterL-- + " машин(ы)");
            } else {
                System.out.println("rНа дороге осталось " + counterR-- + " машин(ы)");
            }
        } catch (IOException var5) {
            System.err.println("ошибка файла: ");
        } catch (InterruptedException var6) {
            System.err.println("ошибка потока: ");
        }

    }
}