package com.aventstack.extentreports.adapter.testng.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;

@Listeners({ExtentITestListenerClassAdapter.class})
public class SimpleAssertTests {

    @Test
    public void passTest() {
        Assert.assertTrue(true);
    }
    
    @Test
    public void failTest() {
        Assert.assertTrue(false);
    }
    
}
