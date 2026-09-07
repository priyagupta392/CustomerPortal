package com.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Base.BaseTest;
import com.util.ScreenshotUtils;

public class TestListener implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("Inside onTestFailure");
		System.out.println("Test Failed: " + result.getName());

		BaseTest test = (BaseTest) result.getInstance();

		try {

			ScreenshotUtils.captureScreenshot(test.getDriver(), result.getName());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}