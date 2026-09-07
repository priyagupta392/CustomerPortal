package com.Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.util.ReadProperties;

public class BaseTest {
	
	protected WebDriver driver;
	
	public WebDriver getDriver() {
	    return driver;
	}
	
	public void launchBrowser() throws IOException {
		
		String url = ReadProperties.getProperties("ChromeURL");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
				
	}
	
	
	public void closeBrowser() {
		driver.quit();
	}
	

}
