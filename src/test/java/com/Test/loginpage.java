package com.Test;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Base.BaseTest;
import com.Pages.VerifyOtp;
import com.Pages.dashboard;
import com.Pages.login;
import com.database.db;
import com.util.ReadProperties;
import com.util.RetryAnalyzer;
import com.util.TestDataGenerator;

public class loginpage extends BaseTest{
	
	
	login log;
	VerifyOtp vOtp;
	db databse;
	dashboard disc;
	
	
	
	@BeforeMethod
	
	public void setUp() throws IOException {
		
	launchBrowser();
	log = new login(driver);
	vOtp = new VerifyOtp(driver);
	databse = new db();
	disc = new dashboard(driver);
	
	}
	
	@AfterMethod
    public void tearDown() {

        closeBrowser();
    }
	
	
	
	@Test(priority = 1, description = "Verify login page displays Email/Mobile input field" , groups = "UI")
	public void Login_UI() {
		
		Assert.assertTrue(log.isEmailInputDisplayed(),"Email/Mobile input field is not displayed");
		Assert.assertTrue(log.getGetOtpButton().isDisplayed(),"Get OTP button is not displayed");
		
	}
	
	
	@Test(priority = 2,   description = "verifyGetOtpButtonDisabledWhenInputFieldIsEmpty", groups = "UI")
	public void GetOtpButtonDisabled() {
		
		log.clearEmailInput();
		Assert.assertFalse(log.getGetOtpButton().isEnabled(), "Get OTP button should be disabled when the input field is empty.");
		
	}
	
	
	@Test(priority = 3,   description = "Verify Get OTP button is enabled after entering valid email", groups = "UI")
	public void GetOtpButtonEnabled() throws IOException {
		
		String Email = ReadProperties.getProperties("email");
		System.out.println(Email);
		log.enterEmail(Email);
		log.clickTandC();
		Assert.assertTrue(log.getGetOtpButton().isEnabled());
				
	}
	
	@Test(priority = 4, description = "Verify Terms & Conditions link is displayed", groups = "UI")
	public void TermsAndConditiondisplayed() {
		
		Assert.assertTrue(log.isTermsDisplayed(),"Terms and Conditions are not displayed");
	}
	
	
	@Test(priority = 5, description = "Verify Terms & Conditions page opens", groups = "UI")
	public void openTerms() {
		
		log.clickTermsandCondition();
		Assert.assertTrue(driver.getCurrentUrl().contains("disclaimer"),"Terms & Conditions page was not opened");
		
	}

	@Test(priority = 6,   description = "Verify OTP is sent for valid registered email", groups = "login")
	public void OtpSent() throws IOException {
		
		String Email = ReadProperties.getProperties("email");
		log.enterEmail(Email);
		log.clickTandC();
		log.clickOtpBtn();
		Assert.assertEquals(log.getToastMessage(), "OTP has been sent to your email");
	}
	
	@Test(priority = 7,  description = "Verify OTP is sent for valid registered mobile number", groups = "login")
	public void mobOtp() throws IOException {
		
		String MobNo = ReadProperties.getProperties("mobile");
		log.enterMobile(MobNo);
		log.clickTandC();
		log.clickOtpBtn();
		
	}
	
		
	@Test(description = "Verify invalid email displays error" , groups = "Validation Msg")
	public void EmailValidationMsg() throws IOException {
		
		String Email = TestDataGenerator.generateEmail();
		String expectederrormsg = ReadProperties.getProperties("expected.email.validationmsg");
		
		log.enterEmail(Email);
		log.clickTandC();
		log.clickOtpBtn();
		log.isInvalidEmailValidationMsgDisplayed();
		Assert.assertEquals(log.isInvalidEmailValidationMsgDisplayed(), expectederrormsg);
	}
	
	@Test(description = "Verify incorrect OTP error", groups = "Validation Msg")
	public void InvalidOTP() throws IOException {
		
		String Email = ReadProperties.getProperties("email");
		String invalidotp = TestDataGenerator.generateOtp();
		log.enterEmail(Email);
		log.clickTandC();
		log.clickOtpBtn();
		vOtp.enterOtp(invalidotp);
		vOtp.clickVerfiyOtp();
		
		String actualmsg = log.getToastMessage();
		System.out.println(actualmsg);
		String expectederrormsg = ReadProperties.getProperties("expected.otp.validationmsg");
		Assert.assertEquals(actualmsg, expectederrormsg);
		
	}
	
	
	
	
	
}
