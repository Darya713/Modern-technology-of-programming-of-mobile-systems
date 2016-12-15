import java.io.IOException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Road s = null;

        try {
            try {
                s = new Road("synh.txt");
                SyncThread e = new SyncThread("Левоcтороннее движение: ", s);
                SyncThread m2 = new SyncThread("Правостороннее движение: ", s);
                e.start();
                m2.start();
                e.join();
                m2.join();
            } catch (IOException var8) {
                System.err.println("ошибка файла: ");
            } catch (InterruptedException var9) {
                System.err.println("ошибка потока :");
            }

        } finally {
            ;
        }
    }
}
