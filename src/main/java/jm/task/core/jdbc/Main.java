package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
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


        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);




        userDao.removeUserById(2);
        userDao.getAllUsers();
        System.out.println(userDao.getAllUsers().get(0));
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        try {
            //Util.closeConnection();
            Util.closeFactory();
            System.out.println("Connection closed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // реализуйте алгоритм здесь

    }
}
