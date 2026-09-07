package com.Test;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.BasePage;
import com.Base.BaseTest;
import com.Pages.VerifyOtp;
import com.Pages.dashboard;
import com.Pages.login;
import com.database.db;
import com.util.ReadProperties;
import com.util.RetryAnalyzer;

public class ProfilePageTest extends BaseTest{

	
	login log;
	VerifyOtp vOtp;
	db databse;
	dashboard disc;
	
	
	
	@BeforeClass
	
	public void setUp() throws IOException {
		
	launchBrowser();

	log = new login(driver);
	vOtp = new VerifyOtp(driver);
	databse = new db();
	disc = new dashboard(driver);
	
	}
	
	@AfterClass
    public void tearDown() {

        closeBrowser();
    }
	
	@Test(priority = 1,   description = "Verify successful login using valid OTP AND user's initials are displayed in the top navigation bar after login", groups = "login")
	public void loginwithvalidOtp() throws IOException, SQLException {
		
		
		String Email = ReadProperties.getProperties("email");
		log.enterEmail(Email);
		log.clickTandC();
		log.clickOtpBtn();
		
		String otp = databse.getOtp();
		vOtp.enterOtp(otp);
		vOtp.clickVerfiyOtp();
		System.out.println(log.getToastMessage());
		System.out.println("Current URL: " + driver.getCurrentUrl());
		System.out.println("Page Title: " + driver.getTitle());
		System.out.println(disc.userInitials());
		Assert.assertEquals(disc.userInitials(), "VS");
				
	}
	
	@Test(priority = 2, description = "Verify all dropdown options are displayed", groups = "profile", retryAnalyzer = RetryAnalyzer.class)
	public void Userprofile() throws IOException, SQLException {
		disc.clickProfileIcone();
		disc.printListOfItemsinProfile();
	  Assert.assertTrue(disc.verifyProfileItems(),"One or more profile menu items are missing");
	}
	
	@Test(priority = 3, description = "Verify Profile option redirects to Profile page", groups = "profile", dependsOnMethods = "Userprofile",retryAnalyzer = RetryAnalyzer.class)
	public void  ProfileOptionRedirects() {
	disc.clickProfile();
	System.out.println(driver.getCurrentUrl());
	Assert.assertTrue(driver.getCurrentUrl().contains("/profile"),"Profile page was not opened");
			
	}
	
	@Test(priority = 4, description = "Verify About Us option redirects to About Us page", groups = "profile",dependsOnMethods = "Userprofile")
	public void AboutUSRedirects() {
		String parent = driver.getWindowHandle();
		
		disc.clickProfileIcone();
		disc.clickAboutUs();
		
		String newWindow = disc.switchToNewTab(parent);
		
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("/about-us/"), "About Us page was not opened");
		
		driver.close();
		driver.switchTo().window(parent);
		Assert.assertTrue(driver.getWindowHandle().equals(parent), "Failed to return to parent tab");
	}
	
//	@Test(priority = 5,description = "Verify View Sold Stock option redirects to Sold Stock page",groups = "profile",dependsOnMethods = "Userprofile",retryAnalyzer = RetryAnalyzer.class)
//	public void ViewSoldStckRedirects() {
//		disc.clickProfileIcone();
//		disc.clickViewSoldStck();
//		Assert.assertTrue(driver.getCurrentUrl().contains("/holdings/view-sold-stock"), "View Sold Stock page was not opened");
//	}
	
	@Test(priority = 6,description = "Verify FAQs option redirects to FAQs page",groups = "profile",dependsOnMethods = "Userprofile", retryAnalyzer = RetryAnalyzer.class)
	public void FAQsRedirects() {
		disc.clickProfileIcone();
		disc.clickFAQs();
		Assert.assertTrue(driver.getCurrentUrl().contains("/faq"),"FAQs page was not opened");
	}
	
	@Test(priority = 7,description = "Verify Contact Us option redirects to Contact Us page",groups = "profile",dependsOnMethods = "Userprofile")
	public void contactUsRedirects() {
		disc.clickProfileIcone();
		disc.clickContact();
		Assert.assertTrue(disc.sectionContact(), "Contact Us section is not displayed");
		disc.closeButton();
	}
	
	@Test(priority = 8,description = "Verify LogOut option redirects to Login page",groups = "profile",dependsOnMethods = "Userprofile")
	public void LogOut() {
		disc.clickProfileIcone();
		disc.clickLogout();
		Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Login page not Displayed");
				
	}
	
	
}
