package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String USER_NAME = "root";
    private static final String PASS = "";
    private static final String URL = "jdbc:mysql://localhost:3306/sys";

    private static Connection connection;

    public  static Connection getConnection() throws SQLException {
        if (connection ==  null) return  connection = DriverManager.getConnection(URL, USER_NAME, PASS);
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

}
