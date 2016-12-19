import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConnectionPool <T> {
    private final static int POOL_SIZE = 3; // размер пула
    // для управления доступом
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);// порядок соотв.запросам
    private final Queue<T> resources = new LinkedList<T>();

    public ConnectionPool(Queue<T> source) {
        resources.addAll(source);
    }

    public T getResource(long maxWaitMillis) throws Exception {
        try {
            if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
                T res = resources.poll();
                return res;
            }
        }
        catch (InterruptedException e) {
            throw new Exception(e);
        }
        throw new Exception(" ");
    }

    public void returnResource(T res) {
        resources.add(res); // возвращение экземпляра в пул
        semaphore.release();
    }
}

class Connect {
    private int connectID;

    public Connect(int id) {
        super();
        this.connectID = id;
    }

    public int getConnectID() {
        return connectID;
    }

    public void setConnectID(int id) {
        this.connectID = id;
    }

    public void using() {
        try {
            //использование соединений
            Thread.sleep(new Random().nextInt(500));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Client extends Thread {
    boolean flag = false;
    private boolean reading = false;
    private ConnectionPool<Connect> pool;

    public Client(ConnectionPool<Connect> pool) {
        this.pool = pool;
    }

    public void ChangeAction() {
        flag = !flag;
    }

    public void run() {
        Connect connect = null;
        try {
            connect = pool.getResource(500);
            reading = true;
            System.out.println("Соединение с клиентом № " + this.getId() +
                    " ... трубку взял оператор №" + connect.getConnectID());

            connect.using();
        }
        catch (Exception e) {
            System.out.println("Клиент №" + this.getId() + " не дождался и повесил трубку");
            // ChangeAction();
            System.out.println("Попытка дозвониться снова от клиента №" + this.getId());
            this.run();
        }
        finally {
            if (connect != null) {
                reading = false;
                System.out.println("Клиент № " + this.getId() + " повесил трубку");
                pool.returnResource(connect);
            }
        }
    }

    public boolean isReading() {
        return reading;
    }
}
