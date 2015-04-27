import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionsPool {

    private LinkedList<Connection> usedObjects = new LinkedList<>();
    private LinkedList<Connection> unusedObjects = new LinkedList<>();
    private int usedConnections = 0;
    private int maxConnections;
    private Creational creational;
    private Connection c;
    private Object lockObject = new Object();
    private static ConnectionsPool cp;

    private ConnectionsPool(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public static ConnectionsPool getInstance(int maxConnections) {
        if (cp == null) {
            return new ConnectionsPool(maxConnections);
        }
        return cp;
    }

    public Connection getConnection(String url, String userName, String password) {
        synchronized (lockObject) {
            creational = new Creational(url, userName, password);
            if (usedConnections < maxConnections && unusedObjects.size() == 0) {
                System.out.println("Connect " + Thread.currentThread().getName());
                c = creational.create();
                usedObjects.add(c);
                usedConnections++;
                return c;
            }
            if (usedConnections < maxConnections && unusedObjects.size() != 0) {
                System.out.println("Connect " + Thread.currentThread().getName());
                c = unusedObjects.getFirst();
                unusedObjects.removeFirst();
                usedObjects.addFirst(c);
                usedConnections++;
                return c;
            }
            if (usedConnections >= maxConnections) {
                try {
                    System.out.println("Wait " + Thread.currentThread().getName());
                    lockObject.wait();
                    System.out.println("Connect after wait " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public void releaseConnection(Connection c) {
        synchronized (lockObject) {
            System.out.println("Release connect " + Thread.currentThread().getName());
            usedObjects.remove(c);
            unusedObjects.addFirst(c);
            usedConnections--;
            lockObject.notify();
        }
    }
}
