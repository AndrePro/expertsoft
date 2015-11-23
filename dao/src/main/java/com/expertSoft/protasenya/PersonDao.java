package com.expertSoft.protasenya;

import com.expertSoft.protasenya.connectionPool.ConnectionsPool;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDao {

    private static final String TABLE = "person";

    private static final String INSERT = "insert into " + TABLE + " " +
            " (first_name,last_name,login,email,phone_number)" +
            " values (?,?,?,?,?) " +
            "ON DUPLICATE KEY UPDATE first_name=VALUES(first_name),last_name=VALUES(last_name)," +
            "email=VALUES(email),phone_number=VALUES(phone_number)";

    private static final String SELECT_ALL = "select SQL_CALC_FOUND_ROWS * FROM " + TABLE + " limit ?,?";
    private Connection connection;
    private ConnectionsPool cp = ConnectionsPool.getInstance();
    private int noOfRecords;


    public PersonDao() {

    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void add(Person obj) {

        try {
            connection = cp.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, obj.getFirstName());
            preparedStatement.setString(2, obj.getLastName());
            preparedStatement.setString(3, obj.getLogin());
            preparedStatement.setString(4, obj.getEmail());
            preparedStatement.setString(5, obj.getPhoneNumber());
            preparedStatement.executeUpdate();
            cp.releaseConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAll(int offset, int noOfRecords) {
        try {
            connection = cp.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            List<Person> person = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                person.add(createEmployeeFromResultSet(resultSet));
            }
            resultSet.close();
            resultSet = connection.createStatement().executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next())
                this.noOfRecords = resultSet.getInt(1);
            cp.releaseConnection(connection);
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Person createEmployeeFromResultSet(ResultSet resultSet) {
        Person e = new Person();
        try {
            e.setId(resultSet.getInt("id_person"));
            e.setFirstName(resultSet.getString("first_name"));
            e.setLastName(resultSet.getString("last_name"));
            e.setLogin(resultSet.getString("login"));
            e.setEmail(resultSet.getString("email"));
            e.setPhoneNumber(resultSet.getString("phone_number"));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return e;
    }
}
