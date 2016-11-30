package com.service;

import com.user.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Main Server on 24.11.2016.
 */
public class UserService {
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

}
