package com.repo;

import com.user.Role;
import com.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Main Server on 26.11.2016.
 */
public class UserRepo {

    public static Map<String, User> users = new HashMap<String, User>();

    public User createUser(Scanner scanner) {
        System.out.println("Enter your login");
        String login = scanner.nextLine().trim();
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

        User user = new User(name, surname, date);
        user = this.updateUser(user, scanner);
        users.put(login, user);
        return user;
    }

    public User findUser(Scanner scanner) {
        User user = null;
        System.out.println("Enter your login");
        String login = scanner.nextLine().trim();

        for (Map.Entry entry : users.entrySet()) {
            if (entry.getKey().equals(login)) {
                user = (User) entry.getValue();
            }
        }
        if (user != null) {
            user = this.updateUser(user, scanner);
            users.put(login, user);
            return user;
        } else return null;
    }

    public User updateUser(User user, Scanner scanner) {
        while (true) {
            System.out.println("Your current Role is " + user.getRole());
            System.out.println("If You like to continue type OK");
            System.out.println("If You like to change your Role type password");
            System.out.println("//SURER USER password super, ADMIN passwors admin//");
            String choice = scanner.nextLine().trim();

            if (choice.equals("admin")) {
                user.setRole(Role.ADMIN);
                return user;
            } else if (choice.equals("super")) {
                user.setRole(Role.SUPER_USER);
                return user;
            } else if (choice.equals("OK")) {
                return user;
            } else {
                continue;
            }
        }
    }
}

