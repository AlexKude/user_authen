package com;

import com.repo.ConnectManager;
import com.repo.PostgresConnectorManager;
import com.service.ServiceName;
import com.service.UserRoleAnnotation;
import com.service.UserService;
import com.user.Role;
import com.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Main Server on 25.11.2016.
 */
@Service(value = "userLogIn")
public class UserLogIn {
    @Resource
    @Qualifier(value = "postgresConnectorManager")
    private  ConnectManager connectManager;


    public ConnectManager getConnectManager() {
        return connectManager;
    }

    public void setConnectManager(ConnectManager connectManager) {
        this.connectManager = connectManager;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserLogIn logIn = context.getBean("userLogIn", UserLogIn.class);


        while (true) {
            System.out.println("<=========================>");
            System.out.println("What would You like to do?");
            System.out.println("Type LOGIN for login");
            System.out.println("Type LIST for list of users");
            System.out.println("Type EXIT for exit");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "LOGIN":
                    logIn.login(scanner);
                    break;
                case "LIST":
                    List<User> allUsers = logIn.connectManager.findAllUsers();
                    if (allUsers.isEmpty()) {
                        System.out.println("No active users found");
                    } else {
                        for (User user : allUsers) {
                            System.out.println("User: " + user.getSurname() + " " + user.getName() + " " + user.getRole().toString());
                        }
                    }
                    break;
                case "EXIT":
                    System.exit(0);
            }
        }
    }


    public void login(Scanner scanner) {

        UserService userService = new UserService();
        User user = null;
        String choice = "";

        while (true) {
            System.out.println("Do Yor like create user or find existing?");
            System.out.println("Please type CREATE or FIND");
            choice = scanner.nextLine().trim();

            if (choice.equals("CREATE")) {
                user = connectManager.createUser(scanner);
                if (user == null) {
                    continue;
                }
                break;
            } else if (choice.equals("FIND")) {
                user = connectManager.findUser(scanner);
                if (user == null) {
                    continue;
                }
                break;
            }
        }

        findAvailableServices(user.getRole());
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
                        } catch (Exception ignored) {

                        }
                    }
                }
            }
        }
    }


    public static void findAvailableServices(Role role) {
        UserService userService = new UserService();
        Class<? extends UserService> userClass = userService.getClass();
        List<String> availableServices = new ArrayList<>();
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
