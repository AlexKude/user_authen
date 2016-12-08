package com.repo;

import java.util.Date;

/**
 * Created by Main Server on 05.12.2016.
 */
public class UserValidation {

    public static boolean loginCheck(String login){
        boolean flag = true;
        char[] chars = login.toCharArray();
        if (chars.length < 5 ) flag = false;
        if (chars.length > 10 ) flag = false;
        for (char aChar : chars) {
            if(!Character.isLetter(aChar)) {
                flag = false;
                break;
            }

        }
        return flag;
    }

    public static boolean nameCheck(String name) {
        boolean flag = true;
        char[] chars = name.toCharArray();
        for (char aChar : chars) {
            if(!Character.isLetter(aChar)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static int ageCalculation(Date dateOfBirth){
        Date date = new Date();
        int age = date.getYear() - dateOfBirth.getYear();
        return age;
    }




}
