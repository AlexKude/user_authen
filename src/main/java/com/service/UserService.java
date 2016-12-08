package com.service;

import com.UserLogIn;
import com.repo.ConnectManager;
import com.user.Role;
import com.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Main Server on 24.11.2016.
 */
@Service(value = "userService")
public class UserService {
    @Resource
    @Qualifier(value = "postgresConnectorManager")
    private ConnectManager connectManager;

    public ConnectManager getConnectManager() {
        return connectManager;
    }

    public void setConnectManager(ConnectManager connectManager) {
        this.connectManager = connectManager;
    }

    private Runtime run = Runtime.getRuntime();
    private String skype_home;
    private String internet_home;

    public UserService() {
        init();
    }

    private void init() {
        try {
            FileInputStream fis;
            fis = new FileInputStream("src/main/resources/config.properties");
            Properties properties = new Properties();
            properties.load(fis);
            skype_home = properties.getProperty("skype_home");
            internet_home = properties.getProperty("internet_home");
            fis.close();
        } catch (IOException e) {
            System.out.println("Init error........ ");
            System.out.println("Pleas try again");
            System.exit(0);
        }

    }

    @UserRoleAnnotation({Role.USER, Role.SUPER_USER, Role.ADMIN})
    @ServiceName(name = "NOTEPAD")
    public void startNotePad() {
        try {
            run.exec("Notepad");
        } catch (IOException e) {
            System.out.println("Notepad failed to start");
        }
    }

    @UserRoleAnnotation({Role.USER, Role.SUPER_USER, Role.ADMIN})
    @ServiceName(name = "EXPLORER")
    public void startExplorer() {
        try {
            run.exec("explorer");
        } catch (IOException e) {
            System.out.println("Explorer failed to start");
        }
    }

    @UserRoleAnnotation({Role.ADMIN})
    @ServiceName(name = "CONTROL PANEL")
    public void startControlPanel() {
        try {
            run.exec("control");
        } catch (IOException e) {
            System.out.println("Control Panel failed to start");
        }
    }

    @UserRoleAnnotation({Role.ADMIN})
    @ServiceName(name = "REGISTER EDITOR")
    public void startRegisterEditor() {
        try {
            run.exec("regedit");
        } catch (IOException e) {
            System.out.println("Register Editor failed to start");
        }
    }

    @UserRoleAnnotation({Role.ADMIN})
    @ServiceName(name = "PERFORMANCE MONITOR")
    public void startPerformanceMonitor() {
        try {
            run.exec("perfmon");
        } catch (IOException e) {
            System.out.println("Performance Monitor failed to start");
        }

    }

    @UserRoleAnnotation({Role.ADMIN})
    @ServiceName(name = "SYSTEM INFO")
    public void startSystemInfoMonitor() {
        try {
            run.exec("msinfo32");
        } catch (IOException e) {
            System.out.println("System Information Monitor failed to start");
        }
    }

    @ServiceName(name = "SKYPE")
    @UserRoleAnnotation({Role.SUPER_USER, Role.ADMIN})
    public void skypeAccess() {
        UserService userService = new UserService();

        try {
            run.exec(userService.skype_home);
        } catch (IOException e) {
            System.out.println("Skype failed to start");
        }
    }


    @UserRoleAnnotation({Role.SUPER_USER, Role.ADMIN})
    @ServiceName(name = "INTERNET")
    public void internetAccess() {
        UserService userService = new UserService();

        try {
            run.exec(userService.internet_home + " http://odessa.itschool-hillel.org");
        } catch (IOException e) {
            System.out.println("Internet failed to start");
        }
    }

    @UserRoleAnnotation({Role.ADMIN})
    @ServiceName(name = "USER STATISTICS")
    public void userStatistics() {
        List<Integer> age = new ArrayList<>();
        int userCount = 0;
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserService userService = context.getBean("userService", UserService.class);
        List<User> allUsers = userService.connectManager.findAllUsers();
        if (allUsers.isEmpty()) {
            System.out.println("Something went wrong, please try again");
            return;
        }
        for (User user : allUsers) {
            ++userCount;
            age.add(user.getAge());
        }
        int teenager = UserStatistics.teenagerCheck(age);
        int youth = UserStatistics.youthCheck(age);
        int middle = UserStatistics.middleageCheck(age);
        int senior = UserStatistics.seniorsCheck(age);
        int average = UserStatistics.averageAge(age);

        System.out.println("USER STATISTICS");
        System.out.println("TOTAL NUMBER OF USERS: " + userCount);
        if (teenager != 0) System.out.println("NUMBER OF TEENAGERS: " + teenager);
        if (youth != 0) System.out.println("NUMBER OF YOUNG PERSONS: " + youth);
        if (middle != 0) System.out.println("NUMBER OF MIDDLE AGE: " + middle);
        if (senior != 0) System.out.println("NUMBER OF SENIORS: " + senior);
        System.out.println("AVERAGE AGE OF USERS: " + average);

        context.close();
    }
}



