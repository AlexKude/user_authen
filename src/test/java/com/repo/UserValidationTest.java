package com.repo;

import com.runner.FormattedRunner;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import static com.repo.UserValidation.*;

/**
 * Created by Main Server on 05.12.2016.
 */
@RunWith(FormattedRunner.class)
public class UserValidationTest {

    @Test
    public void loginCheckTest() {
        Assert.assertTrue(loginCheck("Memphis"));
        Assert.assertTrue(loginCheck("Miami"));
        Assert.assertTrue(loginCheck("Boston"));
        Assert.assertTrue(loginCheck("Alabama"));
        Assert.assertTrue(loginCheck("Rotchester"));

        Assert.assertFalse(loginCheck("24january"));
        Assert.assertFalse(loginCheck("Fox Trot"));
        Assert.assertFalse(loginCheck("Fox"));
        Assert.assertFalse(loginCheck("Abcdefgijklmnopqrstuvwxyz"));
        Assert.assertFalse(loginCheck("D   t  r"));
    }

    @Test
    public void nameCheckTest(){
        Assert.assertTrue(nameCheck("Alexander"));
        Assert.assertTrue(nameCheck("Robert"));
        Assert.assertTrue(nameCheck("John"));
        Assert.assertTrue(nameCheck("Alan"));
        Assert.assertTrue(nameCheck("Donald"));

        Assert.assertFalse(nameCheck("Stew24"));
        Assert.assertFalse(nameCheck("Artur_Dask"));
        Assert.assertFalse(nameCheck("Alex.niro"));
        Assert.assertFalse(nameCheck("Vlad Dracula"));
        Assert.assertFalse(nameCheck("Mike mike"));
    }

    @Test
    public void ageCalculationTest() {
        Assert.assertEquals(40, ageCalculation(new Date(76, 10, 10)));
        Assert.assertEquals(56, ageCalculation(new Date(60, 10, 10)));
        Assert.assertEquals(86, ageCalculation(new Date(30, 03, 01)));
        Assert.assertEquals(86, ageCalculation(new Date(30, 01, 01)));
        Assert.assertEquals(46, ageCalculation(new Date(70, 05, 05)));
        Assert.assertEquals(36, ageCalculation(new Date(80, 06, 24)));
        Assert.assertEquals(26, ageCalculation(new Date(90, 04, 12)));
        Assert.assertEquals(27, ageCalculation(new Date(88, 12, 10)));
        Assert.assertEquals(20, ageCalculation(new Date(96, 10, 11)));
        Assert.assertEquals(18, ageCalculation(new Date(98, 11, 11)));
    }
}
