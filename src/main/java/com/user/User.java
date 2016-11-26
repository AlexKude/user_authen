package com.user;

import java.util.Date;



/**
 * Created by Main Server on 24.11.2016.
 */
public class User {
    private String name;
    private String surname;
    private int age;
    private Role role = Role.USER;

    public User(String name, String surname, Date dateOfBirth) {
        this.name = name;
        this.surname = surname;
        Date date = new Date();
        this.age = date.getYear() - dateOfBirth.getYear();
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

    public int age() {
        return age;
    }

    public void age(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }
}
