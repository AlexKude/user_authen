package com.user;

import com.repo.UserValidation;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;



/**
 * Created by Main Server on 24.11.2016.
 */
@RunWith(JUnit4.class)
public class User {
    private String login;
    private String name;
    private String surname;
    private int age;
    private Role role = Role.USER;

    public User() {
    }

    public User(String login, String name, String surname, Date dateOfBirth) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.age = UserValidation.ageCalculation(dateOfBirth);
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", role=" + role +
                '}';
    }
}
