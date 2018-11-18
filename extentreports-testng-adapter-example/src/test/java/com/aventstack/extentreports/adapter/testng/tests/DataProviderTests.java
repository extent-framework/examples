package com.aventstack.extentreports.adapter.testng.tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners({ ExtentITestListenerClassAdapter.class })
public class DataProviderTests {

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][] { { "testuser_1", "Test@123" }, { "testuser_2", "Test@123" } };
    }

    @Test(dataProvider = "Authentication", groups = { "ExtentFramework", "TestNG", "a:Anshoo", "d:iPhoneX" })
    public void passDataProviderTests(String user, String password) {
        Assert.assertTrue(true);
    }

    @Test(dataProvider = "Authentication")
    public void failDataProviderTests(String user, String password) {
        Assert.assertTrue(false);
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result) {
        switch (result.getStatus()) {
        case ITestResult.FAILURE:
            ExtentTestManager.getTest(result).fail("ITestResult.FAILURE, event afterMethod");
            break;
        case ITestResult.SKIP:
            ExtentTestManager.getTest(result).skip("ITestResult.SKIP, event afterMethod");
            break;
        default:
            ExtentTestManager.getTest(result).pass("default, event afterMethod");
            break;
        }
    }

}
