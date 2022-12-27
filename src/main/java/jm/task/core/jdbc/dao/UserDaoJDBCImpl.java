package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS users (
                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(20),
                    lastName VARCHAR(20),
                    age TINYINT)
                    """);

        } catch (SQLException e) {
            System.out.println("Table already exists");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //DROP TABLE;
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = Util.getConnection().prepareStatement("""
                INSERT users(name, lastName, age) VALUES (?, ?, ?)
                """)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();
            System.out.println("User с именем – " +
                    name +
                    " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        try (PreparedStatement ps = Util.getConnection().prepareStatement("""
                DELETE FROM users WHERE id=?
                """)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //DELETE FROM IF EXISTS Users
        //WHERE Id=?;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {

                User user = new User(resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4));

                user.setId(resultSet.getLong(1));

                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(list.toArray()));
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("""
                    TRUNCATE TABLE users
                    """);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //TRUNCATE TABLE Clients
    }
}
