package com.expertSoft.protasenya.connectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Creational {
    private String url;
    private String userName;
    private String password;
    private Connection connection;

    public Creational() {
        try {
            Properties props = new Properties();
            props.load(this.getClass().getClassLoader()
                    .getResourceAsStream("dao.ini"));
            this.url = props.getProperty("database.url");
            this.userName = props.getProperty("database.user");
            this.password = props.getProperty("database.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection createConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}