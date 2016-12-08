package com.service;

import java.util.List;

/**
 * Created by Main Server on 05.12.2016.
 */
public class UserStatistics {

    public static int teenagerCheck(List<Integer> age) {
        int count = 0;
        for (Integer in : age) {
            if (in < 20) {
                count++;
            }
        }
        return count;
    }

    public static int youthCheck(List<Integer> age) {
        int count = 0;
        for (Integer in : age) {
            if (in > 20 && in < 30) {
                count++;
            }
        }
        return count;
    }

    public static int middleageCheck(List<Integer> age) {
        int count = 0;
        for (Integer in : age) {
            if (in > 30 && in < 50) {
                count++;
            }
        }
        return count;
    }

    public static int seniorsCheck(List<Integer> age) {
        int count = 0;
        for (Integer in : age) {
            if (in > 50) {
                count++;
            }
        }
        return count;
    }

    public static int averageAge(List<Integer> age) {
        int count = 0;
        int avg = 0;
        for (Integer in : age) {
                ++count;
                avg += in;
        }
        return avg / count;
    }
}