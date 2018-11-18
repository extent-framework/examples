package com.aventstack.extentreports.adapter.testng.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners({ ExtentITestListenerClassAdapter.class })
public class ScreenshotTests {

    private static final String IMG_NAME = "screenshot.png";

    // A static image stored under classpath
    private static final String IMG_PATH = "src/test/resources/" + IMG_NAME;

    // Using the same OUTPUT_PATH as set in extent.properties
    // [extent.reporter.html.out]
    private static final String OUTPUT_PATH = "test-output/HtmlReport/";

    @Test
    public void passTest() {
        Assert.assertTrue(true);
    }

    /**
     * A screenshot will be attached for failTest
     */
    @Test
    public void failTest() {
        Assert.assertTrue(false);
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) throws IOException {
        switch (result.getStatus()) {
        case ITestResult.FAILURE:
            ExtentTestManager.getTest(result).fail("ITestResult.FAILURE, event afterMethod",
                    MediaEntityBuilder.createScreenCaptureFromPath(getImage()).build());
            break;
        case ITestResult.SKIP:
            ExtentTestManager.getTest(result).skip("ITestResult.SKIP, event afterMethod");
            break;
        default:
            break;
        }
    }

    /**
     * !!This code block is just an example only!! !!Real-world implemention would
     * require capturing a screenshot!!
     * 
     * @return Image path
     * @throws IOException
     */
    private String getImage() throws IOException {
        Files.copy(new File(IMG_PATH).toPath(), new File(OUTPUT_PATH + IMG_NAME).toPath());
        return IMG_NAME;
    }

}
