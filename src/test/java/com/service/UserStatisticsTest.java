package com.service;

import com.runner.FormattedRunner;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.service.UserStatistics.*;


/**
 * Created by Main Server on 05.12.2016.
 */
@RunWith(FormattedRunner.class)
public class UserStatisticsTest {
    List<Integer> list1 = Arrays.asList(11,15,22,26,33,35,44,55,66,77);
    List<Integer> list2 = Arrays.asList(8,12,16,28,31,32,33,50,60,70);
    List<Integer> list3 = Arrays.asList(10,11,25,28,29,29,34,35,46,47);
    List<Integer> list4 = Arrays.asList(22,25,26,27,28,29,34,35,36,37);
    List<Integer> list5 = Arrays.asList(15,35,42,46,53,55,64,65,66,67);
    List<Integer> list6 = Arrays.asList(10,15,22,26,31,35,44,55,66,67);
    List<Integer> list7 = Arrays.asList(10,14,21,25,28,35,44,45,46,47);
    List<Integer> list8 = Arrays.asList(11,18,22,28,33,34,45,51,56,57);
    List<Integer> list9 = Arrays.asList(7,8,12,16,23,25,34,35,36,37);
    List<Integer> list10 = Arrays.asList(11,15,22,26,38,39,41,45,46,47);

    @Test
    public void teenagerCheckTest (){
        Assert.assertEquals(2,teenagerCheck(list1));
        Assert.assertEquals(3,teenagerCheck(list2));
        Assert.assertEquals(2,teenagerCheck(list3));
        Assert.assertEquals(0,teenagerCheck(list4));
        Assert.assertEquals(1,teenagerCheck(list5));
        Assert.assertEquals(2,teenagerCheck(list6));
        Assert.assertEquals(2,teenagerCheck(list7));
        Assert.assertEquals(2,teenagerCheck(list8));
        Assert.assertEquals(4,teenagerCheck(list9));
        Assert.assertEquals(2,teenagerCheck(list10));
    }

    @Test
    public void youthCheckTest() {
        Assert.assertEquals(2,youthCheck(list1));
        Assert.assertEquals(1,youthCheck(list2));
        Assert.assertEquals(4,youthCheck(list3));
        Assert.assertEquals(6,youthCheck(list4));
        Assert.assertEquals(0,youthCheck(list5));
        Assert.assertEquals(2,youthCheck(list6));
        Assert.assertEquals(3,youthCheck(list7));
        Assert.assertEquals(2,youthCheck(list8));
        Assert.assertEquals(2,youthCheck(list9));
        Assert.assertEquals(2,youthCheck(list10));
    }

    @Test
    public void middleageCheckTest() {
        Assert.assertEquals(3,middleageCheck(list1));
        Assert.assertEquals(3,middleageCheck(list2));
        Assert.assertEquals(4,middleageCheck(list3));
        Assert.assertEquals(4,middleageCheck(list4));
        Assert.assertEquals(3,middleageCheck(list5));
        Assert.assertEquals(3,middleageCheck(list6));
        Assert.assertEquals(5,middleageCheck(list7));
        Assert.assertEquals(3,middleageCheck(list8));
        Assert.assertEquals(4,middleageCheck(list9));
        Assert.assertEquals(6,middleageCheck(list10));
    }

    @Test
    public void seniorsCheckTest() {
        Assert.assertEquals(3,seniorsCheck(list1));
        Assert.assertEquals(2,seniorsCheck(list2));
        Assert.assertEquals(0,seniorsCheck(list3));
        Assert.assertEquals(0,seniorsCheck(list4));
        Assert.assertEquals(6,seniorsCheck(list5));
        Assert.assertEquals(3,seniorsCheck(list6));
        Assert.assertEquals(0,seniorsCheck(list7));
        Assert.assertEquals(3,seniorsCheck(list8));
        Assert.assertEquals(0,seniorsCheck(list9));
        Assert.assertEquals(0,seniorsCheck(list10));
    }

    @Test
    public void averageAgeTest() {
        Assert.assertEquals(38,averageAge(list1));
        Assert.assertEquals(34,averageAge(list2));
        Assert.assertEquals(29,averageAge(list3));
        Assert.assertEquals(29,averageAge(list4));
        Assert.assertEquals(50,averageAge(list5));
        Assert.assertEquals(37,averageAge(list6));
        Assert.assertEquals(31,averageAge(list7));
        Assert.assertEquals(35,averageAge(list8));
        Assert.assertEquals(23,averageAge(list9));
        Assert.assertEquals(33,averageAge(list10));
    }

}
