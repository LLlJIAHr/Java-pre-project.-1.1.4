package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String USER_NAME = "root";
    private static final String PASS = "";
    private static final String URL = "jdbc:mysql://localhost:3306/sys";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASS);
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
