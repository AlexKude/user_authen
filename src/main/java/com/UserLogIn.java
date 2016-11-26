package com;

import com.repo.UserRepo;
import com.service.ServiceName;
import com.service.UserRoleAnnotation;
import com.service.UserService;
import com.user.Role;
import com.user.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Main Server on 25.11.2016.
 */
public class UserLogIn {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("<=========================>");
            System.out.println("What would You like to do?");
            System.out.println("Type LOGIN for login");
            System.out.println("Type LIST for list of users");
            System.out.println("Type EXIT for exit");
            String choice = scanner.nextLine().trim();

            if (choice.equals("LOGIN")) {
                login(scanner);
            } else if (choice.equals("LIST")) {
                Map<String, User> users = UserRepo.users;
                if (!users.isEmpty()) {
                    for (Map.Entry entry : users.entrySet()) {
                        User value = (User) entry.getValue();
                        System.out.println("User: " + value.getSurname() + " " + value.getName() + " " + value.getRole());
                    }
                } else {
                    System.out.println("There is no active users");
                }
            } else if (choice.equals("EXIT")) {
                System.exit(0);
            } else {
                continue;
            }
        }
    }

    public static void login(Scanner scanner) {

        UserService userService = new UserService();
        UserRepo repo = new UserRepo();
        User user = null;
        String choice = "";

        while (true) {
            System.out.println("Do Yor like create user or find existing?");
            System.out.println("Please type CREATE or FIND");
            choice = scanner.nextLine().trim();
            if (choice.equals("CREATE")) {
                user = repo.createUser(scanner);
                break;
            } else if (choice.equals("FIND")) {
                user = repo.findUser(scanner);
                if (user == null) {
                    System.out.println("User not found");
                    continue;
                } else {
                    break;
                }
            } else {
                continue;
            }
        }

        findAvailableServeces(user.getRole());
        System.out.println("Type LOGOUT for logout");

        Class<? extends UserService> userClass = userService.getClass();
        Method[] methods = userClass.getDeclaredMethods();
        ServiceName serviceAnno;

        while (true) {
            System.out.println("Plese make your choice: ");
            choice = scanner.nextLine();
            if (choice.equals("LOGOUT")) {
                return;
            }
            for (Method method : methods) {
                serviceAnno = method.getAnnotation(ServiceName.class);
                if (serviceAnno != null) {
                    if (serviceAnno.name().equals(choice)) {
                        try {
                            method.invoke(userService);
                        } catch (IllegalAccessException e) {

                        } catch (InvocationTargetException e) {

                        }
                    }
                }
            }
        }
    }

    public static void findAvailableServeces(Role role) {
        UserService userService = new UserService();
        Class<? extends UserService> userClass = userService.getClass();
        List<String> availableServices = new ArrayList<String>();
        Method[] methods = userClass.getDeclaredMethods();
        UserRoleAnnotation roleAnno;
        ServiceName serviceAnno;

        for (Method method : methods) {
            roleAnno = method.getAnnotation(UserRoleAnnotation.class);
            serviceAnno = method.getAnnotation(ServiceName.class);
            if (roleAnno != null) {
                Role[] value = roleAnno.value();
                for (Role rl : value) {
                    if (rl.equals(role)) {
                        availableServices.add(serviceAnno.name());
                        continue;
                    }
                }
            }
        }

        System.out.println("Available services for your Role: ");
        for (String str : availableServices) {
            System.out.println(str);
        }

    }
}
