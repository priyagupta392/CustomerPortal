package com.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Base.BasePage;

public class VerifyOtp extends BasePage{
	
	public VerifyOtp(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//input[@inputmode='numeric']")
	WebElement otpfield;
	
	@FindBy(xpath = "//span[starts-with(text(),'Verify OTP')]")
	WebElement verfiy;
	
	
	public void enterOtp(String value) {
		waitforVisibilityofElement(otpfield);
		otpfield.sendKeys(value);
	}
	
	public void clickVerfiyOtp() {
		waitforVisibilityofElement(verfiy);
		Click(verfiy);
	}
	
	

}
