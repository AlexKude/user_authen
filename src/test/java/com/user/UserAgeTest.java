package com.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.Date;


/**
 * Created by Main Server on 01.12.2016.
 */
@RunWith(JUnit4.class)
public class UserAgeTest {

    @Test
    public void ageCalculationTest(){
        User user = new User();
        Assert.assertEquals(40,user.ageCalculation(new Date(76,10,10)));
        Assert.assertEquals(56,user.ageCalculation(new Date(60,10,10)));
        Assert.assertEquals(86,user.ageCalculation(new Date(30,03,01)));
        Assert.assertEquals(86,user.ageCalculation(new Date(30,01,01)));
        Assert.assertEquals(46,user.ageCalculation(new Date(70,05,05)));
        Assert.assertEquals(36,user.ageCalculation(new Date(80,06,24)));
    }
}
