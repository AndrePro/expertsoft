package com.expertSoft.protasenya.connectionPool;

import com.expertSoft.protasenya.connectionPool.Creational;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionsPool {

    private int maxConnections = 1;
    private LinkedList<Connection> usedObjects = new LinkedList<>();
    private LinkedList<Connection> unusedObjects = new LinkedList<>();
    private int usedConnections = 0;
    private Creational creational;
    private Connection c;
    private Object lockObject = new Object();
    private static ConnectionsPool cp;

    private ConnectionsPool() {
    }

    public static ConnectionsPool getInstance() {
        if (cp == null) {
            System.out.println("cp new " + cp);
            return new ConnectionsPool();
        }
        System.out.println("old " + cp);
        return cp;
    }

    public Connection getConnection() {
        synchronized (lockObject) {
            creational = new Creational();
            if (usedConnections < maxConnections && unusedObjects.size() == 0) {
                System.out.println("Connect " + Thread.currentThread().getName());
                c = creational.createConnection();
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
//package com.expertSoft.protasenya.connectionPool;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.Properties;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//
//public class ConnectionsPool {
//
//    private int maxConnections=1;
//    private BlockingQueue<Connection> usedObjects = new ArrayBlockingQueue<>(maxConnections);
//    private BlockingQueue<Connection> unusedObjects = new ArrayBlockingQueue<>(maxConnections);
//    private static final ThreadLocal<ThreadLocalCounter> threadLocal =new ThreadLocal<>();
//    private int createdConnections = 0;
//    private Creational creational;
//    private Connection connection;
//    private Object lock = new Object();
//    private static ConnectionsPool cp=new ConnectionsPool();
//    private String url;
//    private String userName;
//    private String password;
//
//    private ConnectionsPool() {
//
//    }
//
//    public static ConnectionsPool getInstance() {
//        if (cp == null) {
//            return new ConnectionsPool();
//        }
//        return cp;
//    }
//
//    public Connection getConnection() {
//        if (usedObjects.size()<maxConnections && unusedObjects.size() == 0) {
//            if (threadLocal.get() != null) {
//                connection =  threadLocal.get().getConnection();
//                threadLocal.get().incrementCount();
//                System.out.println(Thread.currentThread().getName() + " get used threadLocal variable1 " + threadLocal+" "+ threadLocal.get().getCount());
//            } else {
//                System.out.println("Connect " + Thread.currentThread().getName() + " variable 0");
//                threadLocal.set(new ThreadLocalCounter());
//                connection=threadLocal.get().getConnection();
//                threadLocal.get().incrementCount();
//                System.out.println(Thread.currentThread().getName() + " set new threadLocal variable " + threadLocal+" "+ threadLocal.get().getCount());
//                try {
//                    usedObjects.put(connection);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock){
//                    createdConnections++;
//                }
//            }
//            return connection;
//        }
//
//        if (usedObjects.size()<maxConnections && unusedObjects.size() != 0) {
//            System.out.println("Connect " + Thread.currentThread().getName());
//            if (threadLocal.get() != null) {
//                connection = threadLocal.get().getConnection();
//                threadLocal.get().incrementCount();
//                System.out.println(Thread.currentThread().getName() + " get used threadLocal variable2 "+threadLocal+" "+ threadLocal.get().getCount());
//            } else {
//                System.out.println(Thread.currentThread().getName() + " get not threadLocal variable");
//                connection = unusedObjects.peek();
//                unusedObjects.peek();
//                try {
//                    usedObjects.put(connection);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock) {
//                    createdConnections++;
//                }
//            }
//            return connection;
//        }
//        return null;
//    }
//
//    public void releaseConnection(Connection c) {
//        threadLocal.get().decrementCount();
//            if(threadLocal.get().getCount()==0){
//                System.out.println("Release connect " + Thread.currentThread().getName()+" "+ threadLocal.get().getCount());
//                try {
//                    System.out.println(usedObjects.size());
//                    usedObjects.take();
//                    System.out.println(usedObjects.size());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    System.out.println(unusedObjects.size());
//                    unusedObjects.put(c);
//                    System.out.println(unusedObjects.size());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronized (lock) {
//                    System.out.println(createdConnections);
//                    createdConnections--;
//                    System.out.println(createdConnections);
//                }
//                System.out.println("fucking");
//            }
//            else{
//
//                System.out.println(Thread.currentThread().getName() + " release threadLocal variable "+ threadLocal.get().getCount());
//            }
//    }
//}
