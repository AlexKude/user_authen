package com.repo;

import com.user.User;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Main Server on 01.12.2016.
 */
public interface ConnectManager {
    User createUser(Scanner scanner);

    User findUser(Scanner scanner);

    User updateUser(User user, Scanner scanner);

    List<User> findAllUsers();
}
