package com.repo;

import com.user.Role;
import com.user.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Main Server on 26.11.2016.
 */
@Repository(value = "postgresConnectorManager")
public class PostgresConnectorManager implements ConnectManager {

    private String driver;
    private String url;
    private String user;
    private String password;

    public PostgresConnectorManager() {
        init();
    }

    private void init() {
        try {
            this.driver = "org.postgresql.Driver";
            this.url = "jdbc:postgresql://localhost:5432/UserDB";
            this.user = "postgres";
            this.password = "postgres";
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found!");
        }
    }


    @Override
    public User createUser(Scanner scanner) {
        System.out.println("Enter your login");
        String login = scanner.nextLine().trim();

        if (!checkLogin(login)) {
            System.out.println("This login is already in use. Please try other one");
            return null;
        }

        System.out.println("Enter your Family name");
        String surname = scanner.nextLine().trim();
        System.out.println("Enter your First name");
        String name = scanner.nextLine().trim();

        Date date = null;
        while (date == null) {
            System.out.println("Enter your Date of Birth - dd.MM.yyyy");
            String sd = scanner.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                date = format.parse(sd);
            } catch (Exception e) {
                System.out.println("Wrong date format");
                date = null;
            }
        }

        User user = new User(login, name, surname, date);
        user = this.updateUser(user, scanner);
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();
            String insertUser = "INSERT INTO userdb.usertable (login, surname, name, age, role) VALUES ('" + user.getLogin() + "', '" + user.getSurname() +
                    "', '" + user.getName() + "', " + user.getAge() + ", '" + user.getRole().toString() + "')";
            statement.execute(insertUser);
            connection.close();

        } catch (SQLException e) {
            System.out.println("failed to connect Data Base.");
            return null;
        }
        return user;
    }


    @Override
    public User findUser(Scanner scanner) {
        Role role = null;
        User user = null;
        System.out.println("Enter your login");
        String login = scanner.nextLine().trim();

        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM userdb.usertable WHERE login = '" + login + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                switch (resultSet.getString("role")) {
                    case "USER":
                        role = Role.USER;
                        break;
                    case "SUPER_USER":
                        role = Role.SUPER_USER;
                        break;
                    case "ADMIN":
                        role = Role.ADMIN;
                        break;
                }
                user.setRole(role);
            }
            if (user == null) {
                return null;
            }
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
             return null;
        }
        user = this.updateUser(user, scanner);
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();
            String insertUser = "UPDATE userdb.usertable SET role = '" + user.getRole().toString() + "' WHERE login = '" + user.getLogin() + "'";
            statement.execute(insertUser);
            connection.close();

        } catch (SQLException e) {
            System.out.println("Failed to connect Data Base");
            return null;
        }
        return user;
    }


    @Override
    public User updateUser(User user, Scanner scanner) {
        while (true) {
            System.out.println("Your current Role is " + user.getRole());
            System.out.println("If You like to continue type OK");
            System.out.println("If You like to change your Role type password");
            System.out.println("//SURER USER password super, ADMIN passwors admin//");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "admin":
                    user.setRole(Role.ADMIN);
                    return user;
                case "super":
                    user.setRole(Role.SUPER_USER);
                    return user;
                case "OK":
                    return user;
            }
        }
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        User user = null;
        Role role = null;
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM userdb.usertable";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                switch (resultSet.getString("role")) {
                    case "USER":
                        role = Role.USER;
                        break;
                    case "SUPER_USER":
                        role = Role.SUPER_USER;
                        break;
                    case "ADMIN":
                        role = Role.ADMIN;
                        break;
                }
                user.setRole(role);
                users.add(user);
            }
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    private boolean checkLogin(String login) {
        try {
            Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            Statement statement = connection.createStatement();
            String query = "SELECT login FROM userdb.usertable";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (login.equals(resultSet.getString("login"))) {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}


