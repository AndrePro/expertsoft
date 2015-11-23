package com.expertSoft.protasenya.connectionPool;


import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class ThreadLocalCounter {
    private int count;
    private Connection connection;
    private Creational creational;
    private String url;
    private String userName;
    private String password;

    public ThreadLocalCounter() {
        try {
            Properties props = new Properties();
            props.load(this.getClass().getClassLoader()
                    .getResourceAsStream("dao.ini"));
            url = props.getProperty("database.url");
            userName = props.getProperty("database.user");
            password = props.getProperty("database.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        creational = new Creational();
    }

    public Connection getConnection() {
        return creational.createConnection();
    }

    public int getCount() {
        return count;
    }

    public int incrementCount() {
        count++;
        return count;
    }

    public int decrementCount() {
        count--;
        return count;
    }
}
