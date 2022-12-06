package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
// service переиспользует методы dao

// Обработка всех исключений, связанных
// с работой с базой данных должна находиться в dao

// Класс Util должен содержать логику настройки
// соединения с базой данных
public class Main {
    public static void main(String[] args) {

        Util util = new Util();
        try (Connection connection = util.getConnection()) {
            UserDaoJDBCImpl userDao = new UserDaoJDBCImpl(connection);
            userDao.createUsersTable();

            userDao.saveUser("Name1", "LastName1", (byte) 20);
            userDao.saveUser("Name2", "LastName2", (byte) 25);
            userDao.saveUser("Name3", "LastName3", (byte) 31);
            userDao.saveUser("Name4", "LastName4", (byte) 38);

            userDao.removeUserById(1);
            userDao.getAllUsers();
            userDao.cleanUsersTable();
            userDao.dropUsersTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // реализуйте алгоритм здесь

    }
}
