public class SyncThread extends Thread {
    private Road logist;
    String z;

    public SyncThread(String name, Road rs) {
        super(name);
        this.logist = rs;
        this.z = name;
    }

    public void run() {
        for(int i = 0; i < 6; ++i) {
            this.logist.dumping(this.getName(), this.z);
        }

    }
}
