package com.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

	public static String captureScreenshot(WebDriver driver, String testName) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String path = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";

		System.out.println("Screenshot captured at: " + path);

		File dest = new File(path);

		Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

		return path;
	}
}