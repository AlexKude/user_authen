package com.runner;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Main Server on 05.12.2016.
 */
public class CustomListener extends RunListener {
    File log;
    PrintWriter logWriter;

    public CustomListener() throws Exception {
        super();
        init();
    }

    public void init() throws Exception {
        log = new File("log.html");
        if (!log.exists()) {
            log.createNewFile();
        }
        logWriter = new PrintWriter(log.getAbsoluteFile());
        logWriter.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        logWriter.println("<html>");
        logWriter.println("<head>");
        logWriter.println("<title>Log</title>");
        logWriter.println("</head>");
        logWriter.println("<body>");
        logWriter.println("<h3>Test Log</h3>");
        logWriter.println("<ul>");
    }


    @Override
    public void testRunStarted(Description description) throws Exception {
        logWriter.println("<li>" + description.toString() + " run test started </li>");
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        logWriter.println("<li>" + result.getRunCount() + " run test finished </li>");
        logWriter.println("</ul>");
        logWriter.println("</body>");
        logWriter.println("</html>");
        logWriter.flush();
        logWriter.close();
    }

    @Override
    public void testStarted(Description description) throws Exception {
        logWriter.println("<li>" + description.toString() + " started </li>");
    }

    @Override
    public void testFinished(Description description) throws Exception {
        logWriter.println("<li>" + description.toString() + " finished </li>");
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        logWriter.println("<li> failure: " + failure.getDescription() + "</li>");
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        try {
            logWriter.println("<li> failure assumed: " + failure.getDescription() + "</li>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        logWriter.println("<li>" + description.toString() + "ignored </li>");
    }
}
